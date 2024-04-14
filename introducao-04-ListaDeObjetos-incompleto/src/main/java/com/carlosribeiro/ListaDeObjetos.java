package com.carlosribeiro;

import java.util.*;

public class ListaDeObjetos {
    private ArrayList lista;
    private int corrente;

    public ListaDeObjetos(int tam) {
        lista = new ArrayList<>(tam);
        corrente = -1;
    }

    public void adicionar(Object obj) {
    // ==>
    }

    public int tamanho(int numero) {
        int posicao = localizar(numero);
    }

    public boolean remover(int numero) {
    // ==>

    }

    private int localizar(int numero) {
        // Because there's a cache of Integer from -128 to 127.
        // Using valueOf for that range will return those instance instead of
        // create a new instance again and again.



    }

    public Object recuperar(int numero) {
    // ==>




    }

    public boolean vazia() {
    // ==>
    }


    public Object recuperarPrimeiro() {
    // ==>



    }

    public Object recuperarProximo() {
    // ==>




    }
}

