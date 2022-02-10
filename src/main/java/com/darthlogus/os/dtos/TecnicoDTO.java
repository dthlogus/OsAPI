package com.darthlogus.os.dtos;

import com.darthlogus.os.domain.Tecnico;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo NOME deve ser preenchido")
    private String nome;
    @CPF
    @NotEmpty(message = "O campo CPF deve ser preenchido")
    private String cpf;
    @NotEmpty(message = "O campo TELEFONE deve ser preenchido")
    private String telefone;

    public TecnicoDTO() {
    }

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.telefone = tecnico.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
