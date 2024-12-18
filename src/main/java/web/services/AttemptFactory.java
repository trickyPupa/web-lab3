package web.services;

import web.containers.PointData;
import web.models.Attempt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;

@ApplicationScoped
@Named
public class AttemptFactory {

    @Inject
    private AreaCheckService areaCheckService;

    public Attempt getAttempt(PointData data) {
        Attempt a = new Attempt();
        a.setX(data.getXValue());
        a.setY(data.getYValue());
        a.setR(data.getRValue());

        a.setResult(areaCheckService.checkAreaHit(a));
        a.setCreatedAt(LocalDateTime.now());

        return a;
    }
}
