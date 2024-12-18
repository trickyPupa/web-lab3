package web.controllers;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import web.containers.PointData;
import web.models.Attempt;
import web.dao.AttemptDAO;
import web.services.AttemptFactory;
import web.services.ValidationService;

import java.io.Serializable;

@Log4j2
@Named("resultController")
@ApplicationScoped
public class ResultController implements Serializable {

    @Inject
    private PointData pointData;

    @Inject
    private AttemptDAO dao;

    @Inject
    private AttemptFactory attemptFactory;

    @Inject
    private ValidationService validationService;

    public String submit() {
        return submit(false);
    }

    public String submit(boolean redirect) {
        try {
            log.info(pointData.toString());

            Attempt a = attemptFactory.getAttempt(pointData);

            if (!validate(a)) {
                return null;
            }
            log.info("validation ok");

            dao.save(a);
        }
        catch (Exception e) {
            log.info(e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown error" , e.getMessage()));
        }

        if (redirect) {
            return "main?faces-redirect=true";
        }
        return "main";
    }

    public boolean validate(Attempt attempt) {
        return validationService.isValid(attempt);
    }
}
