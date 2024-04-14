package com.carlosribeiro;

public class Empregado
{
	private String nome;
	private double salario;
	
	public Empregado (String n, double s)
	{	this.nome = nome;
		this.salario = salario;
	}

	// ==> toString()

	public String getNome()
	{	return nome;
	}
		
	public double getSalario()
	{	return salario;
	}
		
	public void setNome(String nome)
	{	this.nome = nome;
	}
		
	public void setSalario(double s)
	{	this.salario = salario;
	}
}