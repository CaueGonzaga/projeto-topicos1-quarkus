package dev.cauesouza.model.buy;

import dev.cauesouza.model.DefaultEntity;
import dev.cauesouza.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordered_item")
public class OrderedItem extends DefaultEntity {

    @ManyToOne
    private Product product;

    private int amount;

    private double price;
}
