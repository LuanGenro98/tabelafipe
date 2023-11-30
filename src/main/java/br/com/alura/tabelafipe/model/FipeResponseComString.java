package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FipeResponseComString(@JsonProperty(value = "codigo") String codigo,
                                    @JsonProperty(value = "nome") String nome){
}
