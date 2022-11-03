package com.cooperativa.cooperativa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperativa.cooperativa.entities.TipoPersona;

public interface ITipoPersonaRepository extends JpaRepository<TipoPersona,Integer> {
    
}
