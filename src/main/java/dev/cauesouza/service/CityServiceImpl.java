package dev.cauesouza.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import dev.cauesouza.dto.address.CityDTO;
import dev.cauesouza.dto.address.CityResponseDTO;
import dev.cauesouza.model.address.City;
import dev.cauesouza.repository.CityRepository;
import dev.cauesouza.repository.StateRepository;

@ApplicationScoped
public class CityServiceImpl implements CityService {

    @Inject
    private CityRepository cityRepository;

    @Inject
    private StateRepository stateRepository;

    @Inject
    private Validator validator;

    @Override
    public List<CityResponseDTO> listAll() {
        List<City> list = cityRepository.listAll();

        return list.stream().map(
                s -> new CityResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    public CityResponseDTO findById(Long id) {
        City entity = cityRepository.findById(id);
        if (entity == null)
            throw new NotFoundException("State not found.");
        return new CityResponseDTO(entity);
    }

    @Override
    public List<CityResponseDTO> findByName(String name) {
        List<City> list = cityRepository.findByName(name);

        return list.stream().map(
                s -> new CityResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CityResponseDTO persist(CityDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        City entity = new City();
        entity.setName(receivedDTO.name());
        entity.setState(stateRepository.findById(receivedDTO.idState()));
        cityRepository.persist(entity);

        return new CityResponseDTO(entity);
    }

    @Override
    @Transactional
    public CityResponseDTO update(Long id, CityDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        City entity = cityRepository.findById(id);
        entity.setName(receivedDTO.name());
        entity.setState(stateRepository.findById(receivedDTO.idState()));

        return new CityResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return cityRepository.count();
    }

    private void validate(CityDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<CityDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
