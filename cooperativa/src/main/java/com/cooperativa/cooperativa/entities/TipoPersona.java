package com.cooperativa.cooperativa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipos_personas")
@Getter
@Setter
public class TipoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String tipo;

    @OneToMany(mappedBy = "tipoPersona")
    private Set<Persona> personas = new HashSet<>();

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
        for (Persona persona : personas) {
            persona.setTipoPersona(this);
        }
    }
}
 