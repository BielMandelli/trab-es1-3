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

@WebServlet("/endereco/id")
public class ObterEnderecoPorIDServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCEnderecoGeralServicos enderecoGeralServicos = new UCEnderecoGeralServicos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            Endereco endereco = enderecoGeralServicos.obterEnderecoPorId(id);

            String enderecoResponse = objectMapper.writeValueAsString(endereco);

            response.getWriter().write(enderecoResponse);
        } catch (Exception e) {
            String errorJson =  objectMapper.writeValueAsString(e);
            response.setStatus(500);
            response.getWriter().println(errorJson);
        }
    }
}
