package cadastroserver;

import controller.MovimentosVendaJpaController;
import java.io.*;
import java.sql.*;
import java.util.List;
import javax.persistence.*;
import model.Produtos;
import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServer {

    private static final String PERSISTENCE_UNIT_NAME = "CadastroServerPU";
    private static EntityManagerFactory factory;
    
    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProdutosJpaController ctrlProd = new ProdutosJpaController();
        UsuariosJpaController ctrlUsu = new UsuariosJpaController();
        MovimentosVendaJpaController ctrlMov = new MovimentosVendaJpaController();

        try (ServerSocket server = new ServerSocket(4321)) {
            while (true) {
                Socket s1 = server.accept();
                CadastroThreadV2 thread = new CadastroThreadV2(ctrlProd, ctrlUsu, ctrlMov, s1);
                thread.start();
            }
        } catch (IOException e) {
        }
        
    }

    private static class CadastroThreadV2 extends Thread {

        private final Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public CadastroThreadV2(ProdutosJpaController produtoController,UsuariosJpaController usuarioController, MovimentosVendaJpaController movimentosVendaController, Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Ler login e senha do cliente
                String login = in.readLine();
                String senha = in.readLine();

                // Validar credenciais
                if (!validarCredenciais(login, senha)) {
                    try (clientSocket) {
                        out.println("Credenciais inválidas");
                    }
                    return;
                }

                out.println("Credenciais válidas");

                // Ciclo de resposta
                String acao;
                while ((acao = in.readLine()) != null) {
                    if (acao.equals("L")) {
                        List<Produtos> produtos = listarProdutos();
                        for (Produtos produto : produtos) {
                            out.println(produto.getId() + " - " + produto.getNome() + " - " + produto.getQuantidade() + " - " + produto.getPreco());
                        }
                    }
                }
            } catch (IOException e) {
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                }
            }
        }

        private static boolean validarCredenciais(String login, String senha) {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;";
            String user = "Nome";
            String password = "Senha";
            String query = "SELECT COUNT(*) FROM Usuarios WHERE Nome = ? AND Senha = ?";

            try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, login);
                stmt.setString(2, senha);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            } catch (SQLException e) {
            }

            return false;
        }

        private List<Produtos> listarProdutos() {
            EntityManager em = factory.createEntityManager();
            List<Produtos> produtos = em.createQuery("SELECT p FROM Produtos p", Produtos.class).getResultList();
            em.close();
            return produtos;
        }
    }
}
