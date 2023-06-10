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

import dev.cauesouza.dto.user.UserDTO;
import dev.cauesouza.dto.user.UserResponseDTO;
import dev.cauesouza.model.user.UserEntity;
import dev.cauesouza.repository.UserRepository;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    HashServiceImpl hashService;

    @Inject
    private Validator validator;

    @Override
    public List<UserResponseDTO> listAll() {
        List<UserEntity> list = userRepository.listAll();

        return list.stream().map(
                s -> new UserResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        UserEntity entity = userRepository.findById(id);
        if (entity == null)
            throw new NotFoundException("State not found.");
        return new UserResponseDTO(entity);
    }

    @Override
    public List<UserResponseDTO> findByName(String name) {
        List<UserEntity> list = userRepository.findByName(name);

        return list.stream().map(
                s -> new UserResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO persist(UserDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        UserEntity entity = new UserEntity();
        entity.setUsername(receivedDTO.username());
        if (receivedDTO.password().equals(receivedDTO.confirmPassword())) {
            entity.setPassword(hashService.getHashSenha(receivedDTO.confirmPassword()));
        }
        entity.setRoles(receivedDTO.roles());
        userRepository.persist(entity);

        return new UserResponseDTO(entity);
    }

    @Override
    @Transactional
    public UserResponseDTO update(Long id, UserDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        UserEntity entity = userRepository.findById(id);

        entity.setUsername(receivedDTO.username());
        if (receivedDTO.password().equals(receivedDTO.confirmPassword())) {
            entity.setPassword(hashService.getHashSenha(receivedDTO.confirmPassword()));
        }
        entity.setPassword(receivedDTO.password());

        entity.setRoles(receivedDTO.roles());
        return new UserResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    private void validate(UserDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String hash) {
        return userRepository.findByUsernameAndPassword(username, hash);
    }

    @Override
    public UserResponseDTO findByLogin(String username) {
        UserEntity user = userRepository.findByLogin(username);
        if (user == null)
            throw new NotFoundException("Usuário não encontrado.");
        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO updatePassword(Long id, String username) {
        UserEntity user = userRepository.findByLogin(username);

        return update(id, new UserDTO(user.getUsername(), user.getPassword(), user.getPassword(), user.getRoles()));
    }

    @Override
    @Transactional
    public UserResponseDTO update(Long id, String imageName) {

        UserEntity entity = userRepository.findById(id);

        entity.setImageName(imageName);

        return UserResponseDTO.valueOf(entity);
    }
}
