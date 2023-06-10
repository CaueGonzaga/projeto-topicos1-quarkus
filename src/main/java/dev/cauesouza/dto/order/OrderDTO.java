package dev.cauesouza.dto.order;

import java.util.List;

public record OrderDTO(
        Long idCustomer,
        List<Long> idsOrderedItens,
        int amount,
        double totalPriceOrder,
        String status) {
}
