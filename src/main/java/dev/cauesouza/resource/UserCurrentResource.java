package dev.cauesouza.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import dev.cauesouza.application.Result;
import dev.cauesouza.dto.user.UserDTO;
import dev.cauesouza.dto.user.UserResponseDTO;
import dev.cauesouza.service.FileService;
import dev.cauesouza.service.HashService;
import dev.cauesouza.service.UserService;
import dev.cauesouza.util.ImageForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/usercurrent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserCurrentResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    @Inject
    HashService hashService;

    @Inject
    FileService fileService;

    @GET
    @RolesAllowed({ "Admin", "User" })
    public Response getUserEntity() {

        String login = jwt.getSubject();
        UserResponseDTO UserEntity = userService.findByLogin(login);
        return Response.ok(UserEntity).build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed({ "Admin", "User" })
    public Response updatePassword(@PathParam("id") Long id, UserDTO receivedDTO) {

        String login = jwt.getSubject();
        UserResponseDTO UserEntity = userService.updatePassword(id, login);

        return Response.ok(UserEntity).build();
    }

    @PATCH
    @Path("/newImage")
    @RolesAllowed({ "Admin", "User" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form) {
        String imageName = "";

        try {
            imageName = fileService.salvarImagemUserEntity(form.getImagem(), form.getNomeImagem());
        } catch (IOException e) {
            Result result = new Result(e.getMessage());
            return Response.status(Status.CONFLICT).entity(result).build();
        }

        String login = jwt.getSubject();
        UserResponseDTO UserEntity = userService.findByLogin(login);

        UserEntity = userService.update(UserEntity.id(), imageName);

        return Response.ok(UserEntity).build();

    }

    @GET
    @Path("/download/{imageName}")
    @RolesAllowed({ "Admin", "User" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("imageName") String imageName) {
        ResponseBuilder response = Response.ok(fileService.download(imageName));
        response.header("Content-Disposition", "attachment;filename=" + imageName);
        return response.build();
    }

}
