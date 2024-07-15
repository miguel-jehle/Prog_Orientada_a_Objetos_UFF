package com.carlosribeiro;

import com.carlosribeiro.model.Cliente;
import com.carlosribeiro.util.EstendeThread;
import com.carlosribeiro.util.MinhaClasseRunnable;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        List<Cliente> clientes = List.of(
                new Cliente(1, "Luis", "luis@gamil.com"),
                new Cliente(2, "Lucas", "lucas@gmail.com")
        );

        String texto = "Texto enviado por email";

        EstendeThread et = new EstendeThread(clientes, texto);
        et.start();

        MinhaClasseRunnable mcr = new MinhaClasseRunnable(clientes, texto);
        Thread thread = new Thread(mcr);
        thread.start();

        System.out.println("Fim da execução do método main.");
    }
}










