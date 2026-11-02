package endereco;

import com.fasterxml.jackson.databind.ObjectMapper;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.manager.UCEnderecoGeralServicos;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/endereco")
public class ObterEnderecosServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCEnderecoGeralServicos enderecoGeralServicos = new UCEnderecoGeralServicos();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Endereco> enderecos = enderecoGeralServicos.obterEnderecos();
            String json = objectMapper.writeValueAsString(enderecos);
            resp.getWriter().write(json);
        } catch (Exception e) {
            String error = objectMapper.writeValueAsString(e);
            resp.setStatus(500);
            resp.getWriter().println(error);
        }
    }
}
