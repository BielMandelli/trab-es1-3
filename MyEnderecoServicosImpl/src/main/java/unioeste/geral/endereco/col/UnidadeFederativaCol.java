package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.UnidadeFederativa;

public class UnidadeFederativaCol {
    public boolean validarId(Long id){
        return id != null && id > 0;
    }

    public boolean validarNome(String nome){
        return nome != null && !nome.isBlank();
    }

    public boolean validarSigla(String sigla){
        return sigla != null && !sigla.isBlank();
    }

    public boolean validarUnidadeFederativa(UnidadeFederativa unidadeFederativa){
        return unidadeFederativa != null
                && validarNome(unidadeFederativa.getNomeUF())
                && validarNome(unidadeFederativa.getSiglaUF());
    }

    public boolean validarUnidadeFederativaForm(UnidadeFederativa unidadeFederativa){
        return unidadeFederativa != null
                && validarNome(unidadeFederativa.getNomeUF())
                && validarNome(unidadeFederativa.getSiglaUF());
    }
}
