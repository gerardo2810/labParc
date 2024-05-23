package com.example.clase7gtics.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "Reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Usuario alumno;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private Dispositivos dispositivo;

    @Column(nullable = false)
    private Date fechaInicio;

    @Column(nullable = false)
    private Date fechaFin;
}
