package web.services;

import javax.enterprise.context.ApplicationScoped;
import web.models.Point;

import java.io.Serializable;

@ApplicationScoped
public class AreaCheckService implements Serializable {
    public boolean checkAreaHit(Point point) {
        double x = point.getX(), y = point.getY(), r = point.getR();

        if (x >= 0 && y >= 0 && y <= r - x) {
            return true;
        }
        else if (x >= 0 && y <= 0 && y * y + x * x <= r * r / 4) {
            return true;
        }
        else if (x <= 0 && y <= 0 && x >= -r && y >= -r / 2) {
            return true;
        }
        return false;
    }
}
