package com.carlosribeiro;

import java.util.*;

public class ListaDeObjetos<E> {
    private final ArrayList<E> lista;
    private int corrente;

    public ListaDeObjetos(int tam) {
        lista = new ArrayList<>(tam);
        corrente = -1;
    }

    public void adicionar(E obj) {
        lista.add(obj);
    }

    public int tamanho() {
        return lista.size();
    }

    public boolean remover(int numero) {
        int posicao = localizar(numero);
        if (posicao == -1) {
            return false;
        }
        else {
            lista.remove(posicao);
            return true;
        }

    }

    public E recuperar(int numero) {

        int posicao = localizar(numero);
        if (posicao == -1) {
            return null;
        }
        else {
            return lista.get(posicao);
        }
    }

    private int localizar(int numero) {
        for(int i = 0; i < lista.size(); i++) {

            if (lista.get(i).equals(numero)) {

                return i;
            }
        }
        return -1;
    }

    public boolean vazia() {
        return lista.isEmpty();
    }


    public E recuperarPrimeiro() {
        corrente = 0;
        return lista.get(corrente);
    }

    public E recuperarProximo() {
        if (corrente != -1) {
            corrente++;
            if (corrente < lista.size()) {
                return lista.get(corrente);
            }
            else {
                corrente = -1;
                return null;
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException(
                    "Tentando acessar uma posição inválida do array.");
        }
    }
}

