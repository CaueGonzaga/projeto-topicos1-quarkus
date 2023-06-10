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
import dev.cauesouza.dto.address.AddressDTO;
import dev.cauesouza.dto.address.AddressResponseDTO;
import dev.cauesouza.service.AddressServiceImpl;

@Path("/addresses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class AddressResource {

    @Inject
    private AddressServiceImpl addressService;

    @GET
    @RolesAllowed("Admin")
    public Response listAll() {
        List<AddressResponseDTO> list = addressService.listAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {
        List<AddressResponseDTO> list = addressService.findByStreet(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public AddressResponseDTO findById(@PathParam("id") Long id) {
        return addressService.findById(id);
    }

    @POST
    public Response persist(@RequestBody AddressDTO receivedDTO) {
        try {
            AddressResponseDTO entity = addressService.persist(receivedDTO);
            return Response.status(Status.CREATED).entity(entity).build();

        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody AddressDTO receivedDTO) {
        try {
            AddressResponseDTO entity = addressService.update(id, receivedDTO);
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
        addressService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    @RolesAllowed("Admin")
    public Response count() {
        Long count = addressService.count();
        return Response.status(Status.OK).entity(count).build();
    }
}