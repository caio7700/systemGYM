package com.systemgym.systemgym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemgym.systemgym.entity.Aluno;

@Controller
@RequestMapping("admin/aluno")
public class AlunoController {

    @GetMapping
public String exibirFormulario(Aluno aluno, Model model) {
        model.addAttribute("msgm", "O servidor está ok e o Thymeleaf também!");
        return "admin/formAluno";
}
}