package com.systemgym.systemgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemgym.systemgym.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // O JpaRepository já nos dá métodos como save(), findAll(), deleteById(), etc.
}
