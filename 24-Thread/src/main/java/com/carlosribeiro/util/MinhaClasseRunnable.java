package com.carlosribeiro.util;

import com.carlosribeiro.model.Cliente;

import java.util.List;

public class MinhaClasseRunnable implements Runnable {
    private List<Cliente> clientes;
    private String texto;

    public MinhaClasseRunnable(List<Cliente> clientes, String texto) {
        this.clientes = clientes;
        this.texto = texto;
    }

    @Override
    public void run() {
        for (Cliente cliente : clientes) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(texto + " ao cliente " + cliente.getNome());
        }
    }
}
