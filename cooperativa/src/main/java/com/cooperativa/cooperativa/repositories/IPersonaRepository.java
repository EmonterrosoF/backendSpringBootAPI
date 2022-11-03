package com.cooperativa.cooperativa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperativa.cooperativa.entities.Persona;

public interface IPersonaRepository extends JpaRepository<Persona,Integer> {
    
}
