package dev.cauesouza.repository;

import java.util.List;

import dev.cauesouza.model.product.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public List<Product> buscarPorDescricao(String description) {
        String str = "%" + description.toUpperCase() + "%";
        return find("UPPER(description) like ?1", str).list();
    }
}
