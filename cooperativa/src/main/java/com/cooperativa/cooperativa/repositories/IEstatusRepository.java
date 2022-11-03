package com.cooperativa.cooperativa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperativa.cooperativa.entities.Estatus;

public interface IEstatusRepository extends JpaRepository<Estatus,Integer> {
    
}
