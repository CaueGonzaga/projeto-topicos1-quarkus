package dev.cauesouza.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dev.cauesouza.dto.product.ProductDTO;
import dev.cauesouza.dto.product.ProductResponseDTO;
import dev.cauesouza.service.ProductServiceImpl;
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

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "products", description = "Operations on produtos resource.")
public class ProdutosResource {

    @Inject
    private ProductServiceImpl productService;

    @GET
    @Operation(summary = "Get all products")
    public List<ProductResponseDTO> listarTodos() {
        return productService.listAll();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get all produtos")
    public Response listarPorId(@PathParam("id") Long id) {
        ProductResponseDTO entity = productService.findById(id);
        return Response.status(Status.OK).entity(entity).build();

    }

    @GET
    @Path("/search/descricao/{descricao}")
    @Operation(summary = "Get produto by id")
    public Response listarPorDescricao(@PathParam("descricao") String descricao) {
        List<ProductResponseDTO> list = productService.findByDescription(descricao);
        return Response.status(Status.OK).entity(list).build();
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "produto created", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "409", description = "produto already exists")
    @Operation(summary = "Create new produto")
    public Response create(@Valid ProductDTO receivedEntity) {
        productService.persist(receivedEntity);
        return Response.status(Status.CREATED).entity("Cadastrado com sucesso").build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    @APIResponse(responseCode = "204", description = "produto updated", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "409", description = "produto already exists")
    @Operation(summary = "Edit produto by ID")
    public Response update(@PathParam("id") Long id, @Valid ProductDTO receivedProdutoDTO) {
        productService.update(id, receivedProdutoDTO);
        return Response.status(Status.NO_CONTENT).entity("Atualizado com sucesso!").build();

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "produto deleted")
    @Operation(summary = "Delete produto by ID")
    public Response delete(@PathParam("id") Long id) {
        productService.deleteById(id);
        return Response.status(Status.OK).entity("Deletado com sucesso!").build();

    }
}
