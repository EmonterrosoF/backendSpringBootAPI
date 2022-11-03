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

import com.cooperativa.cooperativa.entities.TipoPersona;
import com.cooperativa.cooperativa.repositories.ITipoPersonaRepository;

@RestController
@RequestMapping(value = "/api/tipo")
public class TipoPersonaController {

    @Autowired
    private ITipoPersonaRepository estatusRepository;

    @GetMapping
    public ResponseEntity<Page<TipoPersona>> getAllEstatus(Pageable pageable) {
        return ResponseEntity.ok(estatusRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<TipoPersona> saveEstatus(@Valid @RequestBody TipoPersona estatus) {
        TipoPersona estatusSave = estatusRepository.save(estatus);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(estatusSave.getId()).toUri();

        return ResponseEntity.created(uri).body(estatusSave);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPersona> updateEstatus(@Valid @PathVariable Integer id, @RequestBody TipoPersona estatus) {
        Optional<TipoPersona> estatusOptional = estatusRepository.findById(id);

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        estatus.setId(estatusOptional.get().getId());
        estatusRepository.save(estatus);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoPersona> deleteEstatus(@PathVariable Integer id) {
        Optional<TipoPersona> estatusOptional = estatusRepository.findById(id);

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        estatusRepository.delete(estatusOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPersona> getEstatus(@PathVariable Integer id) {
        Optional<TipoPersona> estatusOptional = estatusRepository.findById(id);

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(estatusOptional.get());
    }

}
