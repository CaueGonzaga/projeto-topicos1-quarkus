package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.order.OrderDTO;
import dev.cauesouza.dto.order.OrderResponseDTO;

public interface OrderService {

    List<OrderResponseDTO> listAll();

    OrderResponseDTO findById(Long id);

    OrderResponseDTO persist(OrderDTO receivedDTO);

    OrderResponseDTO update(Long id, OrderDTO receivedDTO);

    void deleteById(Long id);

    Long count();
}