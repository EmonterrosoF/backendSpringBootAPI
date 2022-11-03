package com.cooperativa.cooperativa.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cooperativa.cooperativa.entities.Sede;
import com.cooperativa.cooperativa.repositories.ISedeRepository;

@RestController
@RequestMapping(value = "/api/sede")
public class SedeController {

    @Autowired
    private ISedeRepository estatusRepository;

    @GetMapping
    public ResponseEntity<Page<Sede>> getAllEstatus(Pageable pageable) {
        return ResponseEntity.ok(estatusRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Sede> saveEstatus(@Valid @RequestBody Sede sede) {
        Sede estatusSave = estatusRepository.save(sede);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(estatusSave.getId()).toUri();

        return ResponseEntity.created(uri).body(estatusSave);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> updateEstatus(@Valid @PathVariable Integer id, @RequestBody Sede sede) {
        Optional<Sede> estatusOptional = estatusRepository.findById(id);

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        sede.setId(estatusOptional.get().getId());
        estatusRepository.save(sede);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sede> deleteEstatus(@PathVariable Integer id) {
        Optional<Sede> estatusOptional = estatusRepository.findById(id);

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        estatusRepository.delete(estatusOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getEstatus(@PathVariable Integer id) {
        Optional<Sede> estatusOptional = estatusRepository.findById(id);

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(estatusOptional.get());
    }

}
