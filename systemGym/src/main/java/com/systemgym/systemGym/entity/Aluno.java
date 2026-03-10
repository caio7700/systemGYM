package com.systemgym.systemgym.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aluno") // Define o nome da tabela no MySQL
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O MySQL cuidará dos IDs (1, 2, 3...)
    private Long id; // Use Integer (objeto) em vez de int (primitivo) para permitir nulos

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING) // Salva o nome da constante no banco (ATIVO/INATIVO)
    private StatusAluno status = StatusAluno.ATIVO; // Padrão: novo aluno já começa ativo

    @ManyToOne
    @JoinColumn(name = "plano_id") // Cria uma chave estrangeira no MySQL
    private Plano plano;

    private LocalDate dataVencimento;

    public Aluno() {
    }

    public Aluno( Long id, String nome, String email, String telefone, String cpf, LocalDate dataNascimento, StatusAluno status, LocalDate dataVencimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.status = status;
        this.dataVencimento = dataVencimento;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public StatusAluno getStatus() {
        return status;
    }
    public void setStatus(StatusAluno status) {
        this.status = status;
    }
    public Plano getPlano() {
        return plano;
    }
    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf
                + ", dataNascimento=" + dataNascimento + ", status=" + status + ", plano=" + plano + ", dataVencimento=" + dataVencimento + "]";
    }


    
}
