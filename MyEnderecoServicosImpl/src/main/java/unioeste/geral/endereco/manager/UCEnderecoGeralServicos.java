package unioeste.geral.endereco.manager;

import unioeste.apoio.bd.ConexaoBD;
import unioeste.geral.endereco.bo.*;
import unioeste.geral.endereco.col.*;
import unioeste.geral.endereco.dao.*;
import unioeste.geral.endereco.exception.EnderecoException;
import unioeste.geral.endereco.externo.ViaCEPApi;

import java.sql.Connection;
import java.util.List;

public class UCEnderecoGeralServicos {
    private final EnderecoCol enderecoCOL;
    private final CidadeCol cidadeCOL;
    private final BairroCol bairroCOL;
    private final LogradouroCol logradouroCOL;
    private final TipoLogradouroCol tipoLogradouroCOL;
    private final UnidadeFederativaCol unidadeFederativaCOL;
    private final ConexaoBD conexaoBD;
    private final ViaCEPApi viaCEPAPI;

    public UCEnderecoGeralServicos(){
        enderecoCOL = new EnderecoCol();
        cidadeCOL = new CidadeCol();
        bairroCOL = new BairroCol();
        logradouroCOL = new LogradouroCol();
        tipoLogradouroCOL = new TipoLogradouroCol();
        unidadeFederativaCOL = new UnidadeFederativaCol();
        conexaoBD = new ConexaoBD();
        viaCEPAPI = new ViaCEPApi();
    }

    public List<Endereco> obterEnderecos() throws Exception{
        List<Endereco> enderecos;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            EnderecoDAO enderecoDAO = new EnderecoDAO(conexao);

            conexao.setAutoCommit(false);

            enderecos = enderecoDAO.obterEnderecos();
            conexao.commit();
        }

        return enderecos;
    }

    public List<Endereco> obterEnderecosPorCEP(String cep) throws Exception{
        if(!enderecoCOL.validarCEP(cep)){
            throw new EnderecoException("CEP inválido: " + cep);
        }

        List<Endereco> enderecos;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            EnderecoDAO enderecoDAO = new EnderecoDAO(conexao);

            conexao.setAutoCommit(false);
            enderecos = enderecoDAO.obterEnderecosPorCEP(cep);
            conexao.commit();
        }

        return enderecos;
    }

    public Endereco obterEnderecoPorId(Long id) throws Exception{
        if(!enderecoCOL.validarId(id)){
            throw new EnderecoException("Código inválido: " + id);
        }

        Endereco endereco;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            EnderecoDAO enderecoDAO = new EnderecoDAO(conexao);

            conexao.setAutoCommit(false);

            endereco = enderecoDAO.obterEnderecoPorId(id);

            conexao.commit();

            if(endereco == null){
                throw new EnderecoException("Não foi possível encontrar endereço com código " + id);
            }
        }

        return endereco;
    }

    public Endereco obterEnderecoExterno(String cep) throws Exception{
        if(!enderecoCOL.validarCEP(cep)){
            throw new EnderecoException("CEP inválido: " + cep);
        }

        return viaCEPAPI.obterEnderecoPorCEP(cep);
    }

    public Endereco cadastrarEndereco(Endereco endereco) throws Exception{
        if(!enderecoCOL.validarNovoEndereco(endereco)){
            throw new EnderecoException("Endereço inválido");
        }

        try(Connection conexao = conexaoBD.getConexaoBD()){
            EnderecoDAO enderecoDAO = new EnderecoDAO(conexao);

            conexao.setAutoCommit(false);
            Long idEndereco = enderecoDAO.inserirEndereco(endereco);
            endereco.setIdEndereco(idEndereco);

            conexao.commit();
        }

        return endereco;
    }

    public Cidade obterCidadePorId(Long id) throws Exception{
        if(!cidadeCOL.validarId(id)){
            throw new EnderecoException("Id inválido: " + id);
        }

        Cidade cidade;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            CidadeDAO cidadeDAO = new CidadeDAO(conexao);

            conexao.setAutoCommit(false);

            cidade = cidadeDAO.obterCidadePorId(id);

            conexao.commit();

            if(cidade == null){
                throw new EnderecoException("Não foi possível encontrar cidade com id " + id);
            }

        }

        return cidade;
    }

    public List<Cidade> obterCidades() throws Exception{
        List<Cidade> cidades;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            CidadeDAO cidadeDAO = new CidadeDAO(conexao);

            conexao.setAutoCommit(false);

            cidades = cidadeDAO.obterCidades();
            conexao.commit();
        }

        return cidades;
    }

    public List<Logradouro> obterLogradouros() throws Exception {
        List<Logradouro> logradouros;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            LogradouroDAO logradouroDAO = new LogradouroDAO(conexao);

            conexao.setAutoCommit(false);

            logradouros = logradouroDAO.obterLogradouros();
            conexao.commit();
        }

        return logradouros;
    }

    public List<Bairro> obterBairros() throws Exception {
        List<Bairro> bairros;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            BairroDAO bairroDAO = new BairroDAO(conexao);

            conexao.setAutoCommit(false);
            bairros = bairroDAO.obterBairros();
            conexao.commit();
        }

        return bairros;
    }

    public List<UnidadeFederativa> obterUnidadeFederativas() throws Exception {
        List<UnidadeFederativa> unidadeFederativas;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            UnidadeFederativaDAO unidadeFederativaDAO = new UnidadeFederativaDAO(conexao);

            conexao.setAutoCommit(false);

            unidadeFederativas = unidadeFederativaDAO.obterUnidadesFederativas();
            conexao.commit();
        }

        return unidadeFederativas;
    }

    public List<TipoLogradouro> obterTiposLogradouros() throws Exception {
        List<TipoLogradouro> tipoLogradouros;

        try(Connection conexao = conexaoBD.getConexaoBD()){
            TipoLogradouroDAO tipoLogradouroDAO = new TipoLogradouroDAO(conexao);

            conexao.setAutoCommit(false);

            tipoLogradouros = tipoLogradouroDAO.obterTipoLogradouros();
            conexao.commit();
        }

        return tipoLogradouros;
    }
}
