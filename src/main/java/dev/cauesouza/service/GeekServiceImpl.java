package dev.cauesouza.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dev.cauesouza.dto.product.GeekDTO;
import dev.cauesouza.dto.product.GeekResponseDTO;
import dev.cauesouza.exception.NotFoundEntityException;
import dev.cauesouza.model.product.Geek;
import dev.cauesouza.model.product.ProductStatus;
import dev.cauesouza.repository.CategoryRepository;
import dev.cauesouza.repository.GeekRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GeekServiceImpl implements GeekService {

    @Inject
    private GeekRepository geekRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private Validator validator;

    @Override
    public List<GeekResponseDTO> listAll() {
        List<GeekResponseDTO> list = geekRepository.findAll().stream().map(p -> new GeekResponseDTO(p))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public GeekResponseDTO persist(GeekDTO receivedDTO) {

        try {
            validate(receivedDTO);
            Geek entity = new Geek();
            entity.setDescription(receivedDTO.description());
            entity.setStock(receivedDTO.stock());
            entity.setPrice(receivedDTO.price());
            entity.setStatus(validateStatus(receivedDTO.status()));
            entity.setMaterial(receivedDTO.material());
            entity.setCategories(categoryRepository.findById(receivedDTO.idCategory()));
            geekRepository.persist(entity);

            return new GeekResponseDTO(entity);
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Dados inválidos", null);
        }
    }

    @Override
    public GeekResponseDTO update(Long id, GeekDTO receivedDTO) {

        try {
            validate(receivedDTO);
            Geek entity = geekRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            entity.setDescription(receivedDTO.description());
            entity.setStock(receivedDTO.stock());
            entity.setPrice(receivedDTO.price());
            entity.setStatus(validateStatus(receivedDTO.status()));
            entity.setMaterial(receivedDTO.material());
            entity.setCategories(categoryRepository.findById(receivedDTO.idCategory()));

            return new GeekResponseDTO(entity);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Geek não encontrado");
        }
    }

    @Override
    public void deleteById(Long id) throws ConstraintViolationException {
        try {
            geekRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            geekRepository.deleteById(id);

        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Geek nao encontrado");
        }

    }

    @Override
    public GeekResponseDTO findById(Long id) {
        try {
            Geek entity = geekRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            return new GeekResponseDTO(entity);

        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Geek nao encontrado");
        }
    }

    @Override
    public List<GeekResponseDTO> findByDescription(String description) {
        return geekRepository.findByName(description).stream().map(p -> new GeekResponseDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return geekRepository.count();
    }

    private void validate(GeekDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<GeekDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private ProductStatus validateStatus(String status) {
        if (status.toUpperCase().equals("AVAILABLE")) {
            return ProductStatus.AVAILABLE;
        } else if (status.toUpperCase().equals("UNAVAILABLE")) {
            return ProductStatus.UNAVAILABLE;
        } else {
            return null;
        }
    }
}