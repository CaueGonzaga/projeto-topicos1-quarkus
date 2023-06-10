package dev.cauesouza.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(

        @NotBlank(message = "O campo descricao deve ser informado") String description,

        @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero.") double price,

        @NotBlank(message = "O campo cor deve ser informado") String color,

        @Min(value = 0, message = "O campo estoque deve ser informado") int stock,

        @NotNull(message = "O campo idCategory deve ser informado") Long idCategory) {
}
