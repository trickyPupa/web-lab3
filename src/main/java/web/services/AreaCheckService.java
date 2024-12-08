package web.services;

import javax.enterprise.context.ApplicationScoped;
import web.models.Attempt;

import java.io.Serializable;

@ApplicationScoped
public class AreaCheckService implements Serializable {
    public boolean checkArea(Attempt attempt) {
        double x = attempt.getX(), y = attempt.getY(), r = attempt.getR();

        if (x >= 0 && y >= 0 && y <= x + r) {
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
