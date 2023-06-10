package dev.cauesouza.dto.order;

import java.util.List;
import java.util.stream.Collectors;

import dev.cauesouza.model.buy.OrderEntity;

public record OrderResponseDTO(
        Long id,
        String customer,
        List<String> orderedItens,
        int amount,
        double totalPrice,
        String status) {
    public OrderResponseDTO(OrderEntity o) {
        this(
            o.getId(),
            o.getCustomer().getFirstName(),
            o.getOrderedItems().stream().map(i -> i.getProduct().getDescription()).collect(Collectors.toList()),
            o.getAmount(),
            o.getTotalPriceOrder(),
            o.getStatus());
    }
}
