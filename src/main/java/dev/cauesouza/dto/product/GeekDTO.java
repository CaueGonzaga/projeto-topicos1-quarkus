package dev.cauesouza.dto.product;

import dev.cauesouza.model.product.Geek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GeekDTO(
        @NotBlank(message = "O campo description deve ser informado.") String description,

        @NotNull(message = "O campo stock deve ser informado.") int stock,

        @NotNull(message = "O campo price deve ser informado.") Double price,

        @NotBlank(message = "O campo status deve ser informado.") String status,

        @NotBlank(message = "O campo material deve ser informado.") String material,

        Long idCategory) {
    public GeekDTO(Geek geek) {
        this(
            geek.getDescription(),
            geek.getStock(),
            geek.getPrice(),
            geek.getStatus().name(),
            geek.getMaterial(),
            geek.getCategories().getId());
    }
}