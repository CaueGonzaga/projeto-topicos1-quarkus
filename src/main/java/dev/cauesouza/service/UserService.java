package dev.cauesouza.service;

import java.util.List;

import dev.cauesouza.dto.user.UserDTO;
import dev.cauesouza.dto.user.UserResponseDTO;
import dev.cauesouza.model.user.UserEntity;

public interface UserService {

    List<UserResponseDTO> listAll();

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findByName(String name);

    UserResponseDTO persist(UserDTO receivedDTO);

    UserResponseDTO update(Long id, UserDTO receivedDTO);

    UserResponseDTO update(Long id, String nomeImagem);

    void deleteById(Long id);

    Long count();

    UserEntity findByUsernameAndPassword(String username, String hash);

    UserResponseDTO findByLogin(String login);

    UserResponseDTO updatePassword(Long id, String username);
}
