package br.com.alura.tabelafipe.service;

import java.util.List;

public interface IConversorDados {

    <T> T converterDados(String json, Class<T> clazz);

    <T> List<T>converterDadosParaLista(String json, Class<T> clazz);
}
