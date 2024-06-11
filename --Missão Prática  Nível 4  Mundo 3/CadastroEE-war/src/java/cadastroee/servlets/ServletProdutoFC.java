package cadastroee.servlets;

import cadastroee.controler.ProdutosFacadeLocal;
import cadastroee.model.Produtos;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutosFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String destino = "";

        if (acao != null) {
            switch (acao) {
                case "listar":
                    List<Produtos> produtos = facade.findAll();
                    request.setAttribute("produtos", produtos);
                    destino = "ProdutoLista.jsp";
                    break;
                case "formIncluir":
                    destino = "ProdutoDados.jsp";
                    break;
                case "formAlterar":
                    Long idAlterar = Long.valueOf(request.getParameter("id"));
                    Produtos produtoAlterar = facade.find(idAlterar);
                    request.setAttribute("produto", produtoAlterar);
                    destino = "ProdutoDados.jsp";
                    break;
                case "excluir":
                    Long idExcluir = Long.valueOf(request.getParameter("id"));
                    facade.remove(facade.find(idExcluir));
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
                case "alterar":
                    Long idAtualizar = Long.valueOf(request.getParameter("id"));
                    Produtos produtoAtualizar = facade.find(idAtualizar);
                    produtoAtualizar.setNome(request.getParameter("nome"));
                    produtoAtualizar.setPreco(Float.parseFloat(request.getParameter("preco")));
                    facade.edit(produtoAtualizar);
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
                case "incluir":
                    Produtos produtoNovo = new Produtos();
                    produtoNovo.setNome(request.getParameter("nome"));
                    produtoNovo.setPreco(Float.parseFloat(request.getParameter("preco")));
                    facade.create(produtoNovo);
                    request.setAttribute("produtos", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
                default:
                    break;
            }
        }

        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
