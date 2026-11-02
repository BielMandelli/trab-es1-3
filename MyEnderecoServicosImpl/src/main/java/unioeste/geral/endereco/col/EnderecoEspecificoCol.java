package unioeste.geral.endereco.col;

import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.EnderecoEspecifico;

public class EnderecoEspecificoCol {
    private final EnderecoCol enderecoCol;

    public EnderecoEspecificoCol(){
        this.enderecoCol = new EnderecoCol();
    }

    public boolean validarEnderecoEspecifico(EnderecoEspecifico enderecoEspecifico){
        return  enderecoEspecifico != null;
    }

    public boolean validarEnderecoEspecificoForm(EnderecoEspecifico enderecoEspecifico){
        return  enderecoEspecifico != null;
    }
}
