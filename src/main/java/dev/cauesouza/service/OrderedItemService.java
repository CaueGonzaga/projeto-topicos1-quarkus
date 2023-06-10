package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.ordered_item.OrderedItemDTO;
import dev.cauesouza.dto.ordered_item.OrderedItemResponseDTO;

public interface OrderedItemService {

    List<OrderedItemResponseDTO> listAll();

    OrderedItemResponseDTO findById(Long id);

    OrderedItemResponseDTO persist(OrderedItemDTO receivedDTO);

    OrderedItemResponseDTO update(Long id, OrderedItemDTO receivedDTO);

    void deleteById(Long id);

    Long count();

}