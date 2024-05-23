package com.example.clase7gtics.repository;

import com.example.clase7gtics.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
    List<Usuario> findByRol(String rol);

}
