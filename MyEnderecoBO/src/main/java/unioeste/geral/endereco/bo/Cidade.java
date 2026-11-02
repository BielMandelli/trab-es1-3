package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Cidade implements Serializable {

    private Long idCidade;
    private String nomeCidade;
    private UnidadeFederativa unidadeFederativa;

    public Cidade() {
    }

    public Cidade(Long idCidade, String nomeCidade, UnidadeFederativa unidadeFederativa) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.unidadeFederativa = unidadeFederativa;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }
}