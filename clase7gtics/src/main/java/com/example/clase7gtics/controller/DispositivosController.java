package com.example.clase7gtics.controller;

import com.example.clase7gtics.entity.Dispositivos;
import com.example.clase7gtics.repository.DispositivosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dispositivos")
public class DispositivosController {

    private final DispositivosRepository dispositivosRepository;

    public DispositivosController(DispositivosRepository dispositivosRepository) {
        this.dispositivosRepository = dispositivosRepository;
    }

    @GetMapping("/list")
    public String listarDispositivos(Model model, @RequestParam(name = "role", required = false) String role) {
        model.addAttribute("listaDispositivos", dispositivosRepository.findAll());
        model.addAttribute("role", role);
        return "dispositivos/listDiapo";
    }

    @GetMapping("/edit/{id}")
    public String editarDispositivo(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("dispositivo", dispositivosRepository.findById(id).orElse(new Dispositivos()));
        return "dispositivos/formDiapo";
    }

    @GetMapping("/create")
    public String crearDispositivo(Model model) {
        model.addAttribute("dispositivo", new Dispositivos());
        return "dispositivos/formDiapo";
    }

    @PostMapping("/save")
    public String guardarDispositivo(@ModelAttribute Dispositivos dispositivo) {
        dispositivosRepository.save(dispositivo);
        return "redirect:/dispositivos/list?role=ADMIN";
    }

    @GetMapping("/delete/{id}")
    public String eliminarDispositivo(@PathVariable("id") Integer id) {
        dispositivosRepository.deleteById(id);
        return "redirect:/dispositivos/list?role=ADMIN";
    }
}
