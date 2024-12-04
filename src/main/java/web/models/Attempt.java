package web.models;

import jakarta.persistence.*;
import lombok.Data;
import web.validation.*;

import java.beans.JavaBean;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JavaBean
@Entity
@Table(name = "attempts")
public class Attempt implements Serializable {
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
}
