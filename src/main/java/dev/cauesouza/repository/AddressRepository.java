package dev.cauesouza.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import dev.cauesouza.model.address.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public List<Address> findByName(String name) {
        if (name == null)
            return null;

        String value = "%" + name.toUpperCase() + "%";
        return list("UPPER(address) like ?1", value);
    }
}
