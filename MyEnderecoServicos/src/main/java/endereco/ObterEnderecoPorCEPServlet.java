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
import java.util.List;

@WebServlet("/endereco/cep")
public class ObterEnderecoPorCEPServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCEnderecoGeralServicos enderecoGeralServicos = new UCEnderecoGeralServicos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String cep = String.valueOf(request.getParameter("cep"));
            List<Endereco> enderecos = enderecoGeralServicos.obterEnderecosPorCEP(cep);
            String enderecosResponse = objectMapper.writeValueAsString(enderecos);

            response.getWriter().write(enderecosResponse);
        } catch (Exception e) {
            String errorJson =  objectMapper.writeValueAsString(e);
            response.setStatus(500);
            response.getWriter().println(errorJson);
        }
    }
}
