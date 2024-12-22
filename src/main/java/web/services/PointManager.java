package web.services;

import lombok.extern.log4j.Log4j2;
import web.containers.PointData;
import web.dao.PointDAO;
import web.models.Point;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Log4j2
@Stateless
public class PointManager {

    @EJB
    private PointDAO dao;

    @EJB
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
