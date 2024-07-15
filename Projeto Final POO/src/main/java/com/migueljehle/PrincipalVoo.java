package com.migueljehle;

import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.ExecTrecho;
import com.migueljehle.model.Passagem;
import com.migueljehle.model.Trecho;
import com.migueljehle.service.ExecVooService;
import com.migueljehle.service.TrechoService;
import com.migueljehle.service.VooService;
import com.migueljehle.model.Voo;
import corejava.Console;

import java.util.List;

public class PrincipalVoo {

    private final VooService vooService = new VooService();

    private final TrechoService trechoService = new TrechoService();

    private final ExecVooService execVooService = new ExecVooService();

    public void principal(){

        String origem;
        String destino;
        Voo umVoo;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Voo");
            System.out.println("2. Remover um Voo");
            System.out.println("3. Listar todos Voos");
            System.out.println("4. Listar todos Voos com os Trechos detalhados");
            System.out.println("5. Relatório de clientes por Voo a partir de uma data");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            switch (opcao){
                case 1 -> {
                   origem = Console.readLine("Informe a origem do voo: ");
                   destino = Console.readLine("Informe o destino do voo");
                   umVoo = new Voo(origem,destino);
                   vooService.incluir(umVoo);
                   System.out.println("\nVoo cadastrado com sucesso!");
                }
                case 2 -> {
                    int id = Console.readInt("Informe o número do voo que você deseja remover: ");

                    try {
                        vooService.remover(id);
                        System.out.println('\n' + "Voo removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | EntidadeComListaNaoVaziaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 ->{
                    List<Voo> voos = vooService.recuperarVoos();
                    for(Voo voo : voos){
                        System.out.println(voo);
                    }
                }
                case 4 -> {
                    List<Voo> voos = vooService.recuperarVoos();
                    for (Voo voo : voos) {
                        System.out.println('\n' + "------------------------------------------------------");
                        System.out.println(voo);
                        System.out.println('\n');
                        List<Trecho> trechos = voo.getTrechos();
                        System.out.println("Trechos do Voo " + voo.getId() + ":");
                        System.out.println('\n');
                        for(Trecho trecho: trechos){
                            System.out.print(trecho);
                            System.out.println('\n');
                        }
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id do Voo? ");
                    String data = Console.readLine("Informe a data e hora (DD/MM/AAAA/HH:MM:SS): ");

                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    int resp;
                    List<Trecho> trechos = umVoo.getTrechos();
                    for(Trecho trecho : trechos){
                        List<ExecTrecho> execTrechos = trecho.getExecsTrecho();
                        for(ExecTrecho execTrecho: execTrechos){
                            resp = 0;
                            if(execTrecho.posData(data,execTrecho.getDataHoraInicio())){
                                List<Passagem> passagems = execTrecho.getPassagens();
                                for(Passagem passagem : passagems) resp++;
                                System.out.println("A ExecTrecho " + execTrecho.getId() + " possui " + resp + " passageiros");
                            }

                        }
                    }

                }
                case 6 -> {
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
