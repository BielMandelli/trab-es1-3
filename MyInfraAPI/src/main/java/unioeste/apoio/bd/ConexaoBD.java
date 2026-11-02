
package unioeste.apoio.bd;

import java.sql.*;

public class ConexaoBD {

    // Valores podem ser sobrescritos por variaveis de ambiente (DB_URL, DB_USER, DB_PASSWORD).
    // Se nao estiverem definidas, usa os valores padrao abaixo.
    private static final String URL = config("DB_URL", "jdbc:postgresql://localhost:5432/enderecos");
    private static final String USUARIO = config("DB_USER", "postgres");
    private static final String SENHA = config("DB_PASSWORD", "123456");

    static {
        // No Tomcat, drivers que estao em WEB-INF/lib NAO sao registrados automaticamente
        // no DriverManager (o Tomcat inicializa o DriverManager antes da aplicacao subir).
        // Por isso o registro explicito e obrigatorio aqui.
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                    "Driver PostgreSQL (org.postgresql.Driver) nao encontrado no classpath. " +
                    "Verifique se postgresql-*.jar esta em WEB-INF/lib.");
        }
    }

    private static String config(String nome, String padrao) {
        String valor = System.getenv(nome);
        return (valor == null || valor.isBlank()) ? padrao : valor;
    }

    public Connection getConexaoBD() throws Exception {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new Exception("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    public void encerrarConexoes(ResultSet resultSet, PreparedStatement stmt, Connection conexao) {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
