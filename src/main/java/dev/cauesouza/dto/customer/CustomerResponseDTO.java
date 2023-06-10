package dev.cauesouza.dto.customer;

import dev.cauesouza.model.customer.Customer;

public record CustomerResponseDTO(

        Long id,
        String firstName,
        String lastName,
        Integer age,
        String gender,
        String birthday,
        String email) {
    public CustomerResponseDTO(Customer entity) {
        this(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getAge(),
            entity.getGender(),
            entity.getBirthday().toString(),
            entity.getUser().getUsername());
    }
}
