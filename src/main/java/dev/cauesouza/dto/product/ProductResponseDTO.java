package dev.cauesouza.dto.product;

import dev.cauesouza.model.product.Product;

public record ProductResponseDTO(
        Long id,
        String description,
        double price,
        String category,
        int stock) {
    public ProductResponseDTO(Product p) {
        this(
            p.getId(),
            p.getDescription(),
            p.getPrice(),
            p.getCategories().getDescription(),
            p.getStock());
    }
}
