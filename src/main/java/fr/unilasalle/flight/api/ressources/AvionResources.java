package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Avion;
import fr.unilasalle.flight.api.repositories.PlaneRepository;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;


import java.util.List;

@Path("/planes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvionResources extends GenericRessource {

    @Inject
    private PlaneRepository repository;
    @Inject
    Validator validator;
    @GET
    public Response getPlanesList(@QueryParam("operator") String operator) {
        List<Avion> list;
        if (StringUtils.isBlank(operator)) {
            list = repository.listAll();
        } else {
            list = repository.findByOperator(operator);
        }
        return getOr404(list);
    }

    @GET
    @Path("/{id}")
    public Response getPlaneById(@PathParam("id") Long id) {
        var avion = repository.findByIdOptional(id).orElse(null);
        return getOr404(avion);
    }
    @POST
    @Transactional
    public Response createPlane(Avion plane) {
        var violations = validator.validate(plane);
        if(!violations.isEmpty()) {
            return Response.status(400)
                    .entity(new ErrorWrapper(violations))
                    .build();
        }
        try {
            repository.persistAndFlush(plane);
            return Response.status(201).build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new ErrorWrapper(e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePlane(@PathParam("id") Long id, Avion updatedPlane) {
        Avion existingPlane = repository.findByIdOptional(id).orElse(null);
        if (existingPlane == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Mettre à jour les attributs de l'avion existant avec les données de l'avion mis à jour
        existingPlane.setOperator(updatedPlane.getOperator());
        existingPlane.setModel(updatedPlane.getModel());
        existingPlane.setRegistration(updatedPlane.getRegistration());
        existingPlane.setCapacity(updatedPlane.getCapacity());

        try {
            repository.persistAndFlush(existingPlane);
            return Response.ok(existingPlane).build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new ErrorWrapper(e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePlane(@PathParam("id") Long id) {
        Avion existingPlane = repository.findByIdOptional(id).orElse(null);
        if (existingPlane == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            repository.delete(existingPlane);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new ErrorWrapper(e.getMessage()))
                    .build();
        }
    }


}

