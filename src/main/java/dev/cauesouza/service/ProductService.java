package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.product.ProductDTO;
import dev.cauesouza.dto.product.ProductResponseDTO;

public interface ProductService {
 
    List<ProductResponseDTO> listAll();

    ProductResponseDTO findById(Long id);

    List<ProductResponseDTO> findByDescription(String description);

    ProductResponseDTO persist(ProductDTO receivedDTO);
    
    ProductResponseDTO update(Long id, ProductDTO receivedDTO);
    
    void deleteById(Long id);
    
    Long count();
}