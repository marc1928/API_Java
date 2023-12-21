package fr.unilasalle.flight.api.ressources;
import fr.unilasalle.flight.api.beans.Avion;
import fr.unilasalle.flight.api.beans.Flights;
import fr.unilasalle.flight.api.repositories.FlightsRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Path("/flights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightsResources extends GenericRessource {

    @Inject
    private FlightsRepository repository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFlights() {
        List<Flights> flights = repository.listAll();
        return Response.ok(flights).build();
    }

    @GET
    public Response getFlightsList(@QueryParam("destination") String destinantion) {
        List<Flights> list;
        if (StringUtils.isBlank(destinantion)) {
            list = repository.listAll();
        } else {
            list = repository.findByDestination(destinantion);
        }
        return getOr404(list);
    }
    @GET
    @Path("/{id}")
    public Response getFlightById(@PathParam("id") Long id) {
        var flight = repository.findByIdOptional(id).orElse(null);
        return getOr404(flight);
    }
    @POST
    @Transactional
    public Response createFlight(Flights flight) {
        try {
            repository.persistAndFlush(flight);
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
    public Response updateFlight(@PathParam("id") Long id, Flights updatedFlight) {
        Flights existingFlight = repository.findByIdOptional(id).orElse(null);
        if (existingFlight == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Mettre à jour les attributs du vol existant avec les données du vol mis à jour
        existingFlight.setNumber(updatedFlight.getNumber());
        existingFlight.setOrigin(updatedFlight.getOrigin());
        existingFlight.setDestination(updatedFlight.getDestination());
        existingFlight.setDepartureDate(updatedFlight.getDepartureDate());
        existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
        existingFlight.setArrivalDate(updatedFlight.getArrivalDate());
        existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
        existingFlight.setPlane(updatedFlight.getPlane());

        try {
            repository.persistAndFlush(existingFlight);
            return Response.ok(existingFlight).build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new ErrorWrapper(e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteFlight(@PathParam("id") Long id) {
        Flights existingFlight = repository.findByIdOptional(id).orElse(null);
        if (existingFlight == null) {
            System.out.println("je suis arrive la ");
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            repository.delete(existingFlight);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response.serverError()
                    .entity(new ErrorWrapper(e.getMessage()))
                    .build();
        }
    }
}
