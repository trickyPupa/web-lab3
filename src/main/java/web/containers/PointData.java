package web.containers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@Named("point")
@RequestScoped
public class PointData implements Serializable {
    public static final List<Double> X_VALUES = Arrays.asList(-5d, -4d, -3d, -2d, -1d, 0d, 1d, 2d, 3d);

    private String x = "0";
    private String y = "0";
    private String r = "2";

    public String toString(){
        return x + " " + y + " " + r;
    }

    public Double getXValue() {
        return Double.parseDouble(x);
    }

    public Double getYValue() {
        return Double.parseDouble(y);
    }

    public Double getRValue() {
        return Double.parseDouble(r);
    }

    public List<Double> getXValues() {
        return X_VALUES;
    }
}
