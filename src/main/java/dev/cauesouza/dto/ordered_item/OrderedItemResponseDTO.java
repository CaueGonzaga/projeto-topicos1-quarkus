package dev.cauesouza.dto.ordered_item;

import dev.cauesouza.model.buy.OrderedItem;

public record OrderedItemResponseDTO(
        Long id,
        String product,
        int amount,
        double price) {
    public OrderedItemResponseDTO(OrderedItem i) {
        this(
            i.getId(),
            i.getProduct().getDescription(),
            i.getAmount(),
            i.getPrice());
    }
}
