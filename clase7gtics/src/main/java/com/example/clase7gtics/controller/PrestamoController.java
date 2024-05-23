package com.example.clase7gtics.controller;

import com.example.clase7gtics.entity.Prestamo;
import com.example.clase7gtics.entity.Usuario;
import com.example.clase7gtics.repository.PrestamoRepository;
import com.example.clase7gtics.repository.DispositivosRepository;
import com.example.clase7gtics.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/prestamos")
public class PrestamosController {

    private final PrestamoRepository prestamoRepository;
    private final DispositivosRepository dispositivosRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamosController(PrestamoRepository prestamoRepository, DispositivosRepository dispositivosRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.dispositivosRepository = dispositivosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/list")
    public String listarPrestamos(@RequestParam(name = "role", required = false) String role, @RequestParam(name = "userId") Long userId, Model model) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario != null) {
            if ("ALUMNO".equals(role)) {
                model.addAttribute("listaPrestamos", prestamoRepository.findByAlumnoAndFechaDevolucionIsNull(usuario));
            } else if ("PROFESOR".equals(role)) {
                model.addAttribute("listaPrestamos", prestamoRepository.findByProfesorAndFechaDevolucionIsNull(usuario));
            }
        }
        model.addAttribute("role", role);
        return "prestamos/listPrestamos";
    }

    @GetMapping("/create")
    public String crearPrestamo(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        model.addAttribute("dispositivos", dispositivosRepository.findAll());
        model.addAttribute("alumnos", usuarioRepository.findByRol("ALUMNO"));
        return "prestamos/formPrestamo";
    }

    @PostMapping("/save")
    public String guardarPrestamo(@ModelAttribute Prestamo prestamo, @RequestParam(name = "profesorId") Long profesorId) {
        prestamo.setProfesor(usuarioRepository.findById(profesorId).orElse(null));
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamoRepository.save(prestamo);
        return "";
    }
}