package dev.cauesouza.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import dev.cauesouza.model.address.State;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class StateRepository implements PanacheRepository<State> {

    public List<State> findByName(String name) {
        if (name == null)
            return null;

        String value = "%" + name.toUpperCase() + "%";
        return list("UPPER(name) like ?1", value);
    }
}
