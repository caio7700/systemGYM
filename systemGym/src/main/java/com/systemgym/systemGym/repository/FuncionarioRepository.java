package com.systemgym.systemgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemgym.systemgym.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}