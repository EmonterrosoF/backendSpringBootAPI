package com.cooperativa.cooperativa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estatus")
@Getter
@Setter
public class Estatus {
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @NotNull
    private String estatus;


    @OneToMany(mappedBy = "estatus", cascade = CascadeType.ALL)
    private Set<Persona> personas = new HashSet<>();

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
        for (Persona persona : personas) {
            persona.setEstatus(this);
        }
    }

 
}
