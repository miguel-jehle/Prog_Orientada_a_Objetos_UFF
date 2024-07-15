package com.migueljehle.model;

import com.migueljehle.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Passagem implements Serializable {
    @Id
    private int id;

    private int numero;

    private int preco;

    private Cliente cliente;

    private List<ExecTrecho> execsTrecho;

    public Passagem(int numero, Cliente cliente){
        this.numero = numero;
        this.cliente = cliente;
        this.execsTrecho = new ArrayList<>();
    }

    public String toString(){
        return  "ID = " + id +
                " | Número: " + numero +
                " | Preço: " + preco;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Integer getNum() {return numero;}

    public void setNum(Integer numero){this.numero = numero;}

    public Integer getPreco(){return preco;}

    public void setPreco(Integer preco){this.preco =  preco;}

    public Cliente getCliente(){return cliente;}

    public List<ExecTrecho> getExecsTrecho(){return execsTrecho;}
}
