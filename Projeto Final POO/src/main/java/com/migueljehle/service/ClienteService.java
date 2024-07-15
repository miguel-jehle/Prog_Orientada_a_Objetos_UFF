package com.migueljehle.service;

import com.migueljehle.dao.ClienteDAO;
import com.migueljehle.exception.EntidadeNaoEncontradaException;
import com.migueljehle.exception.EntidadeComListaNaoVaziaException;
import com.migueljehle.model.Cliente;
import com.migueljehle.util.FabricaDeDaos;

import java.util.List;

public class ClienteService{
    private final ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);

    public Cliente incluir(Cliente cliente){return clienteDAO.incluir(cliente);}

    public Cliente recuperarClientePorId(int id){
        Cliente cliente = clienteDAO.recuperarPorId(id);
        if (cliente == null)
            throw new EntidadeNaoEncontradaException(" \n Cliente inexistente!");
        return cliente;
    }

    public Cliente remover(int id){
        Cliente cliente = recuperarClientePorId(id);
        if(!cliente.getPassagens().isEmpty())
            throw new EntidadeComListaNaoVaziaException(
                    " \n Cliente com passagem cadastrada! Não é possível remover.");
        clienteDAO.remover(cliente.getId());
        return cliente;
    }

    public Cliente alterarNome(Cliente cliente, String nome){
        cliente.setNome(nome);
        return cliente;
    }

    public Cliente alterarCPF(Cliente cliente, String cpf){
        cliente.setCPF(cpf);
        return cliente;
    }

    public List<Cliente> recuperarClientes(){return clienteDAO.recuperarTodos();}


}
