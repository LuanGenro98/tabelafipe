package br.com.alura.tabelafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConversorDados implements IConversorDados{

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T converterDados(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao converter JSON para a classe: " + clazz);
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> converterDadosParaLista(String json, Class<T> clazz) {
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao converter JSON para a classe: " + clazz);
            throw new RuntimeException(e);
        }
    }
}
