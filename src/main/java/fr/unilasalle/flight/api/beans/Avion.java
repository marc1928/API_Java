package fr.unilasalle.flight.api.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table ( name="planes")



public class Avion {
    @Id
    @SequenceGenerator( name ="planeSequence", sequenceName = "planeSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "planeSequence")
    private long  id;

    @Column (nullable = false)
    @NotBlank
    private String operator;

    @NotBlank
    @Column (nullable = false)
    private String model;

    @NotBlank
    @Column (nullable = false)
    private String registration;

    @NotNull
    @Column (nullable = false)
    private Integer capacity;
}
