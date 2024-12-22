package web.services;

import lombok.extern.log4j.Log4j2;
import web.containers.PointData;
import web.models.Point;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;
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

        validate(a);

        return a;
    }

    public void validate(Point point) throws ValidationException {
        if (!validationService.isValid(point)) {
            throw new ValidationException();
        }
        log.info("validation ok");
    }
}
