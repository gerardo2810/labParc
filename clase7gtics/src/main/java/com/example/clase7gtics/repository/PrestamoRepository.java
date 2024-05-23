package com.example.clase7gtics.repository;

import com.example.clase7gtics.entity.Prestamo;
import com.example.clase7gtics.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Integer> {
    List<Prestamo> findByAlumnoAndFechaDevolucionIsNull(Usuario alumno);
    List<Prestamo> findByProfesorAndFechaDevolucionIsNull(Usuario profesor);
}
