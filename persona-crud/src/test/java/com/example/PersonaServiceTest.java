package com.example;


import com.example.model.Persona;
import com.example.repository.PersonaRepository;
import com.example.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonaServiceTest {

    @InjectMocks
    private PersonaService personaService;

    @Mock
    private PersonaRepository personaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearPersona() {
        Persona persona = new Persona("Maria", 30, "98765432", "Femenino", 2L, LocalDate.now());
        when(personaRepository.save(any(Persona.class))).thenReturn(persona);

        Persona resultado = personaService.crearPersona(persona);
        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNombrePersona());
        verify(personaRepository, times(1)).save(persona);
    }

    @Test
    void testObtenerTodasLasPersonas() {
        Persona persona1 = new Persona("Maria", 30, "98765432", "Femenino", 2L, LocalDate.now());
        Persona persona2 = new Persona("Juan", 25, "77558899", "Masculino", 1L, LocalDate.now());
        List<Persona> personas = new ArrayList<>(List.of(persona1, persona2));

        when(personaRepository.findAll()).thenReturn(personas);

        List<Persona> resultado = personaService.obtenerTodasLasPersonas();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(personaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPersonaPorId() {
        Persona persona = new Persona("Maria", 30, "98765432", "Femenino", 2L, LocalDate.now());
        when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));

        Optional<Persona> resultado = personaService.obtenerPersonaPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Maria", resultado.get().getNombrePersona());
        verify(personaRepository, times(1)).findById(1L);
    }

    @Test
    void testActualizarPersona() {
        Persona personaExistente = new Persona("Maria", 30, "98765432", "Femenino", 2L, LocalDate.now());
        Persona personaActualizada = new Persona("Maria Lopez", 31, "98765432", "Femenino", 2L, LocalDate.now());

        when(personaRepository.findById(1L)).thenReturn(Optional.of(personaExistente));
        when(personaRepository.save(any(Persona.class))).thenReturn(personaActualizada);

        Persona resultado = personaService.actualizarPersona(1L, personaActualizada);
        assertNotNull(resultado);
        assertEquals("Maria Lopez", resultado.getNombrePersona());
        assertEquals(31, resultado.getEdadPersona());
        verify(personaRepository, times(1)).findById(1L);
        verify(personaRepository, times(1)).save(any(Persona.class));
    }

    @Test
    void testEliminarPersona() {
        Persona persona = new Persona("Maria", 30, "98765432", "Femenino", 2L, LocalDate.now());
        when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));

        personaService.eliminarPersona(1L);
        verify(personaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarPersonaNoEncontrada() {
        when(personaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> personaService.eliminarPersona(1L));
        verify(personaRepository, times(1)).findById(1L);
        verify(personaRepository, never()).deleteById(anyLong());
    }
}