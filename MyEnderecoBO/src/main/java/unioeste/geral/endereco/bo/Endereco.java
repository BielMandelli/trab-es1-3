package unioeste.geral.endereco.bo;

import java.io.Serializable;

public class Endereco implements Serializable {
    private Long idEndereco;
    private String CEP;
    private Cidade cidade;
    private Logradouro logradouro;
    private Bairro bairro;

    public Endereco() {
    }

    public Endereco(Long idEndereco, String CEP, Cidade cidade,
                    Logradouro logradouro, Bairro bairro) {
        this.idEndereco = idEndereco;
        this.CEP = CEP;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.bairro = bairro;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}