package com.systemgym.systemgym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.systemgym.systemgym.entity.Plano;
import com.systemgym.systemgym.repository.PlanoRepository;

@Controller
@RequestMapping("/admin/plano")
public class PlanoController {

    @Autowired
    private PlanoRepository planoRepository;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("plano", new Plano());
        return "admin/formPlano";
    }

    @PostMapping("/salvar")
    public String salvarPlano(Plano plano) {
        planoRepository.save(plano);
        return "redirect:/admin/plano/lista";
    }

    @GetMapping("/lista")
public String listarPlanos(Model model) {
    // Busca todos os planos no banco via Repository
    List<Plano> lista = planoRepository.findAll();
    model.addAttribute("planos", lista); // Passa a lista para o HTML
    return "admin/listaPlanos";
    }

    @GetMapping("/editar/{id}")
public String editarPlano(@PathVariable("id") Long id, Model model) {
    // Busca o plano no banco pelo ID
    Plano plano = planoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de plano inválido: " + id));
    
    model.addAttribute("plano", plano); // Passa o plano preenchido para o form
    return "admin/formPlano"; // Abre o formulário
}

@GetMapping("/excluir/{id}")
public String excluirPlano(@PathVariable("id") Long id, RedirectAttributes attributes) {
    try {
        planoRepository.deleteById(id);
        attributes.addFlashAttribute("mensagem", "Plano excluído com sucesso!");
    } catch (Exception e) {
        // Se houver alunos vinculados, cairemos aqui
        attributes.addFlashAttribute("erro", "Não é possível excluir: existem alunos vinculados a este plano.");
    }
    return "redirect:/admin/plano/lista";
}

}