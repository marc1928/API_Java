package fr.unilasalle.flight.api.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flights")
public class Flights {

    @Id
    @SequenceGenerator(name = "flightsSequence", sequenceName = "flightsSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flightsSequence")
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String number;

    @NotBlank
    @Column(nullable = false)
    private String origin;

    @NotBlank
    @Column(nullable = false)
    private String destination;

    @NotNull
    @Column(nullable = false)
    private Date departureDate;

    @NotNull
    @Column(nullable = false)
    private Time departureTime;

    @NotNull
    @Column(nullable = false)
    private Date arrivalDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Avion plane;

    @NotNull
    @Column(nullable = false)
    private Time arrivalTime;



}
