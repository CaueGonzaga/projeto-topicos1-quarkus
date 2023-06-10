package dev.cauesouza.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {
    ADMIN(1, "Admin"),
    USER(2, "User");

    private int id;
    private String label;

    Role(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Role valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Role role : Role.values()) {
            if (id.equals(role.getId()))
                return role;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}