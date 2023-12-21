package fr.unilasalle.flight.api.beans;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reservations")
public class Reservations {
    @Id
    @SequenceGenerator( name ="reservationsSequence", sequenceName = "reservationsSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "reservationsSequence")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false)
    private Flights flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", nullable = false)
    private Passengers passager;


}
