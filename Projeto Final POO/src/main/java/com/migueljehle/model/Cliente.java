package com.migueljehle.model;

import com.migueljehle.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    @Id
    private int id;

    private String nome;

    private String cpf;

    private List<Passagem> passagens;

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.passagens = new ArrayList<>();
    }

    public String toString(){
        return  "ID = " +  id  +
                " | Nome = " +  nome  +
                " | CPF = " +  cpf;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCPF() {return cpf;}

    public void setCPF(String cpf) {this.cpf = cpf;}

    public List<Passagem> getPassagens() {return passagens;}
}
