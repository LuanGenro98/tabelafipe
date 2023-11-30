package br.com.alura.tabelafipe;

import br.com.alura.tabelafipe.model.FipeResponseComInt;
import br.com.alura.tabelafipe.model.FipeResponseComString;
import br.com.alura.tabelafipe.model.ModelosEAnosResponse;
import br.com.alura.tabelafipe.model.Veiculo;
import br.com.alura.tabelafipe.service.ConversorDados;
import br.com.alura.tabelafipe.service.FipeApiConsumo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class TabelafipeApplication implements CommandLineRunner {

	private FipeApiConsumo consumoApi = new FipeApiConsumo();

	private MontarURIHelper montarUriHelper = new MontarURIHelper();

	private ConversorDados conversor = new ConversorDados();

	private Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(TabelafipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Deseja analisar carros, motos ou caminhoes?");

		String categoria = teclado.nextLine();
		System.out.println("Carregando informações de marcas de " + categoria +"...");
		URI uri = montarUriHelper.montarURIDeMarcas(categoria);
		String response = consumoApi.consultarApi(uri);
		List<FipeResponseComString> fipeResponse = conversor.converterDadosParaLista(response, FipeResponseComString.class);
		System.out.println("Marca | Código");
		fipeResponse.forEach(f -> System.out.println(f.nome() + " | " + f.codigo()));
		System.out.println("\nAcima estão as marcas de " + categoria + ", escolha uma marca através do código:");

		Integer codigoEscolhido = teclado.nextInt();
		System.out.println("Carregando informações de modelos da marca escolhida...");
		uri = montarUriHelper.montarURIDeModelos(codigoEscolhido);
		response = consumoApi.consultarApi(uri);
		ModelosEAnosResponse modelosResponse = conversor.converterDados(response, ModelosEAnosResponse.class);
		modelosResponse.modelos().stream()
				.map(m -> m.nome())
				.forEach(System.out::println);
		System.out.println("\nAcima estão os modelos da marca escolhida. Agora, escolha um modelo e digite parte do nome:");

		String trechoModelo = teclado.next();
		System.out.println("Carregando informações dos anos disponíveis da marca escolhida...");
		List<Integer> codigosDoModelo = modelosResponse
				.modelos()
				.stream()
				.filter(m -> m.nome().toLowerCase().contains(trechoModelo.toLowerCase()))
				.map(m -> m.codigo())
				.collect(Collectors.toList());

		Map<Integer, List<String>> codigoEAnosDoModelo = new HashMap<>();

		codigosDoModelo.parallelStream().forEach(c->
		{
			URI uri2 = montarUriHelper.montarURIDeAnosDoModelo(c);
			String json = consumoApi.consultarApi(uri2);
			List<FipeResponseComString> anosENomesDoModelo = conversor.converterDadosParaLista(json, FipeResponseComString.class);
			List<String> anosDoModelo = anosENomesDoModelo.stream()
					.map(FipeResponseComString::codigo)
					.toList();
			codigoEAnosDoModelo.put(c, anosDoModelo);
		});

		List<Veiculo> veiculos = new ArrayList<>();
		System.out.println("Carregando informações dos modelos disponíveis em cada ano...");
		codigoEAnosDoModelo.forEach( (modelo, anos) ->
		{
			anos.parallelStream().forEach(ano -> {
				URI uri3 = montarUriHelper.montarURIDeModelosPorAno(modelo, ano);
				String json = consumoApi.consultarApi(uri3);
				Veiculo veiculo = conversor.converterDados(json, Veiculo.class);
				veiculos.add(veiculo);
			});
		});

		veiculos.stream()
				.sorted(Comparator.comparing(Veiculo::anoModelo))
				.forEach(v -> System.out.println(v.modelo() + "  ano: " + v.anoModelo() + " valor: " + v.valor() + " combustível: " + v.combustivel()));
	}

}
