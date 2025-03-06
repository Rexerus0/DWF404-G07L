package com.example.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "persona")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @NotNull
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    @Column(name = "nombre_persona", nullable = false)
    private String nombrePersona;

    @NotNull
    @Column(name = "edad_persona", nullable = false)
    private Integer edadPersona;

    @NotNull
    @Size(min = 9, max = 9, message = "El teléfono debe tener exactamente 9 dígitos")
    @Column(name = "telefono_persona", nullable = false)
    private String telefonoPersona;

    @NotNull
    @Size(min = 1, max = 50, message = "El sexo debe tener entre 1 y 50 caracteres")
    @Column(name = "sexo_persona", nullable = false)
    private String sexoPersona;

    @NotNull
    @Column(name = "id_ocupacion", nullable = false)
    private Long idOcupacion;

    @NotNull
    @Column(name = "fecha_nac", nullable = false)
    private LocalDate fechaNac;

    public Persona() {
    }

    public Persona(String nombrePersona, Integer edadPersona, String telefonoPersona, String sexoPersona, Long idOcupacion, LocalDate fechaNac) {
        this.nombrePersona = nombrePersona;
        this.edadPersona = edadPersona;
        this.telefonoPersona = telefonoPersona;
        this.sexoPersona = sexoPersona;
        this.idOcupacion = idOcupacion;
        this.fechaNac = fechaNac;
    }
}