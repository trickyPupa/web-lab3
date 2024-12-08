package web.controllers;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.containers.AttemptContainer;
import web.models.Attempt;
import web.services.AreaCheckService;
import web.services.AttemptsList;
import web.dao.AttemptDAO;
import web.services.ValidationService;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Log4j2
@Named("resultController")
@ApplicationScoped
public class ResultController implements Serializable {
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

    public void check() {
        try {
            log.info(current.get().toString());
            if (!validate()) {
                return;
            }

            current.setResult(areaCheckService.checkArea(current.get()));
            dao.save(current.get());

//            updateCanvas();

            current.reset();
        }
        catch (Exception e) {
            ;
        }

//        return "main?faces-redirect=true";
    }

    public List<Attempt> getList() {
        return dao.getAll();
    }

    public String getListToString() {
        return dao.getAll().toString();
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

    public void updateCanvas() {
        var list = getList();
        for (Attempt attempt : getList()) {
            boolean result = areaCheckService.checkArea(attempt);
            double r;
            if(current.getR() == null) {
                r = list.getLast().getR();
            } else {
                r = current.getR();
            }

            attempt.setR(r);
            attempt.setResult(result);

            String script = String.format(
                    Locale.US, "window.drawDot(%f, %f, %f, %b, true);",
                    attempt.getX(), attempt.getY(), r, result);
            System.out.println("Script: " + script);
            FacesContext.getCurrentInstance()
                    .getPartialViewContext()
                    .getEvalScripts()
                    .add(script);
        }
        log.info("Canvas updated with new radius: {}", current.getR());
    }

    public double getR() {
        return current.getR();
    }

    public void setR(double r) {
        current.setR(r);
        updateCanvas();
    }

    public String getCoordinates() {
        return new Gson().toJson(
                getList().stream().map(Attempt::getCoordinates).collect(Collectors.toList())
        );
    }
}
