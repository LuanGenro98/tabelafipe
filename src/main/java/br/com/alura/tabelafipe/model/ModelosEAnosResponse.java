package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosEAnosResponse(@JsonProperty(value = "modelos") List<FipeResponseComInt> modelos,
                                   @JsonProperty(value = "anos") List<FipeResponseComString> anos){
}
