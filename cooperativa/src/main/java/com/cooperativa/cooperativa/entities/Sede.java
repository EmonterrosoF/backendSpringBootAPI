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
@Table(name = "sedes")
@Getter
@Setter
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String  nombre;

    private String localidad;

     @OneToMany(mappedBy = "sede")
     private Set<Persona> personas = new HashSet<>();

     public void setSede(Set<Persona> personas) {
        this.personas = personas;
        for (Persona persona : personas) {
            persona.setSede(this);
        }
    }

}
