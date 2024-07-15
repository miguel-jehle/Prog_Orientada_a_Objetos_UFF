package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.DAOGenerico;
import com.carlosribeiro.util.Id;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DAOGenericoImpl<V extends Id> implements DAOGenerico<V> {

    protected final LinkedHashMap<Integer, V> map = new LinkedHashMap<>(16);

    public V incluir(V obj) {
        return map.put(obj.getId(), obj);
    }

    public V alterar(V obj) {
        return map.put(obj.getId(), obj);
    }

    public V remover(Integer id) {
        return map.remove(id);
    }

    public V recuperarPorId(Integer id) {
        return map.get(id);
    }

    public List<V> recuperarTodos() {
        return new ArrayList<>(map.values()); // .stream().toList();
    }
}
