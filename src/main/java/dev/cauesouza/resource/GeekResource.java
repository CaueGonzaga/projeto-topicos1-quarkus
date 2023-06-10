package dev.cauesouza.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dev.cauesouza.dto.product.GeekDTO;
import dev.cauesouza.dto.product.GeekResponseDTO;
import dev.cauesouza.service.GeekServiceImpl;
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

@Path("/geeks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "geeks", description = "Operations on geeks resource.")
public class GeekResource {

    @Inject
    private GeekServiceImpl geekService;

    @GET
    @Operation(summary = "Get all geeks")
    public List<GeekResponseDTO> listarTodos() {
        return geekService.listAll();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get all geeks")
    public Response listarPorId(@PathParam("id") Long id) {
        GeekResponseDTO entity = geekService.findById(id);
        return Response.status(Status.OK).entity(entity).build();

    }

    @GET
    @Path("/search/descricao/{descricao}")
    @Operation(summary = "Get geek by id")
    public Response listarPorDescricao(@PathParam("descricao") String descricao) {
        List<GeekResponseDTO> list = geekService.findByDescription(descricao);
        return Response.status(Status.OK).entity(list).build();
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "geek created", content = @Content(schema = @Schema(implementation = GeekDTO.class)))
    @APIResponse(responseCode = "409", description = "geek already exists")
    @Operation(summary = "Create new geek")
    public Response create(@Valid GeekDTO receivedDTO) {
        geekService.persist(receivedDTO);
        return Response.status(Status.CREATED).entity("Cadastrado com sucesso").build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    @APIResponse(responseCode = "204", description = "geek updated", content = @Content(schema = @Schema(implementation = GeekDTO.class)))
    @APIResponse(responseCode = "409", description = "geek already exists")
    @Operation(summary = "Edit geek by ID")
    public Response update(@PathParam("id") Long id, @Valid GeekDTO receivedProdutoDTO) {
        geekService.update(id, receivedProdutoDTO);
        return Response.status(Status.NO_CONTENT).entity("Atualizado com sucesso!").build();

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "geek deleted")
    @Operation(summary = "Delete geek by ID")
    public Response delete(@PathParam("id") Long id) {
        geekService.deleteById(id);
        return Response.status(Status.OK).entity("Deletado com sucesso!").build();

    }
}
