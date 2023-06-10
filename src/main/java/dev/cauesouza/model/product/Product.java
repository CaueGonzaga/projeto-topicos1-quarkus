package dev.cauesouza.model.product;

import dev.cauesouza.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends DefaultEntity {

    private String description;

    private int stock;

    private Double price;

    @ManyToOne
    private Category categories;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
