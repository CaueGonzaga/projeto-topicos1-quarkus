package dev.cauesouza.repository;

import java.util.List;

import dev.cauesouza.model.product.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
    public List<Category> findByName(String name) {
        String likeName = "%" + name.toUpperCase() + "%";

        return list("UPPER(name) like ?1", likeName);
    }

    public List<Category> listAllOrdenated() {
        return find("order by id").list();
    }
}