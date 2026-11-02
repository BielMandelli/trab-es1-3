package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Bairro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BairroDAO {
    private final Connection conexao;

    public BairroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    private Bairro criarBairro(ResultSet rs) throws Exception {
        Long id = rs.getLong("idBairro");
        String nome = rs.getString("nomeBairro");
        return new Bairro(id, nome);
    }

    public Bairro obterBairroPorId(Long id) throws Exception {
        String sql = "SELECT * FROM bairro WHERE \"idBairro\" = ?";
        Bairro bairro = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bairro = criarBairro(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Bairro com o id " + id + ": " + e.getMessage());
        }
        return bairro;
    }

    public List<Bairro> obterBairros() throws Exception {
        String sql = "SELECT * FROM bairro";
        List<Bairro> bairros = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bairros.add(criarBairro(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Bairros: " + e.getMessage());
        }
        return bairros;
    }

    public Long inserirBairro(Bairro bairro) throws Exception {
        String sql = "INSERT INTO bairro (\"idBairro\", \"nomeBairro\") VALUES (?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, bairro.getIdBairro());
            pstmt.setString(2, bairro.getNomeBairro());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new Exception("Erro ao inserir Bairro: " + bairro.getNomeBairro());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir Bairro: " + bairro.getNomeBairro(), e);
        }
        return bairro.getIdBairro();
    }
}