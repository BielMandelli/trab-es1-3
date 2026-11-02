package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Logradouro;

public class EnderecoCol {
    private CidadeCol cidadeCol;
    private BairroCol bairroCol;
    private LogradouroCol logradouroCol;

    public EnderecoCol(){
        this.cidadeCol = new CidadeCol();
        this.logradouroCol = new LogradouroCol();
        this.bairroCol = new BairroCol();
    }

    public boolean validarId(Long id){
        return id != null && id > 0;
    }

    public boolean validarCEP(String cep){
        return cep != null && !cep.isBlank() && cep.matches("\\d{8}");
    }

    public boolean validarCidade(Cidade cidade){
        return cidadeCol.validarCidade(cidade);
    }

    public boolean validarBairro(Bairro bairro){
        return bairroCol.validarBairro(bairro);
    }

    public boolean validarLogradouro(Logradouro logradouro){
        return logradouroCol.validarLogradouro(logradouro);
    }

    public boolean validarEndereco(Endereco endereco){
        return endereco != null
                && validarId(endereco.getIdEndereco())
                && validarCEP(endereco.getCEP())
                && validarBairro(endereco.getBairro())
                && validarLogradouro(endereco.getLogradouro())
                && validarCidade(endereco.getCidade());
    }

    public boolean validarNovoEndereco(Endereco endereco){
        return endereco != null
                && validarCEP(endereco.getCEP())
                && endereco.getBairro() != null && bairroCol.validarId(endereco.getBairro().getIdBairro())
                && endereco.getCidade() != null && cidadeCol.validarId(endereco.getCidade().getIdCidade())
                && endereco.getLogradouro() != null && logradouroCol.validarId(endereco.getLogradouro().getIdLogradouro());
    }
}
