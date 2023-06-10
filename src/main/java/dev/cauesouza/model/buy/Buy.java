package dev.cauesouza.model.buy;

import java.util.List;

import dev.cauesouza.model.DefaultEntity;
import dev.cauesouza.model.address.Address;
import dev.cauesouza.model.customer.Customer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "buy")
public class Buy extends DefaultEntity {

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "buy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuyGeek> geeks;

}
