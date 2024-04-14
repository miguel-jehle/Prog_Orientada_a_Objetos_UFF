package com.carlosribeiro;

import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Empregado 
{
	private static int contador = 0;
	private int numero;
	private String nome;
	private double salario;
	private LocalDate dataAdmissao;

	//Formatador de números
	private static final NumberFormat NF;

	// Formatador para imprimir e efetuar o parse de objetos date-time
	private static final DateTimeFormatter DTF;
	
	static{  //Bloco de codigo que roda uma unica vez   // NF será utilizado para formatar o salário

		NF = NumberFormat.getCompactNumberInstance();
		DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		NF.setMaximumFractionDigits (2);	   // O default é 3. //Para NF ter no max 2 casas decimais
		NF.setMinimumFractionDigits (2);	   // Da mesma forma colocamos no min 2, para ter certeza
	}

	public Empregado (String nome, double salario, String dataAdmissao) throws DataInvalidException {
		setDataAdmissao(dataAdmissao); //Colocamos o throw ali, pois quem chama o setData foi aqui, então mandamos pro principal, por isso o throws ali encima

		this.numero = ++contador;	// Caso a data de admissão seja
									// inválida o contador não será
									// incrementado.
		this.nome = nome;
		this.salario = salario;


		/* 	O método parseInt() gera a exceção NumberFormatException
			e o método substring() gera a exceção StringIndexOutOfBoundsException.
			E o método LocalDate.of gera a exceção DateTimeException.
			Estas exceções são do tipo unchecked, logo o compilador não obriga o
			programador a declarar que este método gera estas exceções.

			(throws NumberFormatException, StringIndexOutOfBoundsException, DateTimeException)

		  	Toda exceção que deriva das classes Error e RuntimeException são consideradas
		  	unchecked exceptions. O programador deve se preocupar com as exceções do tipo
		  	checked, manipulando-as ou anunciando que elas podem ser propagadas.
				
		   	Exceções do tipo unchecked são:
		   	- Um acesso fora dos limites de um array
		   	- Um acesso a um ponteiro null, etc.
				
		   	Hierarquia das classes que representam exceções:
		
	      		Throwable
					Error                  -  unchecked exceptions
					Exception
					    RuntimeException   -  unchecked exceptions
                           ...
						IOException        -  checked exceptions
						SQLException
						...
		*/
	}

	public String toString()
	{	return "Número = " + numero +
			   "  Nome = " + nome +
			   "  Salário = " + getSalarioMasc() +
			   "  Data de Admissão = " + getDataAdmissaoMasc();
	}

	public int getNumero()
	{	return numero;
	}

	public String getNome()
	{	return nome;
	}

	public double getSalario()
	{	return salario;
	}

	public String getSalarioMasc() {
		return NF.format(salario);
	}
		
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public String getDataAdmissaoMasc() {
		return DTF.format(dataAdmissao);
	}

	public void setNome(String n)
	{	nome = n;
	}
		
	public void setSalario(double s)
	{	salario = s;
	}
		
	public void setDataAdmissao (String novaDataAdmissao) throws DataInvalidException{
		try{
			int dia = Integer.parseInt(novaDataAdmissao.substring(0,2));
			int mes = Integer.parseInt(novaDataAdmissao.substring(3,5));
			int ano = Integer.parseInt(novaDataAdmissao.substring(6,10));

			this.dataAdmissao = LocalDate.of(ano,mes,dia);
		}
		//Caso tenha algum desses erros, não vamos printar no console, pois não faz sentido, vamos lançar para frente (Principal) lidar.
		catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e){
			throw new DataInvalidException("Data de admissão invalida");
		}



    }

	public boolean equals(Object num) {
		return numero == (Integer) num;
	}
}