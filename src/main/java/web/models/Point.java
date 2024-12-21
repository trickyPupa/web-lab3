package web.models;

import javax.persistence.*;
import lombok.Data;
import web.validation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "points")
public class Point implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

    private LocalDateTime createdAt;

    public String getCreatedAt() {
        return createdAt.format(formatter);
    }
}
