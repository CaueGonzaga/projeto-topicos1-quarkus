package dev.cauesouza.model.address;

import dev.cauesouza.model.DefaultEntity;
import dev.cauesouza.model.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address extends DefaultEntity {

    private String address;

    private String complement;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
