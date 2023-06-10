package dev.cauesouza.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import dev.cauesouza.application.Result;
import dev.cauesouza.dto.customer.CustomerDTO;
import dev.cauesouza.dto.customer.CustomerResponseDTO;
import dev.cauesouza.service.CustomerServiceImpl;
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

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    private CustomerServiceImpl customerService;

    @GET
    public Response listAll() {
        List<CustomerResponseDTO> list = customerService.listAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {
        List<CustomerResponseDTO> list = customerService.findByName(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public CustomerResponseDTO findById(@PathParam("id") Long id) {
        return customerService.findById(id);
    }

    @POST
    public Response persist(@RequestBody CustomerDTO receivedDTO) {
        try {
            CustomerResponseDTO entity = customerService.persist(receivedDTO);
            return Response.status(Status.CREATED).entity(entity).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody CustomerDTO receivedDTO) {
        try {
            CustomerResponseDTO entity = customerService.update(id, receivedDTO);
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
        customerService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Long count = customerService.count();
        return Response.status(Status.OK).entity(count).build();
    }
}