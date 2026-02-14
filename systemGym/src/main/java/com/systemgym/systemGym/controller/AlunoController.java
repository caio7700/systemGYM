package com.systemgym.systemgym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/aluno")
public class AlunoController {

    @GetMapping
    public String teste() {
        return "Parabéns! A rota /admin/aluno está funcionando e o servidor está ok.";
    }
}