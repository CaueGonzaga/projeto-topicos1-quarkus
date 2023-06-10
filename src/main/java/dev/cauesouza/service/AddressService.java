package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.address.AddressDTO;
import dev.cauesouza.dto.address.AddressResponseDTO;

public interface AddressService {

    List<AddressResponseDTO> listAll();

    AddressResponseDTO findById(Long id);

    List<AddressResponseDTO> findByStreet(String name);

    AddressResponseDTO persist(AddressDTO receivedDTO);

    AddressResponseDTO update(Long id, AddressDTO receivedDTO);

    void deleteById(Long id);

    Long count();
}
