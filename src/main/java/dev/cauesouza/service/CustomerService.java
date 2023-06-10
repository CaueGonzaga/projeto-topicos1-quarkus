package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.customer.CustomerDTO;
import dev.cauesouza.dto.customer.CustomerResponseDTO;

public interface CustomerService {

    List<CustomerResponseDTO> listAll();

    CustomerResponseDTO findById(Long id);

    List<CustomerResponseDTO> findByName(String name);

    CustomerResponseDTO persist(CustomerDTO receivedDTO);

    CustomerResponseDTO update(Long id, CustomerDTO receivedDTO);

    void deleteById(Long id);

    Long count();
}
