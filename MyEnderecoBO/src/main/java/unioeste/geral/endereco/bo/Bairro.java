package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Bairro implements Serializable {

    private Long idBairro;
    private String nomeBairro;

    public Bairro() {
    }

    public Bairro(Long idBairro, String nomeBairro) {
        this.idBairro = idBairro;
        this.nomeBairro = nomeBairro;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }
}