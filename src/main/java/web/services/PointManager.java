package web.services;

import lombok.extern.log4j.Log4j2;
import web.containers.PointData;
import web.dao.PointDAO;
import web.models.Point;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Log4j2
@ApplicationScoped
@Named
public class PointManager {

    @Inject
    private PointDAO dao;

    @Inject
    private PointFactory pointFactory;

    public void addPoint(PointData data) {
        try {
            log.info(data.toString());

            Point a = pointFactory.getPoint(data);

            dao.save(a);
        }
        catch (Exception e) {
            log.info(e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown error" , e.getMessage()));
        }
    }
}
