package dev.cauesouza.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import dev.cauesouza.model.customer.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public List<Customer> findByName(String username) {
        if (username == null)
            return null;

        String value = "%" + username.toUpperCase() + "%";
        return list("UPPER(first_name) like ?1", value);
    }
}
