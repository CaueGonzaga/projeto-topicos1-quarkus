package dev.cauesouza.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import dev.cauesouza.application.Result;
import dev.cauesouza.dto.address.StateDTO;
import dev.cauesouza.dto.address.StateResponseDTO;
import dev.cauesouza.service.StateServiceImpl;

@Path("/states")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class StateResource {

    @Inject
    private StateServiceImpl stateService;

    @GET
    public Response listAll() {
        List<StateResponseDTO> list = stateService.listAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {
        List<StateResponseDTO> list = stateService.findByName(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public StateResponseDTO findById(@PathParam("id") Long id) {
        return stateService.findById(id);
    }

    @POST
    @RolesAllowed("Admin")
    public Response persist(@RequestBody StateDTO receivedDTO) {
        try {
            StateResponseDTO entity = stateService.persist(receivedDTO);
            return Response.status(Status.CREATED).entity(entity).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response update(@PathParam("id") Long id, @RequestBody StateDTO receivedDTO) {
        try {
            StateResponseDTO entity = stateService.update(id, receivedDTO);
            ;
            return Response.status(Status.NO_CONTENT).entity(entity).build();

        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")

    public Response deleteById(@PathParam("id") Long id) {
        stateService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Long count = stateService.count();
        return Response.status(Status.OK).entity(count).build();
    }
}