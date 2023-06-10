package dev.cauesouza.repository;

import java.util.List;

import dev.cauesouza.model.product.Geek;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeekRepository implements PanacheRepository<Geek> {

    public List<Geek> findByName(String name) {
        if (name == null)
            return null;

        String value = "%" + name.toUpperCase() + "%";
        return list("UPPER(description) like ?1", value);
    }

}
