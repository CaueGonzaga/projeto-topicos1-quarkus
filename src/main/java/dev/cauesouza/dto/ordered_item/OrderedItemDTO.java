package dev.cauesouza.dto.ordered_item;

import jakarta.validation.constraints.Min;

public record OrderedItemDTO(

        Long idProduct,

        @Min(value = 0, message = "O campo quantidade deve ser informado") int amount,

        double price) {
}
