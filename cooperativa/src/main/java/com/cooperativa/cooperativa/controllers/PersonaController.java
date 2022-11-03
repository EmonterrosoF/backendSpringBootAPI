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

import com.cooperativa.cooperativa.entities.Estatus;
import com.cooperativa.cooperativa.entities.Persona;
import com.cooperativa.cooperativa.repositories.IEstatusRepository;
import com.cooperativa.cooperativa.repositories.IPersonaRepository;

@RestController
@RequestMapping(value = "/api/persona")
public class PersonaController {

    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private IEstatusRepository estatusRepository;

    @GetMapping
    public ResponseEntity<Page<Persona>> getAllEstatus(Pageable pageable) {
        return ResponseEntity.ok(personaRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Persona> savePersona(@Valid @RequestBody Persona persona) {
        Optional<Estatus> estatusOptional = estatusRepository.findById(persona.getEstatus().getId());
        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        persona.setEstatus(estatusOptional.get());
        Persona personaSave = personaRepository.save(persona);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(personaSave.getId()).toUri();

        return ResponseEntity.created(uri).body(personaSave);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updateEstatus(@Valid @PathVariable Integer id, @RequestBody Persona persona) {
        Optional<Estatus> estatusOptional = estatusRepository.findById(persona.getEstatus().getId());

        if (!estatusOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (!personaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        persona.setEstatus(estatusOptional.get());
        persona.setId(personaOptional.get().getId());
        personaRepository.save(persona);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> deleteEstatus(@PathVariable Integer id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (!personaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        personaRepository.delete(personaOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getEstatus(@PathVariable Integer id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (!personaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(personaOptional.get());
    }

}
