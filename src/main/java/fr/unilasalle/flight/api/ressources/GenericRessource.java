package fr.unilasalle.flight.api.ressources;

import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenericRessource {

    protected Response getOr404(Object object) {
        if (object instanceof List<?> && ((List<?>) object).isEmpty()) {
            return Response.noContent().status(404).build();
        }

        if (object != null) {
            return Response.ok(object).status(200).build();
        } else {
            return Response.noContent().status(404).build();
        }
    }

    protected static class ErrorWrapper {
        private String message;
        private List<String> errors;

        ErrorWrapper(String message) {
            this.message = message;
            this.errors = new ArrayList<>();
        }
        ErrorWrapper(Set<? extends ConstraintViolation<?>> violations) {
            this.message = "";
            this.errors = new ArrayList<>();
            for (ConstraintViolation<?> violation : violations) {
                this.errors.add(violation.getMessage());
            }
        }
    }
}
