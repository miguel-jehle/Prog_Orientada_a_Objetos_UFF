package com.migueljehle;

import com.migueljehle.exception.DataIncoerenteExecption;
import com.migueljehle.exception.DataInvalidaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.exception.VooNaoRelacionadoException;
import com.migueljehle.model.ExecTrecho;
import com.migueljehle.model.ExecVoo;
import com.migueljehle.model.Passagem;
import com.migueljehle.model.Trecho;
import com.migueljehle.service.ExecTrechoService;
import com.migueljehle.service.ExecVooService;
import com.migueljehle.service.TrechoService;
import corejava.Console;

import java.util.List;

public class PrincipalExecTrecho {

    private final ExecTrechoService execTrechoService = new ExecTrechoService();

    private final TrechoService trechoService = new TrechoService();

    private final ExecVooService execVooService = new ExecVooService();

    public void principal(){
        ExecTrecho umExecTrecho;
        ExecVoo umExecVoo;
        Trecho umTrecho;
        String dataHoraInicio;
        String dataHoraChegada;
        int idExecVoo;
        int idTrecho;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma Execução de Trecho");
            System.out.println("2. Remover uma Execução de Trecho");
            System.out.println("3. Listar todos Execuções de Trecho");
            System.out.println("4. Listar todos Execuções Trecho de um Trecho");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao){
                case 1 -> {
                    idTrecho = Console.readInt("Informe o id do Trecho: ");
                    idExecVoo = Console.readInt("Informe o id da Execução Voo");

                    try{
                        umExecVoo = execVooService.recuperarExecVooPorId(idExecVoo);
                        umTrecho = trechoService.recuperarTrechoPorId(idTrecho);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    if(umExecVoo.getExecsTrecho().isEmpty())dataHoraInicio = umExecVoo.getDataHoraInicio();
                    else dataHoraInicio = Console.readLine("Informe a data e hora do inicio da execução no formato dd/MM/aaaa/hh:MM:ss");
                    dataHoraChegada = Console.readLine("Informe a data e hora da chegada da execução no formato dd/MM/aaaa/hh:MM:ss");

                    try{
                        umExecTrecho = new ExecTrecho(dataHoraInicio,dataHoraChegada,umExecVoo,umTrecho);
                    }
                    catch (DataInvalidaException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    try{
                        execTrechoService.incluir(umExecTrecho);
                    }catch (VooNaoRelacionadoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("\nExecução de Trecho número " + umExecTrecho.getId() + " cadastrado com sucesso!");
                }
                case 2 -> {
                    int id = Console.readInt("Informe o número da execução de voo que você deseja remover: ");
                    try {
                        execTrechoService.remover(id);
                        System.out.println('\n' + " Execução de Trecho removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    List<ExecTrecho> execTrechos = execTrechoService.recuperarExecsTrecho();
                    for (ExecTrecho execTrecho : execTrechos) {
                        System.out.println(execTrecho);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Informe o número do Trecho: ");
                    try {
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                        for (ExecTrecho execTrecho : umTrecho.getExecsTrecho()) {
                            System.out.println(execTrecho);
                        }
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 5 -> {
                    continua = false;
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
