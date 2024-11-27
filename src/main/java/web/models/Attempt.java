package web.models;

import jakarta.persistence.*;
import lombok.*;
import web.validation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attempts")
public class Attempt implements Serializable {
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
}
