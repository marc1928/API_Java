package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Reservations;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;
import fr.unilasalle.flight.api.repositories.ReservationsRepository;

import java.util.List;

@Model
public class ReservationsRepository implements PanacheRepositoryBase<Reservations, Long> {

    public void deleteAllReservations() {
        deleteAll();
    }

    public List<Reservations> findByFlightNumber(String number) {
        return find("flight.number",number).list();
    }

    public List<Reservations> findByPassagerId(Long passagerId) {
        return find("passager.id", passagerId).list();
    }

}
