package com.systemgym.systemgym.entity;

public enum StatusFuncionario {
    ATIVO("Ativo"),
    AFASTADO("Afastado"),
    DESLIGADO("Desligado");

    private final String descricao;
    StatusFuncionario(String descricao) { this.descricao = descricao; }
    public String getDescricao() { return descricao; }
}
