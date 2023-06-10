package dev.cauesouza.dto.product;

import dev.cauesouza.model.product.Geek;

public record GeekResponseDTO(
        Long id,

        String description,

        int stock,

        Double price,

        String status,

        String material,

        String category) {
    public GeekResponseDTO(Geek geek) {
        this(
            geek.getId(),
            geek.getDescription(),
            geek.getStock(),
            geek.getPrice(),
            geek.getStatus().name(),
            geek.getMaterial(),
            geek.getCategories().getDescription());
    }
}
