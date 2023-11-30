package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FipeResponseComInt(@JsonProperty(value = "codigo") Integer codigo,
                                 @JsonProperty(value = "nome") String nome){
}
