package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.bo.TipoLogradouro;

public class LogradouroCol {
    private TipoLogradouroCol tipoLogradouroCol;

    public LogradouroCol(){
        this.tipoLogradouroCol = new TipoLogradouroCol();
    }

    public boolean validarId(Long id){
        return id != null && id > 0;
    }

    public boolean validarNome(String nome){
        return nome != null && !nome.isBlank();
    }

    public boolean validarTipoLogradouro(TipoLogradouro tipoLogradouro){
        return tipoLogradouroCol.validarTipoLogradouro(tipoLogradouro);
    }

    public boolean validarLogradouro(Logradouro logradouro){
        return logradouro != null
                && validarId(logradouro.getIdLogradouro())
                && validarNome(logradouro.getNomeLogradouro())
                && validarTipoLogradouro(logradouro.getTipoLogradouro());
    }
    public boolean validarLogradouroForm(Logradouro logradouro){
        return logradouro != null
                && validarNome(logradouro.getNomeLogradouro());
    }
}
