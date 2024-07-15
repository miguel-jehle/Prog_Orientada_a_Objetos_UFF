package com.migueljehle;

import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.model.Cliente;
import com.migueljehle.model.ExecTrecho;
import com.migueljehle.model.Passagem;
import com.migueljehle.model.Voo;
import com.migueljehle.service.ClienteService;
import com.migueljehle.service.PassagemService;
import corejava.Console;

import java.util.List;

public class PrincipalCliente {

    private final ClienteService clienteService = new ClienteService();

    private final PassagemService passagemService = new PassagemService();

    public void principal(){

        String nome;
        String cpf;
        Cliente umCliente;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um cliente");
            System.out.println("2. Alterar um cliente");
            System.out.println("3. Remover um cliente");
            System.out.println("4. Listar todos clientes");
            System.out.println("5. Relatório de um cliente");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

            switch (opcao){
                case 1 -> {
                    nome = Console.readLine("Informe o nome do cliente: ");
                    cpf = Console.readLine("Informe o CPF do cliente: ");
                    umCliente = new Cliente(nome,cpf);
                    clienteService.incluir(umCliente);
                    System.out.println("\nCliente cadastrado com sucesso!");
                }
                case 2 -> { //Alterar
                    int id = Console.readInt("Informe o id do cliente que você deseja alterar: ");

                    try{
                        umCliente = clienteService.recuperarClientePorId(id);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Nome");
                    System.out.println("2. CPF");

                    int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

                    switch (opcaoAlteracao){
                        case 1 ->{
                            String novoNome = Console.readLine("Informe o novo nome: ");
                            clienteService.alterarNome(umCliente,novoNome);
                            System.out.println('\n' + "Alteração do nome realizada com sucesso!");
                        }
                        case 2 ->{
                            String novoCPF = Console.readLine("Informe o novo CPF: ");
                            clienteService.alterarCPF(umCliente,novoCPF);
                            System.out.println('\n' + "Alteração do CPF realizada com sucesso!");
                        }
                        default -> System.out.println('\n' + "Opção inválida!");
                    }

                }
                case 3 -> {
                    int id = Console.readInt("Informe o id do cliente que deseja remover: ");

                    try{
                        clienteService.remover(id);
                        System.out.println('\n' + "Cliente removido com sucesso!");
                    }catch (EntidadeNaoEncontradaException | EntidadeComListaNaoVaziaException e){
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4 -> {
                    List<Cliente> clientes = clienteService.recuperarClientes();
                    for (Cliente cliente : clientes) {
                        System.out.println(cliente);
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Informe o id do cliente que deseja verificar as milhas: ");
                    try{
                        umCliente = clienteService.recuperarClientePorId(id);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    int milhas = 0;
                    List<Passagem> passagens = umCliente.getPassagens();
                    for(Passagem passagem : passagens){
                        List<ExecTrecho> execTrechos = passagem.getExecsTrecho();
                        System.out.println("\n");
                        for(ExecTrecho execTrecho : execTrechos){
                            if(execTrecho.viagemRealizada()){
                                System.out.println("ExecVoo " + execTrecho.getId() + " = " + execTrecho.getTrecho().getMilha() + " milhas.");
                                milhas += execTrecho.getTrecho().getMilha();
                            }
                        }
                    }
                    System.out.println('\n' + "O Cliente " + umCliente.getNome() + " possui " + milhas + " milhas");
                }
                case 6 -> {
                    continua = false;
                }
                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
