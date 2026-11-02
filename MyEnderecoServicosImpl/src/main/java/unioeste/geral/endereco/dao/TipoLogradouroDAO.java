package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.TipoLogradouro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoLogradouroDAO {
    private final Connection conexao;

    public TipoLogradouroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    private TipoLogradouro criarTipoLogradouro(ResultSet rs) throws Exception {
        String nome = rs.getString("nomeLogradouro");
        String sigla = rs.getString("siglaLogradouro");
        return new TipoLogradouro(nome, sigla);
    }

    public TipoLogradouro obterTipoLogradouroPorSigla(String sigla) throws Exception {
        String sql = "SELECT * FROM tipo_logradouro WHERE \"siglaLogradouro\" = ?";
        TipoLogradouro tipoLogradouro = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, sigla);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tipoLogradouro = criarTipoLogradouro(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter TipoLogradouro com a sigla " + sigla + ": " + e.getMessage());
        }
        return tipoLogradouro;
    }

    public List<TipoLogradouro> obterTipoLogradouros() throws Exception {
        String sql = "SELECT * FROM tipo_logradouro";
        List<TipoLogradouro> tipoLogradouros = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tipoLogradouros.add(criarTipoLogradouro(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter TipoLogradouros: " + e.getMessage());
        }
        return tipoLogradouros;
    }

    public String inserirTipoLogradouro(TipoLogradouro tipoLogradouro) throws Exception {
        String sql = "INSERT INTO tipo_logradouro (\"siglaLogradouro\", \"nomeLogradouro\") VALUES (?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, tipoLogradouro.getSiglaLogradouro());
            pstmt.setString(2, tipoLogradouro.getNomeLogradouro());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new Exception("Erro ao inserir TipoLogradouro: " + tipoLogradouro.getNomeLogradouro());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir TipoLogradouro: " + tipoLogradouro.getNomeLogradouro(), e);
        }
        return tipoLogradouro.getSiglaLogradouro();
    }
}