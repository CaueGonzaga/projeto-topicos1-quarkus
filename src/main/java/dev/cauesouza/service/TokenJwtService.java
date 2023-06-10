package dev.cauesouza.service;

import dev.cauesouza.model.user.UserEntity;

public interface TokenJwtService {
    public String generateJwt(UserEntity user);
}
