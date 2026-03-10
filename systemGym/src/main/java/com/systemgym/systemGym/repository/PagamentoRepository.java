package com.systemgym.systemgym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemgym.systemgym.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAlunoId(Long alunoId);
    
}
