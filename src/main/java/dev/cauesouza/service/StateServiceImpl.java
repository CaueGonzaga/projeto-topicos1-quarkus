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

import dev.cauesouza.dto.address.StateDTO;
import dev.cauesouza.dto.address.StateResponseDTO;
import dev.cauesouza.model.address.State;
import dev.cauesouza.repository.StateRepository;

@ApplicationScoped
public class StateServiceImpl implements StateService {

    @Inject
    private StateRepository stateRepository;

    @Inject
    private Validator validator;

    @Override
    public List<StateResponseDTO> listAll() {
        List<State> list = stateRepository.listAll();

        return list.stream().map(
                s -> new StateResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    public StateResponseDTO findById(Long id) {
        State entity = stateRepository.findById(id);
        if (entity == null)
            throw new NotFoundException("State not found.");
        return new StateResponseDTO(entity);
    }

    @Override
    public List<StateResponseDTO> findByName(String name) {
        List<State> list = stateRepository.findByName(name);

        return list.stream().map(
                s -> new StateResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StateResponseDTO persist(StateDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        State entity = new State();
        entity.setName(receivedDTO.name());
        entity.setAcronym(receivedDTO.acronym());
        stateRepository.persist(entity);

        return new StateResponseDTO(entity);
    }

    @Override
    @Transactional
    public StateResponseDTO update(Long id, StateDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        State entity = stateRepository.findById(id);
        entity.setName(receivedDTO.name());
        entity.setAcronym(receivedDTO.acronym());

        return new StateResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stateRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return stateRepository.count();
    }

    private void validate(StateDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<StateDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
