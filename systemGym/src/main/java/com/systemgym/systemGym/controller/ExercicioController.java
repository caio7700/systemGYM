package com.systemgym.systemgym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemgym.systemgym.entity.Exercicio;
import com.systemgym.systemgym.repository.ExercicioRepository;

@Controller
@RequestMapping("/admin/exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioRepository repository;

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("exercicios", repository.findAll());
        return "admin/listaExercicios";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("exercicio", new Exercicio());
        return "admin/formExercicio";
    }

    @PostMapping("/salvar")
    public String salvar(Exercicio exercicio) {
        repository.save(exercicio);
        return "redirect:/admin/exercicio/lista";
    }

}
