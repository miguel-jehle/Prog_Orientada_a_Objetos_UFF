package com.migueljehle.util;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FabricaDeDaos{
    private static final Map<Class<?>, Object> map = new HashMap<>();
    public static <T> T getDAO(Class<T> tipo) {
        Object obj = map.get(tipo);

        //Verifico se existe, caso sim, retorno, se não, vamos criar
        if (obj == null) {
            //Acesso as classes de implementação
            Reflections reflections = new Reflections("com.migueljehle.dao.impl");
            //Acesso a específica da classe do meu objeto
            Set<Class<? extends T>> conjunto = reflections.getSubTypesOf(tipo);
            //Verifico se tem mais de uma que implemente o meu objeto, se sim, tá errado!
            if (conjunto.size() != 1) {
                throw new RuntimeException( "Deve haver uma e apenas uma classe que implementa a interface " + tipo.getName());
            }
            //Caso não, eu acesso essa classe de implementação
            Class<? extends T> classe = conjunto.iterator().next();
            try {
                //Nela, mando gerar o meu nome objeto
                obj = classe.getDeclaredConstructor().newInstance();

                //Insiro ele no map, e mando retornar
                map.put(tipo, obj);
            } catch (InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return (T) obj;
    }
}
