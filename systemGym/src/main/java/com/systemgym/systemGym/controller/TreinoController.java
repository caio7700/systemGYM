package com.systemgym.systemgym.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.systemgym.systemgym.entity.Aluno;
import com.systemgym.systemgym.entity.Treino;
import com.systemgym.systemgym.repository.AlunoRepository;
import com.systemgym.systemgym.repository.ExercicioRepository;
import com.systemgym.systemgym.repository.TreinoRepository;
import com.systemgym.systemgym.service.PdfService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/treino")
public class TreinoController {

    @Autowired private TreinoRepository treinoRepository;
    @Autowired private AlunoRepository alunoRepository;
    @Autowired private ExercicioRepository exercicioRepository;

    @GetMapping("/aluno/{id}")
    public String fichaAluno(@PathVariable Long id, Model model) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow();
        model.addAttribute("aluno", aluno);
        model.addAttribute("treinos", treinoRepository.findByAlunoId(id));
        
        // Objeto vazio para o formulário de adição
        Treino novoTreino = new Treino();
        novoTreino.setAluno(aluno);
        model.addAttribute("novoTreino", novoTreino);
        
        // Lista de exercícios para o Select
        model.addAttribute("listaExercicios", exercicioRepository.findAll());
        
        return "admin/fichaTreino";
    }

    @PostMapping("/salvar")
    public String salvar(Treino treino) {
        treinoRepository.save(treino);
        return "redirect:/admin/treino/aluno/" + treino.getAluno().getId();
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, @RequestParam Long alunoId) {
    treinoRepository.deleteById(id);
    return "redirect:/admin/treino/aluno/" + alunoId;
    }

    @GetMapping("/aluno/{id}/imprimir")
    public void imprimirFicha(@PathVariable Long id, HttpServletResponse response) throws IOException {
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=ficha_treino.pdf");

    Aluno aluno = alunoRepository.findById(id).orElseThrow();
    List<Treino> treinos = treinoRepository.findByAlunoId(id);

    PdfService pdfService = new PdfService();
    pdfService.gerarFichaTreino(response, aluno, treinos);
    
    }

}
