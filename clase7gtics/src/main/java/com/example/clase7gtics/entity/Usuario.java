package com.example.clase7gtics.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String rol;

}
