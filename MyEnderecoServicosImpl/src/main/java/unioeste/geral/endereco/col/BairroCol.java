package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.Bairro;

public class BairroCol {
    public boolean validarId(Long id){
        return id != null && id > 0;
    }

    public boolean validarNome(String nome){
        return nome != null && !nome.isBlank();
    }

    public boolean validarBairro(Bairro bairro){
        return bairro != null && validarId(bairro.getIdBairro()) && validarNome(bairro.getNomeBairro());
    }

    public boolean validarBairroForm(Bairro bairro){
        return bairro != null && validarNome(bairro.getNomeBairro());
    }
}
