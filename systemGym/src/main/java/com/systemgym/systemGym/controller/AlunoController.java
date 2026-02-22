package com.systemgym.systemgym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemgym.systemgym.entity.Aluno;
import com.systemgym.systemgym.repository.AlunoRepository;

@Controller
@RequestMapping("admin/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository; // Injeção de dependência

    // Exibe o formulário de cadastro
    @GetMapping("/novo")
    public String exibirFormulario(Aluno aluno) {
        return "admin/formAluno";
    }

    // Salva o aluno no MySQL
    @PostMapping("/salvar")
    public String salvarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/admin/aluno/lista"; // Redireciona para a lista após salvar
    }

    // Lista todos os alunos cadastrados
    @GetMapping("/lista")
    public String listarAlunos(Model model) {
        List<Aluno> listaDeAlunos = alunoRepository.findAll();
        model.addAttribute("alunos", listaDeAlunos);
        return "admin/listaAlunos"; // Nome do arquivo HTML da lista
    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Long id) {
    alunoRepository.deleteById(id);
    // Após excluir, redirecionamos para a lista para ver o resultado
    return "redirect:/admin/aluno/lista";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable("id") Long id, Model model) {
    // Busca o aluno no banco pelo ID. Se não encontrar, lança uma exceção ou redireciona.
    Aluno aluno = alunoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de aluno inválido:" + id));
    
    model.addAttribute("aluno", aluno); // Passa o aluno encontrado para o formulário
    return "admin/formAluno"; // Reutilizamos o mesmo formulário de cadastro
    }
}