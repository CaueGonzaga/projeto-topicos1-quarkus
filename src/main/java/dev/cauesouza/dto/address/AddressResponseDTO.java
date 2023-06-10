package dev.cauesouza.dto.address;

import dev.cauesouza.model.address.Address;

public record AddressResponseDTO(
        Long id,

        String address,

        String complement,

        String city,

        String customer) {
    public AddressResponseDTO(Address entity) {
        this(
            entity.getId(),
            entity.getAddress(),
            entity.getComplement(),
            entity.getCity().getName(),
            entity.getCustomer().getFirstName());
    }
}
