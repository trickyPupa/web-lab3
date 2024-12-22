package web.controllers;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import web.containers.PointData;
import web.services.PointManager;
import web.services.ResponseManager;

import java.io.Serializable;

@Log4j2
@Named("resultController")
@ApplicationScoped
public class ResultController implements Serializable {

    @Inject
    private PointData pointData;

    @EJB
    private PointManager pointManager;

    @Inject
    private ResponseManager responseManager;

    public String submit() {
        pointManager.addPoint(pointData);

        return responseManager.mainPageResponse();
    }
}
