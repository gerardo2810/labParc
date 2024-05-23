package com.example.clase7gtics.entity;
import com.example.clase7gtics.entity.Dispositivos;
import com.example.clase7gtics.entity.Usuario;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Usuario profesor;

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
