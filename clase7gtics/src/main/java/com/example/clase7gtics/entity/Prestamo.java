package com.example.clase7gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private Dispositivos dispositivo;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Usuario alumno;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Usuario profesor;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = true)
    private LocalDate fechaDevolucion;
}
