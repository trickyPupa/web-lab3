package web.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.containers.AttemptContainer;
import web.models.Attempt;
import web.services.AreaCheckService;
import web.services.AttemptsList;
import web.dao.AttemptDAO;
import web.services.ValidationService;

import java.io.Serializable;
import java.util.List;

@Named("resultController")
@ApplicationScoped
public class ResultController implements Serializable {
    private static final Logger logger = LogManager.getLogger(ResultController.class);

    @Inject
    @Getter
    private AttemptContainer current;

    @Getter
    @Inject
    private AttemptsList attemptsList;

    @Inject
    private AttemptDAO dao;

    @Inject
    private AreaCheckService areaCheckService;

    @Inject
    private ValidationService validationService;

    public String check() {
        try {
            logger.info(current.get().toString());
            if (!validate()) {
                return null;
            }

            current.setResult(areaCheckService.checkArea(current.get()));
            dao.save(current.get());
            current.reset();
        }
        catch (Exception e) {
            ;
        }

        return "main?faces-redirect=true";
    }

    public List<Attempt> getList() {
        return dao.getAll();
    }

    public void clear() {
        dao.deleteAll();
    }

    public boolean isEmpty() {
        return dao.getAll().isEmpty();
    }

    public boolean validate() {
        return validationService.isValid(current.get());
    }
}
