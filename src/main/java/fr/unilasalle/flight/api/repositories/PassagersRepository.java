package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Passengers;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class PassagersRepository implements PanacheRepositoryBase<Passengers, Long> {

    public void deleteAllPassagers() {
        deleteAll();
    }

    public List<Passengers> findBySurname(String surname) {
        return find("surname", surname).list();
    }

    public List<Passengers> findByFirstname(String firstname) {
        return find("firstname", firstname).list();
    }

    public Passengers findByEmail(String email) {
        return find("email", email).firstResult();
    }

}
