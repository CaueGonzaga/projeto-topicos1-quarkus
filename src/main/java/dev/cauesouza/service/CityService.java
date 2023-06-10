package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.address.CityDTO;
import dev.cauesouza.dto.address.CityResponseDTO;

public interface CityService {

    List<CityResponseDTO> listAll();

    CityResponseDTO findById(Long id);

    List<CityResponseDTO> findByName(String name);

    CityResponseDTO persist(CityDTO receivedDTO);

    CityResponseDTO update(Long id, CityDTO receivedDTO);

    void deleteById(Long id);

    Long count();
}
