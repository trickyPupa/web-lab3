package web.models;

import javax.persistence.*;
import lombok.Data;
import web.validation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "attempts")
public class Attempt implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final List<Double> X_VALUES = Arrays.asList(-5d, -4d, -3d, -2d, -1d, 0d, 1d, 2d, 3d);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidationX
    private Double x = 0d;

    @ValidationY
    private Double y = 0d;

    @ValidationR
    private Double r = 2d;

    private boolean result;

    private double executionTime;

    private LocalDateTime createdAt = LocalDateTime.now();

    public String getCreatedAt() {
        return createdAt.format(formatter);
    }
}
