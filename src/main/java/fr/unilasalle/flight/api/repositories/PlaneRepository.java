package fr.unilasalle.flight.api.repositories;

import fr.unilasalle.flight.api.beans.Avion;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.inject.Model;

import java.util.List;

@Model
public class PlaneRepository implements PanacheRepositoryBase<Avion, Long> {

    public void deleteAllAvions() {
        deleteAll();
    }

    public List<Avion> findByOperator(String operatorParametre) {
        return find("operator", operatorParametre).list();
    }


}
