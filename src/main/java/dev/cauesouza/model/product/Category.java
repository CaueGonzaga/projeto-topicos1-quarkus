package dev.cauesouza.model.product;

import dev.cauesouza.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Category extends DefaultEntity {

    @Column(nullable = false)
    private String description;

}
