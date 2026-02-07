package entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Aluno {
@Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @NotBlank(message = "O nome é obrigatório e não pode estar em branco")
    private String nome;
    // @NotBlank(message = "O email é obrigatório e não pode estar em branco")
    private String email;
    // @NotBlank(message = "O telefone é obrigatório e não pode estar em branco")
    private String telefone;
    // @NotBlank(message = "O CPF é obrigatório e não pode estar em branco")
    private String cpf;
 //@Past(message = "A data de nascimento deve ser uma data passada.")
    private LocalDate dataNascimento;

    public Aluno() {
    }

    public Aluno( int id, String nome, String email, String telefone, String cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf
                + ", dataNascimento=" + dataNascimento + "]";
    }


    
}
