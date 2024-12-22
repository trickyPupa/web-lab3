package web.services;

import lombok.extern.log4j.Log4j2;
import web.containers.PointData;
import web.models.Point;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.LocalDateTime;

@Stateless
@Log4j2
public class PointFactory {

    @EJB
    private AreaCheckService areaCheckService;

    @EJB
    private ValidationService validationService;

    public Point getPoint(PointData data) {
        Point a = new Point();
        a.setX(data.getXValue());
        a.setY(data.getYValue());
        a.setR(data.getRValue());

        a.setResult(areaCheckService.checkAreaHit(a));
        a.setCreatedAt(LocalDateTime.now());

        if (!validate(a)) {
            return null;
        }
        log.info("validation ok");

        return a;
    }

    public boolean validate(Point point) {
        return validationService.isValid(point);
    }
}
