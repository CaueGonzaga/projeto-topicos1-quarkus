package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.product.GeekDTO;
import dev.cauesouza.dto.product.GeekResponseDTO;

public interface GeekService {

    List<GeekResponseDTO> listAll();

    GeekResponseDTO findById(Long id);

    List<GeekResponseDTO> findByDescription(String description);

    GeekResponseDTO persist(GeekDTO receivedDTO);

    GeekResponseDTO update(Long id, GeekDTO receivedDTO);

    void deleteById(Long id);

    Long count();
}
