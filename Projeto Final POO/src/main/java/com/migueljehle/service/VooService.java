package com.migueljehle.service;

import com.migueljehle.dao.VooDAO;
import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.Voo;
import com.migueljehle.util.FabricaDeDaos;

import java.util.List;

public class VooService {

    private final VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

    public Voo incluir(Voo voo) {
        return vooDAO.incluir(voo);
    }

    public Voo recuperarVooPorId(int id) {
        Voo voo = vooDAO.recuperarPorId(id);
        if (voo == null)
            throw new EntidadeNaoEncontradaException(" \n Voo inexistente.");
        return voo;
    }

    public Voo remover(int id) {
        Voo voo = recuperarVooPorId(id);
        if (!(voo.getTrechos().isEmpty())){
            throw new EntidadeComListaNaoVaziaException(
                    " \n Este Voo possui Trechos e não pode ser removido!");
        }
        if(!(voo.getExecsvoo().isEmpty())){
            throw new EntidadeComListaNaoVaziaException(
                    " \n Este Voo possui Execuções de Voo e não pode ser removido!");
        }
        vooDAO.remover(voo.getId());
        return voo;
    }

    public List<Voo> recuperarVoos() {
        return vooDAO.recuperarTodos();
    }

}
