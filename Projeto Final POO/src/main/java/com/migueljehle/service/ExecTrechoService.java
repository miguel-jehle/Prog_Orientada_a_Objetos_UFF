package com.migueljehle.service;

import com.migueljehle.dao.ExecTrechoDAO;
import com.migueljehle.dao.PassagemDAO;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.exception.VooNaoRelacionadoException;
import com.migueljehle.model.ExecTrecho;
import com.migueljehle.model.ExecVoo;
import com.migueljehle.model.Passagem;
import com.migueljehle.util.FabricaDeDaos;

import java.util.List;
import java.util.Objects;

public class ExecTrechoService {

    private final ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

    private final PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);

    public  ExecTrecho incluir(ExecTrecho umExecTrecho){
        if(!Objects.equals(umExecTrecho.getTrecho().getVoo(), umExecTrecho.getExecVoo().getVoo())){
            throw new VooNaoRelacionadoException("\n O Trecho e a Execução de Voo selecionados não são do mesmo Voo!");
        }
        execTrechoDAO.incluir(umExecTrecho);
        umExecTrecho.getExecVoo().getExecsTrecho().add(umExecTrecho);
        umExecTrecho.getTrecho().getExecsTrecho().add(umExecTrecho);
        return umExecTrecho;
    }

    public ExecTrecho remover(int id){
        ExecTrecho execTrecho = this.recuperarPorId(id);
        List<Passagem> passagens = execTrecho.getPassagens();
        for(Passagem passagem : passagens){ //Tem que acessar todos as passagens que estão na lista de passagens desta ExecTrecho, então acessar uma a uma, obter a lista de ExecTrecgo desta passagem e remover o ExecTrecho específico
            passagem.getExecsTrecho().remove(execTrecho);
            passagem.getCliente().getPassagens().remove(passagem);
            passagemDAO.remover(passagem.getId());
        }
        execTrecho.getTrecho().getExecsTrecho().remove(execTrecho);
        execTrecho.getExecVoo().getExecsTrecho().remove(execTrecho);
        execTrechoDAO.remover(id);
        return execTrecho;
    }

    public ExecTrecho recuperarPorId(int id) {
        ExecTrecho execTrecho = execTrechoDAO.recuperarPorId(id);
        if (execTrecho == null)
            throw new EntidadeNaoEncontradaException("Execução de Trecho inexistente.");
        return execTrecho;
    }

    public List<ExecTrecho> recuperarExecsTrecho() {
        return execTrechoDAO.recuperarTodos();
    }
}
