package com.example.service;

import com.example.model.Persona;
import com.example.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona crearPersona(@Valid Persona persona) {
        return personaRepository.save(persona);
    }

    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id);
    }

    public Persona actualizarPersona(Long id, Persona personaDetalles) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + id));

        persona.setNombrePersona(personaDetalles.getNombrePersona());
        persona.setEdadPersona(personaDetalles.getEdadPersona());
        persona.setTelefonoPersona(personaDetalles.getTelefonoPersona());
        persona.setSexoPersona(personaDetalles.getSexoPersona());
        persona.setIdOcupacion(personaDetalles.getIdOcupacion());
        persona.setFechaNac(personaDetalles.getFechaNac());

        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
}