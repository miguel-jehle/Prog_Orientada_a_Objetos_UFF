package com.migueljehle;

import com.migueljehle.exception.DataInvalidaException;
import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.ExecVoo;
import com.migueljehle.model.Trecho;
import com.migueljehle.model.Voo;
import com.migueljehle.service.ExecTrechoService;
import com.migueljehle.service.ExecVooService;
import com.migueljehle.service.VooService;
import corejava.Console;

import java.time.ZonedDateTime;
import java.util.List;

public class PrincipalExecVoo {

    private final ExecVooService execVooService = new ExecVooService();

    private final VooService vooService = new VooService();

    private final ExecTrechoService execTrechoService = new ExecTrechoService();

    public void principal(){

        ExecVoo umExecVoo;
        String dataHoraInicio;
        String dataHoraChegada;
        Voo umVoo;
        int idVoo;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma Execução Voo");
            System.out.println("2. Remover uma Execução Voo");
            System.out.println("3. Listar todos as Execuções");
            System.out.println("4. Listar todos as Execuções Voo de um Voo");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao){
                case 1 -> {
                    dataHoraInicio = Console.readLine("Informe a data e hora do inicio da execução no formato dd/MM/aaaa/hh:MM:ss");
                    dataHoraChegada = Console.readLine("Informe a data e hora da chegada da execução no formato dd/MM/aaaa/hh:MM:ss");
                    idVoo = Console.readInt("Informe o número do Voo: ");
                    try {
                        umVoo = vooService.recuperarVooPorId(idVoo);
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    try {
                        umExecVoo = new ExecVoo(dataHoraInicio, dataHoraChegada, umVoo);
                        execVooService.incluir(umExecVoo);
                        System.out.println("\nExecução de Voo número " + umExecVoo.getId() + " cadastrado com sucesso!");
                    } catch (DataInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    int id = Console.readInt(
                            "Informe o número da execução de voo que você deseja remover: ");

                    try {
                        execVooService.remover(id);
                        System.out.println('\n' + "Execução de voo removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | EntidadeComListaNaoVaziaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    List<ExecVoo> execVoos = execVooService.recuperarExecsVoo();
                    for (ExecVoo execVoo : execVoos) {
                        System.out.println(execVoo);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Informe o número do voo: ");
                    try {
                        umVoo = vooService.recuperarVooPorId(id);
                        for (ExecVoo execVoo : umVoo.getExecsvoo()) {
                            System.out.println(execVoo);
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
