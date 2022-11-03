package com.cooperativa.cooperativa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperativa.cooperativa.entities.Sede;

public interface ISedeRepository  extends JpaRepository<Sede,Integer> {
    
}
