package endereco;

import com.fasterxml.jackson.databind.ObjectMapper;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/endereco/cadastrar")
public class CadastrarEnderecoServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCEnderecoGeralServicos enderecoGeralServicos = new UCEnderecoGeralServicos();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Endereco enderecoForm = objectMapper.readValue(request.getReader(), Endereco.class);
            enderecoGeralServicos.cadastrarEndereco(enderecoForm);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println(
                    objectMapper.writeValueAsString("Endereço cadastrado com sucesso")
            );
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().println(
                    objectMapper.writeValueAsString(e.getMessage())
            );
            e.printStackTrace();
        }
    }
}
