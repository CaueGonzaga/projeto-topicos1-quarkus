package dev.cauesouza.service;

import java.util.List;
import java.util.stream.Collectors;

import dev.cauesouza.dto.product.ProductDTO;
import dev.cauesouza.dto.product.ProductResponseDTO;
import dev.cauesouza.exception.NotFoundEntityException;
import dev.cauesouza.model.product.Product;
import dev.cauesouza.repository.CategoryRepository;
import dev.cauesouza.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> listAll() {
        List<ProductResponseDTO> list = productRepository.findAll().stream().map(p -> new ProductResponseDTO(p))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public ProductResponseDTO persist(ProductDTO receivedDTO) {

        try {
            Product entity = new Product();
            entity.setDescription(receivedDTO.description());
            entity.setPrice(receivedDTO.price());
            entity.setCategories(categoryRepository.findById(receivedDTO.idCategory()));
            entity.setStock(receivedDTO.stock());
            productRepository.persist(entity);

            return new ProductResponseDTO(entity);
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Dados inválidos", null);
        }
    }

    @Override
    public ProductResponseDTO update(Long id, ProductDTO receivedDTO) {

        try {
            Product entity = productRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            entity.setDescription(receivedDTO.description());
            entity.setPrice(receivedDTO.price());
            entity.setCategories(categoryRepository.findById(receivedDTO.idCategory()));
            entity.setStock(receivedDTO.stock());

            return new ProductResponseDTO(entity);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Product não encontrado");
        }
    }

    @Override
    public void deleteById(Long id) throws ConstraintViolationException {
        try {
            productRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            productRepository.deleteById(id);

        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Product nao encontrado");
        }

    }

    @Override
    public ProductResponseDTO findById(Long id) {
        try {
            Product entity = productRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            return new ProductResponseDTO(entity);

        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Product nao encontrado");
        }
    }

    @Override
    public List<ProductResponseDTO> findByDescription(String descricao) {
        return productRepository.buscarPorDescricao(descricao).stream().map(p -> new ProductResponseDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return productRepository.count();
    }
}