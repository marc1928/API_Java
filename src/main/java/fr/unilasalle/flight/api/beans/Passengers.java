package fr.unilasalle.flight.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "passengers")
public class Passengers {

    @Id
    @SequenceGenerator(name ="passengersSequence", sequenceName = "passengersSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "passengersSequence")
    private Integer id;
    @NotBlank
    @Column(nullable = false)
    private String surname;

    @NotBlank
    @Column(nullable = false)
    private String firstname;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "passager")
    private List<Reservations> reservations;



}
