package dev.cauesouza.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CityDTO(
        @NotBlank(message = "O campo name deve ser informado.") String name,

        @NotNull(message = "O campo idState deve ser informado.") Long idState) {
}