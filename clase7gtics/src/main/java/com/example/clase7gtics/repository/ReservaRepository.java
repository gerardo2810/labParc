package com.example.clase7gtics.repository;

import com.example.clase7gtics.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Integer> {
}
