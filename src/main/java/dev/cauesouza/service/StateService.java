package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.address.StateDTO;
import dev.cauesouza.dto.address.StateResponseDTO;

public interface StateService {

    List<StateResponseDTO> listAll();

    StateResponseDTO findById(Long id);

    List<StateResponseDTO> findByName(String name);

    StateResponseDTO persist(StateDTO receivedDTO);

    StateResponseDTO update(Long id, StateDTO receivedDTO);

    void deleteById(Long id);

    Long count();
}
