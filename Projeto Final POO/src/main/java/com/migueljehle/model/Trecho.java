package com.migueljehle.model;

import com.migueljehle.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trecho implements Serializable {
    @Id
    private int id;

    private int milhas;

    private int preço;
    private String origem;

    private String destino;

    private Voo voo;

    private List<ExecTrecho> execsTrecho;

    public Trecho(String destino, Voo voo, int milhas, int preço){
        if(voo.getTrechos().isEmpty()){
            this.origem = voo.getOrigem();
        }
        else{
            List<Trecho> trechos = voo.getTrechos();
            Trecho last = trechos.get(trechos.size() - 1);
            this.origem = last.destino;
        }
        this.destino = destino;
        this.voo = voo;
        this.preço = preço;
        this.milhas = milhas;
        this.execsTrecho = new ArrayList<>();
    }

    public String toString(){
        return "ID =  " + id +
                " | Voo: " + voo.getId() +
                " | Origem: " + origem +
                " | Destino: " + destino +
                " | Preço: " + preço +
                " | Milhas:" + milhas;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Integer getPreço() {return preço;}

    public void setPreço(Integer preço) {this.preço = preço;}

    public Integer getMilha() {return milhas;}

    public void setMilhas(Integer milhas) {this.milhas = milhas;}
    public String getOrigem() {return origem;}

    public void setOrigem(String origem) {this.origem = origem;}

    public String getDestino() {return destino;}

    public void setDestino(String destino) {this.destino = destino;}

    public List<ExecTrecho> getExecsTrecho(){return execsTrecho;}

    public Voo getVoo() {
        return voo;
    }

}
