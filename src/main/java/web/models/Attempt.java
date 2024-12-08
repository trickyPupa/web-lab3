package web.models;

import javax.persistence.*;
import lombok.Data;
import web.validation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "attempts")
public class Attempt implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidationX
    private Double x = 0d;

    @ValidationY
    private Double y = 0d;

    @ValidationR
    private Double r = 4d;

    private boolean result;

    private double executionTime;

    private LocalDateTime createdAt = LocalDateTime.now();

    public String getCreatedAt() {
        return createdAt.format(formatter);
    }
}
