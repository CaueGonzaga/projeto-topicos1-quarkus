package dev.cauesouza.model.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import dev.cauesouza.model.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state")
@NoArgsConstructor
@AllArgsConstructor
public class State extends DefaultEntity {

    private String name;

    private String acronym;

}
