package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Logradouro;
import unioeste.geral.endereco.bo.TipoLogradouro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogradouroDAO {
    private final Connection conexao;
    private final TipoLogradouroDAO tipoLogradouroDAO;

    public LogradouroDAO(Connection conexao) {
        this.conexao = conexao;
        this.tipoLogradouroDAO = new TipoLogradouroDAO(conexao);
    }

    private Logradouro criarLogradouro(ResultSet rs) throws Exception {
        Long id = rs.getLong("idLogradouro");
        String nome = rs.getString("nomeLogradouro");
        TipoLogradouro tipoLogradouro = tipoLogradouroDAO.obterTipoLogradouroPorSigla(rs.getString("siglaLogradouro"));
        return new Logradouro(id, nome, tipoLogradouro);
    }

    public Logradouro obterLogradouroPorId(Long id) throws Exception {
        String sql = "SELECT * FROM logradouro WHERE \"idLogradouro\" = ?";
        Logradouro logradouro = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    logradouro = criarLogradouro(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Logradouro com o id " + id + ": " + e.getMessage());
        }
        return logradouro;
    }

    public List<Logradouro> obterLogradouros() throws Exception {
        String sql = "SELECT * FROM logradouro";
        List<Logradouro> logradouros = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    logradouros.add(criarLogradouro(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Logradouros: " + e.getMessage());
        }
        return logradouros;
    }

    public Long inserirLogradouro(Logradouro logradouro) throws Exception {
        String sql = "INSERT INTO logradouro (\"idLogradouro\", \"nomeLogradouro\", \"siglaLogradouro\") VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, logradouro.getIdLogradouro());
            pstmt.setString(2, logradouro.getNomeLogradouro());
            pstmt.setString(3, logradouro.getTipoLogradouro().getSiglaLogradouro());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new Exception("Erro ao inserir Logradouro: " + logradouro.getNomeLogradouro());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir Logradouro: " + logradouro.getNomeLogradouro(), e);
        }
        return logradouro.getIdLogradouro();
    }
}