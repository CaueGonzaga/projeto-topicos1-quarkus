package dev.cauesouza.dto.user;

import java.util.Set;

import dev.cauesouza.model.user.Role;
import dev.cauesouza.model.user.UserEntity;

public record UserResponseDTO(
        Long id,

        String username,

        String imageName,

        Set<Role> role

) {
    public UserResponseDTO(UserEntity entity) {
        this(entity.getId(), entity.getUsername(), entity.getImageName(), entity.getRoles());
    }

    public static UserResponseDTO valueOf(UserEntity u) {
        if (u.getRoles() == null)
            return new UserResponseDTO(u.getId(), u.getUsername(), null, null);

        return new UserResponseDTO(
                u.getId(),
                u.getUsername(),
                u.getImageName(),
                u.getRoles());
    }
}
