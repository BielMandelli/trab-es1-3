package unioeste.geral.endereco.externo;

import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.bo.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ViaCEPApi {
    private static final String VIA_CEP_API_URL = "http://viacep.com.br/ws/";

    public String obterEnderecoViaCepAPI(String urlRequisicao) throws Exception {
        URL url = new URL(urlRequisicao);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("GET");

        if(conexao.getResponseCode() != HttpURLConnection.HTTP_OK){
            throw new RuntimeException("Não foi possível se conectar a API do ViaCEP");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            StringBuilder conteudoBuilder = new StringBuilder();
            String conteudo;

            while ((conteudo = reader.readLine()) != null) {
                conteudoBuilder.append(conteudo);
            }

            return conteudoBuilder.toString();
        }
    }

    public Endereco obterEnderecoPorCEP(String cep) throws Exception {
        String enderecoJSON = obterEnderecoViaCepAPI(VIA_CEP_API_URL + cep + "/json");
        Map<String, String> enderecoMap = converterJSONParaMap(enderecoJSON);

        if(enderecoMap.containsKey("erro")){
            throw new EnderecoException("Não existe endereco com CEP: " + cep);
        }

        return criarEndereco(enderecoMap);
    }

    private Map<String, String> converterJSONParaMap(String json) {
        Map<String, String> map = new HashMap<>();

        json = json.replaceAll("[{}\"]", "");

        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] entry = pair.split(":");
            map.put(entry[0].trim(), entry[1].trim());
        }

        return map;
    }

    private Endereco criarEndereco(Map<String, String> map) {
        String siglaUnidadeFederativa = map.getOrDefault("uf", "");
        String nomeUnidadeFederativa = map.getOrDefault("estado", "");
        String nomeCidade = map.getOrDefault("localidade", "");
        String nomeBairro = map.getOrDefault("bairro", "");
        String nomeLogradouro = map.getOrDefault("logradouro", "");

        String cep = map.getOrDefault("cep", "");

        UnidadeFederativa unidadeFederativa = new UnidadeFederativa(nomeUnidadeFederativa, siglaUnidadeFederativa);
        Cidade cidade = new Cidade(null, nomeCidade, unidadeFederativa);
        Bairro bairro = new Bairro(null, nomeBairro);

        Logradouro logradouro = new Logradouro(null, nomeLogradouro, null);

        return new Endereco(null, cep, cidade, logradouro, bairro);
    }
}
