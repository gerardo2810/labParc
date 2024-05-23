package com.example.clase7gtics.repository;

import com.example.clase7gtics.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo,Integer> {
}
