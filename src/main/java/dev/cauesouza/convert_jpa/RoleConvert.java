package dev.cauesouza.convert_jpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import dev.cauesouza.model.user.Role;

@Converter(autoApply = true)
public class RoleConvert implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role == null ? null : role.getLabel();
    }

    @Override
    public Role convertToEntityAttribute(String label) {
        return label == null ? null : Role.valueOf(label.toUpperCase());
    }

}
