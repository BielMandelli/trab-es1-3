package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Logradouro implements Serializable {

    private Long idLogradouro;
    private String nomeLogradouro;
    private TipoLogradouro tipoLogradouro;

    public Logradouro() {
    }

    public Logradouro(Long idLogradouro, String nomeLogradouro, TipoLogradouro tipoLogradouro) {
        this.idLogradouro = idLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.tipoLogradouro = tipoLogradouro;
    }

    public Long getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(Long idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}