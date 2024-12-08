package web.services;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import web.models.Attempt;

import java.util.Set;

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
                    break;
                }
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
