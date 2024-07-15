package com.migueljehle.service;

import com.migueljehle.dao.ExecVooDAO;
import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.ExecVoo;
import com.migueljehle.util.FabricaDeDaos;

import java.util.List;

public class ExecVooService {

    private final ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);

    public ExecVoo incluir(ExecVoo execVoo){
        execVooDAO.incluir(execVoo);
        execVoo.getVoo().getExecsvoo().add(execVoo);
        return execVoo;
    }

    public ExecVoo remover(int id){
        ExecVoo execVoo = this.recuperarPorId(id);
        if(!execVoo.getExecsTrecho().isEmpty()){
            throw new EntidadeComListaNaoVaziaException(" \n Execução de Voo com Execução de Trecho cadastrado, não é possível remover!");
        }
        execVoo.getVoo().getExecsvoo().remove(execVoo);
        execVooDAO.remover(execVoo.getId());
        return execVoo;
    }

    private ExecVoo recuperarPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null)
            throw new EntidadeNaoEncontradaException("Execução de voo inexistente.");
        return execVoo;
    }

    public List<ExecVoo> recuperarExecsVoo() {
        return execVooDAO.recuperarTodos();
    }

    public ExecVoo recuperarExecVooPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null)
            throw new EntidadeNaoEncontradaException("Execução de voo inexistente.");
        return execVoo;
    }
}
