package dev.cauesouza.resource;

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
import dev.cauesouza.dto.user.UserDTO;
import dev.cauesouza.dto.user.UserResponseDTO;
import dev.cauesouza.service.UserServiceImpl;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserServiceImpl userService;

    @GET
    public Response listAll() {
        List<UserResponseDTO> list = userService.listAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {
        List<UserResponseDTO> list = userService.findByName(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public UserResponseDTO findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @POST
    public Response persist(@RequestBody UserDTO receivedDTO) {
        try {
            UserResponseDTO entity = userService.persist(receivedDTO);
            return Response.status(Status.CREATED).entity(entity).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody UserDTO receivedDTO) {
        try {
            UserResponseDTO entity = userService.update(id, receivedDTO);
            ;
            return Response.status(Status.NO_CONTENT).entity(entity).build();

        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        userService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Long count = userService.count();
        return Response.status(Status.OK).entity(count).build();
    }
}