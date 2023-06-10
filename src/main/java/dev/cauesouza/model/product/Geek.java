package dev.cauesouza.model.product;

import java.util.List;

import dev.cauesouza.model.buy.BuyGeek;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Geek extends Product {

    private String material;

    @OneToMany(mappedBy = "geek", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuyGeek> geeks;
}
