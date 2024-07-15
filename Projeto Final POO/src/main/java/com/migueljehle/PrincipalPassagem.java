package com.migueljehle;

import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.Cliente;
import com.migueljehle.model.ExecTrecho;
import com.migueljehle.model.Passagem;
import com.migueljehle.service.ClienteService;
import com.migueljehle.service.ExecTrechoService;
import com.migueljehle.service.PassagemService;
import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class PrincipalPassagem {

    private final PassagemService passagemService = new PassagemService();

    private final ClienteService clienteService = new ClienteService();
    
    private final ExecTrechoService execTrechoService = new ExecTrechoService();

    public void principal(){

        int numero;
        int preco;
        int idExectrecho = 0;
        int idPassagem;
        int cont = 1000;
        ExecTrecho umExectrecho;
        Cliente umCliente;




        System.out.println("Você consegue tratar de Passagem");
        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma passagem");
            System.out.println("2. Remover uma passagem");
            System.out.println("3. Listar todas as passagens");
            System.out.println("4. Relatório de passagens");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao){
                case 1 -> {
                    Passagem umaPassagem;
                    int idCliente = 0;

                    idCliente = Console.readInt("Informe o ID do cliente:");
                    try{
                        umCliente = clienteService.recuperarClientePorId(idCliente);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    numero = cont; cont += 8;
                    umaPassagem = new Passagem(numero,umCliente);
                    idExectrecho = 0;
                    while(idExectrecho != -1){
                        idExectrecho = Console.readInt("Informe o ID de uma Execução de Trecho que esta passagem pertencerá: ");
                        if(idExectrecho == -1) break;

                        try{
                            umExectrecho = execTrechoService.recuperarPorId(idExectrecho);
                        }catch (EntidadeNaoEncontradaException e){
                            System.out.println(e.getMessage());
                            break;
                        }

                        umaPassagem.getExecsTrecho().add(umExectrecho);
                    }
                    List<ExecTrecho> execTrechos = umaPassagem.getExecsTrecho();
                    for(ExecTrecho execTrecho : execTrechos){
                        int aux = execTrecho.getTrecho().getPreço();
                        int atual = umaPassagem.getPreco();
                        umaPassagem.setPreco(aux + atual);
                    }
                    passagemService.incluir(umaPassagem);
                    System.out.println('\n' + "Passagem " + umaPassagem.getId() + " cadastrada com sucesso!");
                }
                case 2 -> {
                    int id = Console.readInt("Informe o ID da Passagem: ");
                    try{
                        passagemService.remover(id);
                        System.out.println('\n' + "Passagem " + id + " removida com sucesso!");
                    }catch (EntidadeNaoEncontradaException | EntidadeComListaNaoVaziaException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    List<Passagem> passagens = passagemService.recuperarPassagens();
                    for(Passagem passagem : passagens){
                        System.out.println(passagem);
                    }
                }
                case 4 -> {
                    List<Passagem> passagens = passagemService.recuperarPassagens();
                    for(Passagem passagem : passagens){
                        System.out.println('\n' + "------------------------------------------------------");
                        System.out.println("INFORMAÇÕES DA PASSAGEM " + passagem.getId() + " : ");
                        System.out.println('\n');
                        System.out.println("PROPRIETÁRIO: " + passagem.getCliente().getNome());
                        System.out.println('\n');
                        System.out.println("PREÇO: " + passagem.getPreco());
                        System.out.println('\n');
                        System.out.println("EXECUÇÕES DE TRECHO: ");
                        List<ExecTrecho> execTrechos = passagem.getExecsTrecho();
                        for(ExecTrecho execTrecho : execTrechos){
                            System.out.println("Id = " + execTrecho.getId()+ " | ");
                            System.out.print("Origem = " + execTrecho.getTrecho().getOrigem() + " | ");
                            System.out.print("Destino = " + execTrecho.getTrecho().getDestino() + " | ");
                            System.out.print("Partida = " + execTrecho.getDataHoraInicio()+ " | ");
                            System.out.print("Chegada = " + execTrecho.getDataHoraChegada()+ " | ");
                            System.out.print("Preço = " + execTrecho.getTrecho().getPreço()+ " | ");
                            System.out.println('\n');
                        }
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
