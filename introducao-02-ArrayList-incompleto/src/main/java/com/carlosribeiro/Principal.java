package com.carlosribeiro;

import corejava.Console;

import java.util.ArrayList;

public class Principal
{	public static void main (String[] args)
	{
		//variáveis "final" não podem alterar seu tipo posteriormente
		final int TAM = Console.readInt("informe o tamanho da lista"); //Usamos essa função console para ler

		//Criando a lista com o tamanho dado
		ArrayList listaDeEmpregados = new ArrayList(TAM);

		String nome;
		double salario;
		Empregado umEmpregado;

		boolean continua = true;
		while (continua)
		{	System.out.println("\nO que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um empregado"); //Aqui ele da duas formas de vc pular linha
			System.out.println("2. Alterar um empregado");
			System.out.println("3. Remover um empregado");
			System.out.println("4. Listar relação de empregados");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");			
					
			switch (opcao)
			{	case 1:
					//Obtemos as informações do empregado que queremos criar
					nome = Console.readLine("Informe o nome: ");
					salario = Console.readDouble("Informe o salário: ");

					//Estamos passando as informações obtidas para o método construtor
					umEmpregado = new Empregado(nome,salario);
					
					//Com ele criado vamos adicionar ele na lista
					listaDeEmpregados.add(umEmpregado);

					System.out.println("Empregado " + umEmpregado.getNome() + " cadastrado com sucesso.");

					break;
				case 2:
										
					break;
				case 3:

					break;
				case 4:

					for(int i = 0; i < listaDeEmpregados.size(); i++){
						//Repare que aqui precisamos fazer um cast para confirmar o tipo
						umEmpregado = (Empregado) listaDeEmpregados.get(i);
					}

					break;
				case 5:
					continua = false;
					break;
				default:
					System.out.println('\n' + "Opção inválida!");			
					break;
			}
		}		
	}
}