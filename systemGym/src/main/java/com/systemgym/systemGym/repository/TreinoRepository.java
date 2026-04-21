package com.systemgym.systemgym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemgym.systemgym.entity.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long>{
    List<Treino> findByAlunoId(Long alunoId);
}
