package com.systemgym.systemgym.entity;

public enum StatusAluno {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String descricao;

    StatusAluno(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
