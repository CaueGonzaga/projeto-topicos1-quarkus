package dev.cauesouza.model.buy;

import dev.cauesouza.model.DefaultEntity;
import dev.cauesouza.model.product.Geek;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "buy_geek")
public class BuyGeek extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "geek_id")
    private Geek geek;

    @ManyToOne
    @JoinColumn(name = "buy_id")
    private Buy buy;

    private int amount;
}
