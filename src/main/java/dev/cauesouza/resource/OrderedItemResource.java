package dev.cauesouza.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dev.cauesouza.dto.ordered_item.OrderedItemDTO;
import dev.cauesouza.dto.ordered_item.OrderedItemResponseDTO;
import dev.cauesouza.service.OrderedItemServiceImpl;
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

@Path("/ordered-itens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "ordered itens", description = "Operations on itens pedidos resource.")
public class OrderedItemResource {

    @Inject
    private OrderedItemServiceImpl OrderedItemService;

    @GET
    @Operation(summary = "Get all itens pedidos")
    public List<OrderedItemResponseDTO> listarTodos() {
        return OrderedItemService.listAll();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get all itens pedidos")
    public Response listarPorId(@PathParam("id") Long id) {
        OrderedItemResponseDTO entity = OrderedItemService.findById(id);
        return Response.status(Status.OK).entity(entity).build();
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "produto created", content = @Content(schema = @Schema(implementation = OrderedItemDTO.class)))
    @APIResponse(responseCode = "409", description = "produto already exists")
    @Operation(summary = "Create new produto")
    public Response create(@Valid OrderedItemDTO receivedDTO) {
        OrderedItemService.persist(receivedDTO);
        return Response.status(Status.CREATED).entity("Cadastrado com sucesso").build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    @APIResponse(responseCode = "204", description = "produto updated", content = @Content(schema = @Schema(implementation = OrderedItemDTO.class)))
    @APIResponse(responseCode = "409", description = "produto already exists")
    @Operation(summary = "Edit produto by ID")
    public Response update(@PathParam("id") Long id, @Valid OrderedItemDTO receivedDTO) {
        OrderedItemService.update(id, receivedDTO);
        return Response.status(Status.NO_CONTENT).entity("Atualizado com sucesso!").build();

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "produto deleted")
    @Operation(summary = "Delete produto by ID")
    public Response delete(@PathParam("id") Long id) {
        OrderedItemService.deleteById(id);
        return Response.status(Status.OK).entity("Deletado com sucesso!").build();

    }
}
