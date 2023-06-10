package dev.cauesouza.model.customer;

import dev.cauesouza.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Phone extends DefaultEntity {

    @Column(nullable = false, length = 3)
    private String ddd;

    @Column(nullable = false, length = 15)
    private String number;

    @Column(name = "is_whatsapp", nullable = false)
    private Boolean isWhatsapp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}