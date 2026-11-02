package unioeste.geral.endereco.dao;

import unioeste.geral.endereco.bo.Bairro;
import unioeste.geral.endereco.bo.Cidade;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.Logradouro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private final Connection conexao;
    private final LogradouroDAO logradouroDAO;
    private final CidadeDAO cidadeDAO;
    private final BairroDAO bairroDAO;

    public EnderecoDAO(Connection conexao) {
        this.conexao = conexao;
        this.logradouroDAO = new LogradouroDAO(conexao);
        this.cidadeDAO = new CidadeDAO(conexao);
        this.bairroDAO = new BairroDAO(conexao);
    }

    private Endereco criarEndereco(ResultSet rs) throws Exception {
        Long id = rs.getLong("idEndereco");
        String cep = rs.getString("CEP");
        Cidade cidade = cidadeDAO.obterCidadePorId(rs.getLong("idCidade"));
        Logradouro logradouro = logradouroDAO.obterLogradouroPorId(rs.getLong("idLogradouro"));
        Bairro bairro = bairroDAO.obterBairroPorId(rs.getLong("idBairro"));
        return new Endereco(id, cep, cidade, logradouro, bairro);
    }

    public Endereco obterEnderecoPorId(Long id) throws Exception {
        String sql = "SELECT * FROM endereco WHERE \"idEndereco\" = ?";
        Endereco endereco = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    endereco = criarEndereco(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Endereco com o id " + id + ": " + e.getMessage());
        }
        return endereco;
    }

    public List<Endereco> obterEnderecos() throws Exception {
        String sql = "SELECT * FROM endereco";
        List<Endereco> enderecos = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enderecos.add(criarEndereco(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao obter Enderecos: " + e.getMessage());
        }
        return enderecos;
    }

    public Long inserirEndereco(Endereco endereco) throws Exception {
        String sql = "INSERT INTO endereco (\"idEndereco\", \"CEP\", \"idCidade\", \"idLogradouro\", \"idBairro\") VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, endereco.getIdEndereco());
            pstmt.setString(2, endereco.getCEP());
            pstmt.setLong(3, endereco.getCidade().getIdCidade());
            pstmt.setLong(4, endereco.getLogradouro().getIdLogradouro());
            pstmt.setLong(5, endereco.getBairro().getIdBairro());

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new Exception("Erro ao inserir Endereco: " + endereco.getCEP());
            }
        } catch (Exception e) {
            throw new Exception("Erro ao inserir Endereco: " + endereco.getCEP(), e);
        }
        return endereco.getIdEndereco();
    }

    public List<Endereco> obterEnderecosPorCEP(String cep) throws Exception {
        String sql = "SELECT * FROM endereco WHERE \"CEP\" = ?";
        List<Endereco> enderecos = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, cep);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enderecos.add(criarEndereco(rs));
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao obter Enderecos: " + e.getMessage(), e);
        }
        return enderecos;
    }
}