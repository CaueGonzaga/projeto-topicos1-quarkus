package dev.cauesouza.resource;

import dev.cauesouza.dto.user.AuthUserEntityDTO;
import dev.cauesouza.dto.user.UserDTO;
import dev.cauesouza.model.user.UserEntity;
import dev.cauesouza.service.HashService;
import dev.cauesouza.service.TokenJwtService;
import dev.cauesouza.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UserService userService;

    @Inject
    TokenJwtService tokenService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthUserEntityDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.password());

        UserEntity user = userService.findByUsernameAndPassword(authDTO.username(), hash);

        if (user == null) {
            return Response.status(Status.NO_CONTENT)
                    .entity("UserEntity não encontrado").build();
        }
        return Response.ok()
                .header("Authorization", tokenService.generateJwt(user))
                .build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(UserDTO receivedDTO) {

        userService.persist(receivedDTO);

        return Response.ok().build();
    }
}