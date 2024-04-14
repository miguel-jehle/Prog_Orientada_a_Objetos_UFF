package com.carlosribeiro;

import java.util.ArrayList;
import corejava.Console;

public class Principal {
	public static void main(String[] args) {
		// final ...
		ArrayList <Empregado>listaDeEmpregados = new ArrayList<>(10);
		String nome;
		double salario;
		Empregado umEmpregado;

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "==============================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um empregado");
			System.out.println("2. Alterar um empregado");
			System.out.println("3. Remover um empregado");
			System.out.println("4. Listar relação de empregados");
			System.out.println("5. Listar apenas Número e Nome dos empregados");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

			switch (opcao) {
			case 1:
				nome = Console.readLine('\n' + "Digite o nome do empregado: ");
				salario = Console.readDouble("Digite o salario do empregado: ");

				umEmpregado = new Empregado(nome, salario);
				listaDeEmpregados.add(umEmpregado);
				System.out.println("Empregado número " + umEmpregado.getNumero() + " cadastrado com sucesso.");

				break;

			case 2: // Alterar
			{
				int resposta = Console.readInt('\n' + "Digite o número do empregado " +
						                                 "que você deseja alterar: ");

			 	int posicao = Principal.localizar(resposta,listaDeEmpregados);

				if (posicao != -1) {

					umEmpregado = (Empregado) listaDeEmpregados.get(posicao);

					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. Salario");

					int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

					switch (opcaoAlteracao) {
					case 1:
						String novoNome = Console.readLine("Digite o novo nome: ");
						//Uso a função setNome para acessar e substituir pela variável recebida acima
						umEmpregado.setNome(novoNome); //umEmpregado
						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
						break;

					case 2:
						double novoSalario = Console.readDouble("Digite o novo salário: ");
						//Uso a função setSalario para acessar e substituir pela variável recebida acima
						umEmpregado.setSalario((novoSalario));
						System.out.println("Salário alterado com sucesso.");
						break;

					default:
						System.out.println('\n' + "Opção inválida!");
					}
				} else {
					System.out.println('\n' + "Empregado não encontrado!");
				}
				break;
			}

			case 3: // Remover
			{
				int resposta = Console.readInt('\n' +
						"Digite o número do empregado que você deseja remover: ");
				int posicao = localizar(resposta,listaDeEmpregados);

				if (posicao != -1) {
					listaDeEmpregados.remove(posicao);
					System.out.println('\n' + "Empregado removido com sucesso!");
				} else
					System.out.println('\n' + "Empregado não encontrado!");

				break;
			}

			case 4: // Listar tudo
				if (listaDeEmpregados.isEmpty())
					System.out.println('\n' + "Não há empregados na lista.");
				else {
					//for (int i = 0; i < listaDeEmpregados.size(); i++)
                    for (Empregado listaDeEmpregado : listaDeEmpregados) {
                        umEmpregado = (Empregado) listaDeEmpregado;
                        System.out.println(umEmpregado);
                    }
				}
				break;

			case 5: // Listar Numero e Nome
				if (listaDeEmpregados.isEmpty())
					System.out.println('\n' + "Não há empregados na lista.");
				else {
					//for (int i = 0; i < listaDeEmpregados.size(); i++)
                    for (Empregado listaDeEmpregado : listaDeEmpregados) {
                        umEmpregado = (Empregado) listaDeEmpregado;
                        System.out.println("Número = " + umEmpregado.getNumero() +
                                "   Nome = " + umEmpregado.getNome());
                    }
				}
				break;

			case 6: // Sair
				continua = false;
				break;

			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}

	private static int localizar(int numero, ArrayList lista) {
		for (int i = 0; i < lista.size(); i++) {
			Empregado e = (Empregado)lista.get(i);
			if(e.getNumero() == numero) return i;

		}
		return -1;
	}
}
