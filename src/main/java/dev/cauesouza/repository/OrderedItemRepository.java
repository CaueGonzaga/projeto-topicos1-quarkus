package dev.cauesouza.repository;

import dev.cauesouza.model.buy.OrderedItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderedItemRepository implements PanacheRepository<OrderedItem> {
}
