package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.UnidadeFederativa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {
    private final Connection conexao;
    private final UnidadeFederativaDAO unidadeFederativaDAO;

    public CidadeDAO(Connection conexao) {
        this.conexao = conexao;
        this.unidadeFederativaDAO = new UnidadeFederativaDAO(conexao);
    }

    private Cidade criarCidade(ResultSet rs) throws Exception {
        Long id = rs.getLong("idCidade");
        String nome = rs.getString("nomeCidade");
        UnidadeFederativa unidadeFederativa = unidadeFederativaDAO.obterUnidadeFederativaPorSigla(rs.getString("siglaUF"));
        return new Cidade(id, nome, unidadeFederativa);
    }

    public Cidade obterCidadePorId(Long id) throws Exception {
        String sql = "SELECT * FROM cidade WHERE \"idCidade\" = ?";
        Cidade cidade = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cidade = criarCidade(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Cidade com o id " + id + ": " + e.getMessage());
        }
        return cidade;
    }

    public List<Cidade> obterCidades() throws Exception {
        String sql = "SELECT * FROM cidade";
        List<Cidade> cidades = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    cidades.add(criarCidade(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Cidades: " + e.getMessage());
        }
        return cidades;
    }

    public Long inserirCidade(Cidade cidade) throws Exception {
        String sql = "INSERT INTO cidade (\"idCidade\", \"nomeCidade\", \"siglaUF\") VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, cidade.getIdCidade());
            pstmt.setString(2, cidade.getNomeCidade());
            pstmt.setString(3, cidade.getUnidadeFederativa().getSiglaUF());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new Exception("Erro ao inserir Cidade: " + cidade.getNomeCidade());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir Cidade: " + cidade.getNomeCidade(), e);
        }
        return cidade.getIdCidade();
    }
}