package web.controllers;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import web.containers.AttemptContainer;
import web.services.AreaCheckService;
import web.dao.AttemptDAO;
import web.services.ValidationService;

import java.io.Serializable;

@Log4j2
@Named("resultController")
@ApplicationScoped
public class ResultController implements Serializable {

    @Inject
    private AttemptContainer current;

    @Inject
    private AttemptDAO dao;

    @Inject
    private AreaCheckService areaCheckService;

    @Inject
    private ValidationService validationService;

    public String check() {
        return check(false);
    }

    public String check(boolean redirect) {
        try {
            log.info(current.get().toString());

            if (!validate()) {
                return null;
            }
            log.info("validation ok");

            current.setResult(areaCheckService.checkArea(current.get()));
            dao.save(current.get());

            current.reset();
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

    public boolean validate() {
        return validationService.isValid(current.get());
    }
}
