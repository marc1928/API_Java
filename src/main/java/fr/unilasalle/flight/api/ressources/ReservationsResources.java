package fr.unilasalle.flight.api.ressources;

import fr.unilasalle.flight.api.beans.Flights;
import fr.unilasalle.flight.api.beans.Reservations;
import fr.unilasalle.flight.api.repositories.ReservationsRepository;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@Path("/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationsResources extends GenericRessource {
    @Inject
    private ReservationsRepository repository;
    @Inject
    Validator validator;
    @GET
    public Response getAllReservations() {
        List<Reservations> reservations = repository.listAll();
        return Response.ok(reservations).build();
    }

    @GET
    public Response getReservationsList(@QueryParam("number") String number) {
        List<Reservations> list;
        if (StringUtils.isBlank(number)) {
            list = repository.listAll();
        } else {
            list = repository.findByFlightNumber(number);
        }
        return getOr404(list);
    }


    @Path("/{id}")
    public Response getReservationById(@PathParam("id") Long id) {
        var reservation = repository.findByIdOptional(id).orElse(null);
        return getOr404(reservation);
    }
    @POST
    @Transactional
    public Response createReservation(Reservations reservation) {
        try {
            repository.persistAndFlush(reservation);
            return Response.status(201).build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new GenericRessource.ErrorWrapper(e.getMessage()))
                    .build();
        }
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateReservation(@PathParam("id") Long id, Reservations updatedReservation) {
        Reservations existingReservation = repository.findByIdOptional(id).orElse(null);
        if (existingReservation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingReservation.setId(updatedReservation.getId());
        existingReservation.setId(updatedReservation.getId());
        existingReservation.setFlight(updatedReservation.getFlight());
        existingReservation.setPassager(updatedReservation.getPassager());
        try {
            repository.persistAndFlush(existingReservation);
            return Response.ok(existingReservation).build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new GenericRessource.ErrorWrapper(e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteReservation(@PathParam("id") Long id) {
        Reservations existingReservation = repository.findByIdOptional(id).orElse(null);
        if (existingReservation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            repository.delete(existingReservation);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new GenericRessource.ErrorWrapper(e.getMessage()))
                    .build();
        }
    }
}
