package com.example.clase7gtics.controller;

import com.example.clase7gtics.repository.DispositivosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dispositivos")
public class DispositivosController {
    final DispositivosRepository dispositivosRepository;

    public DispositivosController(DispositivosRepository dispositivosRepository) {
        this.dispositivosRepository = dispositivosRepository;
    }

    @GetMapping("/list")
    public String listarDispositivos(Model model) {
        model.addAttribute("listaDiapos", dispositivosRepository.findAll());
        return "dispositivos/listDiapo";
    }
}
