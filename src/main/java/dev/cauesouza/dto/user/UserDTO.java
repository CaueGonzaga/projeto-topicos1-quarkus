package dev.cauesouza.dto.user;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

import dev.cauesouza.model.user.Role;

public record UserDTO(
        @NotBlank(message = "O campo username deve ser informado.") String username,

        @NotBlank(message = "O campo password deve ser informado.") String password,

        @NotBlank(message = "O campo password deve ser informado.") String confirmPassword,

        Set<Role> roles) {
}