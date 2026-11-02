package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.UnidadeFederativa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadeFederativaDAO {
    private final Connection conexao;

    public UnidadeFederativaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    private UnidadeFederativa criarUnidadeFederativa(ResultSet rs) throws Exception {
        String nome = rs.getString("nomeUF");
        String sigla = rs.getString("siglaUF");
        return new UnidadeFederativa(nome, sigla);
    }

    public UnidadeFederativa obterUnidadeFederativaPorSigla(String sigla) throws Exception {
        String sql = "SELECT * FROM unidade_federativa WHERE \"siglaUF\" = ?";
        UnidadeFederativa unidadeFederativa = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, sigla);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    unidadeFederativa = criarUnidadeFederativa(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter UnidadeFederativa com a sigla " + sigla + ": " + e.getMessage());
        }
        return unidadeFederativa;
    }

    public List<UnidadeFederativa> obterUnidadesFederativas() throws Exception {
        String sql = "SELECT * FROM unidade_federativa";
        List<UnidadeFederativa> unidadeFederativas = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    unidadeFederativas.add(criarUnidadeFederativa(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter UnidadeFederativas: " + e.getMessage());
        }
        return unidadeFederativas;
    }

    public String inserirUnidadeFederativa(UnidadeFederativa unidadeFederativa) throws Exception {
        String sql = "INSERT INTO unidade_federativa (\"siglaUF\", \"nomeUF\") VALUES (?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, unidadeFederativa.getSiglaUF());
            pstmt.setString(2, unidadeFederativa.getNomeUF());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new Exception("Erro ao inserir UnidadeFederativa: " + unidadeFederativa.getNomeUF());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir UnidadeFederativa: " + unidadeFederativa.getNomeUF(), e);
        }
        return unidadeFederativa.getSiglaUF();
    }
}