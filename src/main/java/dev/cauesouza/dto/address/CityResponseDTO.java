package dev.cauesouza.dto.address;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import dev.cauesouza.model.address.City;

public record CityResponseDTO(
        Long id,

        String name,

        String state,

        @JsonInclude(JsonInclude.Include.NON_NULL) LocalDateTime createdAt,

        @JsonInclude(JsonInclude.Include.NON_NULL) LocalDateTime updatedAt) {
    public CityResponseDTO(City entity) {
        this(
            entity.getId(), 
            entity.getName(), 
            entity.getState().getName(), 
            entity.getCreatedAt(),
            entity.getUpdatedAt());
    }
}
