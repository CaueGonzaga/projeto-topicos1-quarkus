package dev.cauesouza.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(
        @NotBlank(message = "O campo firstName deve ser informado.") String firstName,

        @NotBlank(message = "O campo lastName deve ser informado.") String lastName,

        @NotNull(message = "O campo age deve ser informado.") Integer age,

        @NotBlank(message = "O campo gender deve ser informado.") String gender,

        @NotNull(message = "O campo birthday deve ser informado.") 
        @NotEmpty(message = "O campo birthday deve ser informado.") 
        @NotBlank(message = "O campo birthday deve ser informado.") String birthday,

        Long idUser) {
}