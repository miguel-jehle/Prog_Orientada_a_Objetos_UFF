package com.migueljehle.model;

import com.migueljehle.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Voo implements Serializable {
    @Id
    private int id;

    private String origem;

    private String destino;

    private List<ExecVoo> execsvoo;

    private List<Trecho> trechos;


    public Voo(String origem, String destino){
        this.destino = destino;
        this.origem = origem;
        this.execsvoo = new ArrayList<>();
        this.trechos = new ArrayList<>();
    }

    public String toString(){
        return "ID = " + id +
                " | Origem = " + origem +
                " | Destino = " + destino;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getOrigem() {return origem;}

    public void setOrigem(String origem) {this.origem = origem;}

    public String getDestino() {return destino;}

    public void setDestino(String destino) {this.destino = destino;}

    public List<Trecho> getTrechos() {
        return trechos;
    }

    public List<ExecVoo> getExecsvoo(){return execsvoo;}

}
