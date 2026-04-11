package com.systemgym.systemgym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemgym.systemgym.entity.Funcionario;
import com.systemgym.systemgym.repository.FuncionarioRepository;

@Controller
@RequestMapping("/admin/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "admin/formFuncionario";
    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return "redirect:/admin/funcionario/lista";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "admin/listaFuncionarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
    Funcionario func = funcionarioRepository.findById(id).orElseThrow();
    model.addAttribute("funcionario", func);
    return "admin/formFuncionario";
}
}
    

