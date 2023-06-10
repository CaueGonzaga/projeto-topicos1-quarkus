package dev.cauesouza.model.address;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import dev.cauesouza.model.DefaultEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City extends DefaultEntity {

    private String name;

    @ManyToOne
    private State state;
}
