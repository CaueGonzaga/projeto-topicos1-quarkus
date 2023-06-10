package dev.cauesouza.dto.address;

import dev.cauesouza.model.address.State;

public record StateResponseDTO(
        Long id,

        String name,

        String acronym) {
    public StateResponseDTO(State entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getAcronym());
    }
}
