package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Flights;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class FlightsRepository implements PanacheRepositoryBase<Flights, Long> {

    public void deleteAllFlights() {
        deleteAll();
    }

    public List<Flights> findByDestination(String destinantion) {
        return find("destination", destinantion).list();
    }



}
