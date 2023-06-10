package dev.cauesouza.model.user;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import dev.cauesouza.model.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends DefaultEntity {

    private String username;

    @Column
    private String password;

    private String imageName;

    @ElementCollection
    @Column(name = "role", length = 30)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
