package com.migueljehle.service;

import com.migueljehle.dao.PassagemDAO;
import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.ExecTrecho;
import com.migueljehle.model.Passagem;
import com.migueljehle.model.Trecho;
import com.migueljehle.util.FabricaDeDaos;

import java.util.List;

public class PassagemService {

    private final PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);

    public Passagem incluir(Passagem passagem){
        passagemDAO.incluir(passagem);
        passagem.getCliente().getPassagens().add(passagem);
        List<ExecTrecho> execsTrecho  = passagem.getExecsTrecho();
        for(ExecTrecho execTrecho : execsTrecho){ //Percorro todas as ExecTrecho desta passagem e então pego a lista de passagens de cada um, e então insiro a passagem na lista de cada um.
            execTrecho.getPassagens().add(passagem);
        }

        return passagem;
    }

    public Passagem recuperarPassagemPorId(int id) {
        Passagem passagem = passagemDAO.recuperarPorId(id);
        if (passagem == null)
            throw new EntidadeNaoEncontradaException(" \n Passagem inexistente.");
        return passagem;
    }

    public Passagem remover(int id){
        Passagem passagem = this.recuperarPassagemPorId(id);
        if(!passagem.getExecsTrecho().isEmpty()) throw new EntidadeComListaNaoVaziaException(" \n Passagem com Execução de Trecho cadastrado! Não é possível remover");
        passagem.getCliente().getPassagens().remove(passagem);
        List<ExecTrecho> execsTrecho  = passagem.getExecsTrecho();
        for(ExecTrecho execTrecho : execsTrecho){ //Percorro todas as ExecTrecho desta passagem e então pego a lista de passagens de cada um, e então retiro a passagem na lista de cada um.
            execTrecho.getPassagens().remove(passagem);
        }
        return passagem;
    }

    public List<Passagem> recuperarPassagens() {
        return passagemDAO.recuperarTodos();
    }
}
