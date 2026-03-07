package com.systemgym.systemgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemgym.systemgym.entity.Plano;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
}