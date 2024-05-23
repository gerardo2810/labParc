package com.example.clase7gtics.repository;

import com.example.clase7gtics.entity.Dispositivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivosRepository extends JpaRepository<Dispositivos, Integer> {

}
