package com.systemgym.systemgym.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.systemgym.systemgym.repository.AlunoRepository;
import com.systemgym.systemgym.repository.FuncionarioRepository;
import com.systemgym.systemgym.repository.PagamentoRepository;

@Controller
public class DashboardController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping("/")
    public String redirecionar() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/admin/dashboard")
    public String index(Model model) {
        // Estatísticas para os cards
        model.addAttribute("totalAlunos", alunoRepository.count());
        model.addAttribute("totalFuncionarios", funcionarioRepository.count());
        
        // Exemplo: Soma de todos os pagamentos realizados
        BigDecimal totalRecebido = pagamentoRepository.findAll()
                .stream()
                .map(p -> p.getValorPago())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        model.addAttribute("faturamentoTotal", totalRecebido);
        
        return "admin/dashboard";
    }

}
