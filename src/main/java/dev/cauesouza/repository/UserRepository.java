package dev.cauesouza.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import dev.cauesouza.model.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
    public List<UserEntity> findByName(String username) {
        if (username == null)
            return null;

        String value = "%" + username.toUpperCase() + "%";
        return list("UPPER(username) like ?1", value);
    }

    public UserEntity findByUsernameAndPassword(String username, String password) {
        if (username == null || password == null)
            return null;

        return find("username = ?1 AND password = ?2 ", username, password).firstResult();
    }

    public UserEntity findByLogin(String username) {
        if (username == null)
            return null;

        return find("username = ?1 ", username).firstResult();
    }
}
