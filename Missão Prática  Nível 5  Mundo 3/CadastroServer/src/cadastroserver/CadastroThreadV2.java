package cadastroserver;

import cadastroserver.CadastroThreadV2.MovimentosVenda;
import controller.MovimentosVendaJpaController;
import controller.ProdutosJpaController;
import controller.UsuariosJpaController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import model.Produtos;
import model.Usuarios;

public class CadastroThreadV2 extends Thread {
    private final ProdutosJpaController ctrlProd;
    private final UsuariosJpaController ctrlUsu;
    private final MovimentosVendaJpaController ctrlMov;
    private final Socket s1;

    public CadastroThreadV2(ProdutosJpaController ctrlProd, UsuariosJpaController ctrlUsu, MovimentosVendaJpaController ctrlMov, Socket s1) {
        this.ctrlProd = ctrlProd;
        this.ctrlUsu = ctrlUsu;
        this.ctrlMov = ctrlMov;
        this.s1 = s1;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(s1.getInputStream())) {

            // Obter o login e a senha
            String login = in.readUTF();
            String senha = in.readUTF();

            // Verificar as credenciais
            Usuarios usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                System.out.println("Credenciais inválidas. Desconectando...");
                return;
            }

            // Loop de resposta
            while (true) {
                // Obter o comando
                String comando = in.readUTF();
                if (comando.equals("L")) {
                    // Enviar a lista de produtos
                    out.writeObject(ctrlProd.findProdutoEntities());
                    out.flush();
                } else if (comando.equals("E") || comando.equals("S")) {
                    // Receber os dados do movimento
                    String idPessoa = in.readUTF();
                    String idProduto = in.readUTF();
                    int quantidade = in.readInt();
                    double valorUnitario = in.readDouble();

                    // Criar o objeto Movimento
                    Movimento movimento = new Movimento();
                    movimento.setUsuario(usuario);
                    movimento.setTipo(comando);
                    movimento.setData(new Date());
                    movimento.setIdPessoa(idPessoa);
                    movimento.setIdProduto(idProduto);
                    movimento.setQuantidade(quantidade);
                    movimento.setValorUnitario(valorUnitario);
                    model.MovimentosVenda MovimentosVenda = null;

                    // Persistir o movimento
                    ctrlMov.create(MovimentosVenda);

                    // Atualizar a quantidade de produtos
                    Produtos produto = ctrlProd.findProduto(idProduto);
                    if (produto != null) {
                        if (comando.equals("E")) {
                            produto.setQuantidade(produto.getQuantidade() + quantidade);
                        } else {
                            int novaQuantidade = produto.getQuantidade() - quantidade;
                            if (novaQuantidade >= 0) {
                                produto.setQuantidade(novaQuantidade);
                            } else {
                                System.out.println("Quantidade insuficiente em estoque para saída.");
                                continue; // Volta para o início do loop
                            }
                        }
                        ctrlProd.edit(produto);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Erro na comunicação: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro inesperado: " + ex.getMessage());
        }
    }

    private static class Movimento {

        public Movimento() {
        }

        private void setUsuario(Usuarios usuario) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setTipo(String comando) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setData(Date date) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setIdPessoa(String idPessoa) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setIdProduto(String idProduto) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setQuantidade(int quantidade) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void setValorUnitario(double valorUnitario) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    public static class MovimentosVenda {

        public MovimentosVenda() {
        }
    }
}
