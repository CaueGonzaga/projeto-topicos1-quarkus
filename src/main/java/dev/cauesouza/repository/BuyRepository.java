package dev.cauesouza.repository;

import java.util.List;

import dev.cauesouza.model.buy.Buy;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BuyRepository implements PanacheRepository<Buy> {

    public List<Buy> findByName(String name) {
        if (name == null)
            return null;

        String value = "%" + name.toUpperCase() + "%";
        return list("UPPER(description) like ?1", value);
    }

}
