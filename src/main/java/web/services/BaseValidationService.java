package web.services;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lombok.extern.log4j.Log4j2;
import web.models.Attempt;

import java.util.Set;

@Log4j2
@ApplicationScoped
public class BaseValidationService implements ValidationService {
    @Override
    public boolean isValid(Attempt attempt) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Attempt>> a = validator.validate(attempt);
            if (a.isEmpty()) {
                return true;
            } else {
                for (ConstraintViolation<Attempt> c : a) {
                    log.info(c.getMessage());
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, c.getMessage(), null));
                    return false;
                }
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
