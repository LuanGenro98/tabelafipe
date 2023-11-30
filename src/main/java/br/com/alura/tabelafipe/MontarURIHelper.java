package br.com.alura.tabelafipe;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class MontarURIHelper {

    private String categoriaEscolhida;
    private Integer marcaEscolhida;

    public URI montarURIDeMarcas(String categoria){
        categoriaEscolhida = categoria;
        StringBuilder path = new StringBuilder();
        path.append("/fipe/api/v1/");
        path.append(categoria);
        path.append("/marcas");
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("parallelum.com.br")
                .path(path.toString())
                .build()
                .toUri();
        return uri;
    }

    public URI montarURIDeModelos(Integer codigo){
        marcaEscolhida = codigo;
        StringBuilder path = new StringBuilder();
        path.append("/fipe/api/v1/");
        path.append(categoriaEscolhida);
        path.append("/marcas/");
        path.append(codigo);
        path.append("/modelos");
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("parallelum.com.br")
                .path(path.toString())
                .build()
                .toUri();
        return uri;
    }

    public URI montarURIDeAnosDoModelo(Integer codigo){
        StringBuilder path = new StringBuilder();
        path.append("/fipe/api/v1/");
        path.append(categoriaEscolhida);
        path.append("/marcas/");
        path.append(marcaEscolhida);
        path.append("/modelos/");
        path.append(codigo);
        path.append("/anos");
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("parallelum.com.br")
                .path(path.toString())
                .build()
                .toUri();
        return uri;
    }

    public URI montarURIDeModelosPorAno(Integer modelo, String codigoAno){
        StringBuilder path = new StringBuilder();
        path.append("/fipe/api/v1/");
        path.append(categoriaEscolhida);
        path.append("/marcas/");
        path.append(marcaEscolhida);
        path.append("/modelos/");
        path.append(modelo);
        path.append("/anos/");
        path.append(codigoAno);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("parallelum.com.br")
                .path(path.toString())
                .build()
                .toUri();
        return uri;
    }
}
