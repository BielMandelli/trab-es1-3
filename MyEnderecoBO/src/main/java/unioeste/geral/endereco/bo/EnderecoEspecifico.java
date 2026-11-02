package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class EnderecoEspecifico implements Serializable {
    private Endereco endereco;
    private String complemento;
    private Long nroCasa;

    public EnderecoEspecifico() {
    }

    public EnderecoEspecifico(Endereco endereco, String complemento, Long nroCasa) {
        this.endereco = endereco;
        this.complemento = complemento;
        this.nroCasa = nroCasa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Long getNroCasa() {
        return nroCasa;
    }

    public void setNroCasa(Long nroCasa) {
        this.nroCasa = nroCasa;
    }
}