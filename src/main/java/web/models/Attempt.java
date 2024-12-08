package web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import web.validation.*;

import java.beans.JavaBean;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JavaBean
@Entity
@Table(name = "attempts")
public class Attempt implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public record Coordinates(double x, double y, double r, boolean result) { };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidationX
    private Double x;

    @ValidationY
    private Double y;

    @ValidationR
    private Double r;

    private boolean result;

    private double executionTime;

    private LocalDateTime createdAt = LocalDateTime.now();

    public String getCreatedAt() {
        return createdAt.format(formatter);
    }

    public Coordinates getCoordinates() {
        return new Coordinates(x, y, r, result);
    }
}
