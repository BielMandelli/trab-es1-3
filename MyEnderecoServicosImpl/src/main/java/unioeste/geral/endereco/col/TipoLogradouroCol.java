package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.TipoLogradouro;

public class TipoLogradouroCol {
    public boolean validarId(Long id){
        return id != null && id > 0;
    }

    public boolean validarNome(String nome){
        return nome != null && !nome.isBlank();
    }

    public boolean validarSigla(String sigla){
        return sigla != null && !sigla.isBlank();
    }

    public boolean validarTipoLogradouro(TipoLogradouro tipoLogradouro){
        return tipoLogradouro != null
                && validarNome(tipoLogradouro.getNomeLogradouro())
                && validarNome(tipoLogradouro.getSiglaLogradouro());
    }

    public boolean validarTipoLogradouroForm(TipoLogradouro tipoLogradouro){
        return tipoLogradouro != null
                && validarNome(tipoLogradouro.getNomeLogradouro())
                && validarNome(tipoLogradouro.getSiglaLogradouro());
    }
}
