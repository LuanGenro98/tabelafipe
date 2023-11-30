package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Veiculo(@JsonProperty(value = "TipoVeiculo") Integer tipoVeiculo,
                      @JsonProperty(value = "Valor") String valor,
                      @JsonProperty(value = "Marca") String marca,
                      @JsonProperty(value = "Modelo") String modelo,
                      @JsonProperty(value = "AnoModelo") Integer anoModelo,
                      @JsonProperty(value = "Combustivel") String combustivel,
                      @JsonProperty(value = "CodigoFipe") String codigoFipe,
                      @JsonProperty(value = "MesReferencia") String mesReferencia,
                      @JsonProperty(value = "SiglaCombustivel") String siglaCombustivel) {
}
