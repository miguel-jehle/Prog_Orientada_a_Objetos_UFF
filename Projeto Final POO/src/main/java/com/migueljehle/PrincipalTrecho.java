package com.migueljehle;

import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.Trecho;
import com.migueljehle.model.Voo;
import com.migueljehle.service.ExecTrechoService;
import com.migueljehle.service.TrechoService;
import com.migueljehle.service.VooService;
import corejava.Console;

import java.util.List;

public class PrincipalTrecho {

    private final TrechoService trechoService = new TrechoService();

    private final ExecTrechoService execTrechoService =  new ExecTrechoService();

    private final VooService vooService = new VooService();


    public void principal(){

        String origem;
        String destino;
        Trecho umTrecho;
        Voo umVoo;
        int idVoo;
        int preco;
        int milhas;

        System.out.println("Você pode tratar de Trecho");
        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Trecho");
            System.out.println("2. Remover um Trecho");
            System.out.println("3. Listar todos Trechos");
            System.out.println("4. Listar todos os Trechos de um mesmo Voo");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            switch (opcao){
                case 1 -> {
                    idVoo = Console.readInt("Informe o número do Voo ao qual o Trecho pertence: ");
                    destino = Console.readLine("Informe o destino do Trecho: ");
                    preco = Console.readInt("Informe o preço do Trecho: ");
                    milhas = Console.readInt("Informe a quantidade de milhas do Trecho: ");
                    try {
                        umVoo = vooService.recuperarVooPorId(idVoo);
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    umTrecho = new Trecho(destino, umVoo,milhas,preco);
                    trechoService.incluir(umTrecho);
                    System.out.println("\nTrecho número " + umTrecho.getId() + " cadastrado com sucesso!");
                }
                case 2 -> {
                    int id = Console.readInt(
                            "Informe o número do trecho que você deseja remover: ");

                    try {
                        trechoService.remover(id);
                        System.out.println('\n' + "Trecho " + id + " removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | EntidadeComListaNaoVaziaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {
                    List<Trecho> trechos = trechoService.recuperarTrechos();
                    for (Trecho trecho : trechos) {
                        System.out.println(trecho);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Informe o número do voo: ");
                    try {
                        umVoo = vooService.recuperarVooPorId(id);
                        for (Trecho trecho : umVoo.getTrechos()) {
                            System.out.println(trecho);
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
