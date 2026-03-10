package com.systemgym.systemgym.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systemgym.systemgym.entity.Aluno;
import com.systemgym.systemgym.entity.Pagamento;
import com.systemgym.systemgym.entity.StatusAluno;
import com.systemgym.systemgym.repository.AlunoRepository;
import com.systemgym.systemgym.repository.PagamentoRepository;

@Controller
@RequestMapping("/admin/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    // Abre o formulário de pagamento já vinculado a um aluno específico
    @GetMapping("/novo/{alunoId}")
    public String novoPagamento(@PathVariable("alunoId") Long alunoId, Model model) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno inválido:" + alunoId));
        
        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(aluno);
        pagamento.setDataPagamento(LocalDate.now());
        // Sugere o valor do plano do aluno como valor pago
        pagamento.setValorPago(aluno.getPlano().getValor());

        model.addAttribute("pagamento", pagamento);
        model.addAttribute("nomeAluno", aluno.getNome());
        return "admin/formPagamento";
    }

    @PostMapping("/salvar")
    public String salvar(Pagamento pagamento) {
       Aluno aluno = alunoRepository.findById(pagamento.getAluno().getId()).get();
    
    Integer meses = aluno.getPlano().getDuracaoMeses();
    LocalDate vencimento = pagamento.getDataPagamento().plusMonths(meses);
    
    pagamento.setDataVencimento(vencimento);
    
    // ATUALIZA A DATA NO CADASTRO DO ALUNO TAMBÉM
    aluno.setDataVencimento(vencimento);
    aluno.setStatus(StatusAluno.ATIVO);
    
    alunoRepository.save(aluno);
    pagamentoRepository.save(pagamento);
    
    return "redirect:/admin/aluno/lista";
    }

    @GetMapping("/extrato")
    public String extrato(Model model) {
// Busca todos os pagamentos realizados no banco
    List<Pagamento> lista = pagamentoRepository.findAll();
    model.addAttribute("pagamentos", lista);
    return "admin/extratoPagamentos"; // O nome do ficheiro HTML que criámos
}
}