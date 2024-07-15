package com.migueljehle.service;


import com.migueljehle.dao.TrechoDAO;
import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.Trecho;
import com.migueljehle.util.FabricaDeDaos;

import java.util.List;

public class TrechoService{

    private final TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);

    public Trecho incluir(Trecho trecho){
        trechoDAO.incluir(trecho);
        trecho.getVoo().getTrechos().add(trecho);
        return trecho;
    }

    public Trecho remover(int id){
        Trecho trecho = this.recuperarTrechoPorId(id);
        if(!trecho.getExecsTrecho().isEmpty()){
            throw new EntidadeComListaNaoVaziaException(
                    "Este Trecho possui execução de trecho e não pode ser removido!");
        }
        trecho.getVoo().getTrechos().remove(trecho);
        trechoDAO.remover(trecho.getId());
        return trecho;
    }

    public Trecho recuperarTrechoPorId(int id) {
        Trecho trecho = trechoDAO.recuperarPorId(id);
        if (trecho == null)
            throw new EntidadeNaoEncontradaException("Trecho inexistente.");
        return trecho;
    }

    public List<Trecho> recuperarTrechos() {
        return trechoDAO.recuperarTodos();
    }

}
