package dev.cauesouza.resource;

import java.util.List;

import dev.cauesouza.dto.order.OrderDTO;
import dev.cauesouza.dto.order.OrderResponseDTO;
import dev.cauesouza.service.OrderServiceImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

@Path("/api/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    private OrderServiceImpl orderService;

    @GET
    public List<OrderResponseDTO> listarTodos() {
        return orderService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response listarPorId(@PathParam("id") Long id) {
            OrderResponseDTO entity = orderService.findById(id);
            return Response.status(Status.OK).entity(entity).build();
      
    }

    @POST
    @Transactional
    public Response create(@Valid OrderDTO receivedEntity) {
            orderService.persist(receivedEntity);
            return Response.status(Status.CREATED).entity("Cadastrado com sucesso").build();
      
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid OrderDTO receivedEntity) {
            orderService.update(id, receivedEntity);
            return Response.status(Status.NO_CONTENT).entity("Atualizado com sucesso!").build();
      
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
            orderService.deleteById(id);
            return Response.status(Status.OK).entity("Deletado com sucesso!").build();
      
    }
}
