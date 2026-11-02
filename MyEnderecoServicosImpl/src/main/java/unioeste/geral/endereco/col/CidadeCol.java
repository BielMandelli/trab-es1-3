package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.UnidadeFederativa;

public class CidadeCol {
    private UnidadeFederativaCol unidadeFederativaCol;

    public CidadeCol(){
        this.unidadeFederativaCol = new UnidadeFederativaCol();
    }

    public boolean validarId(Long id){
        return id != null && id > 0;
    }

    public boolean validarNome(String nome){
        return nome != null && !nome.isBlank();
    }

    public boolean validarUnidadeFederativa(UnidadeFederativa unidadeFederativa){
        return unidadeFederativaCol.validarUnidadeFederativa(unidadeFederativa);
    }

    public boolean validarCidade(Cidade cidade){
        return cidade != null
                && validarId(cidade.getIdCidade())
                && validarNome(cidade.getNomeCidade())
                && validarUnidadeFederativa(cidade.getUnidadeFederativa());
    }

    public boolean validarCidadeForm(Cidade cidade){
        return cidade != null
                && validarNome(cidade.getNomeCidade());
    }
}
