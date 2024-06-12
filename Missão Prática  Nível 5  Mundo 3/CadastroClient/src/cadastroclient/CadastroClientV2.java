package cadastroclient;

import cadastroclientv2.ClientAsync;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CadastroClientV2 {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Digite o login: ");
            String login = reader.readLine();
            System.out.println("Digite a senha: ");
            String senha = reader.readLine();

            output.writeObject(login);
            output.writeObject(senha);

            Thread thread = new Thread(new ClientAsync(input));
            thread.start();

            OUTER:
            while (true) {
                System.out.println("Escolha uma opção:");
                System.out.println("L - Listar");
                System.out.println("X - Finalizar");
                System.out.println("E - Entrada");
                System.out.println("S - Saída");
                String comando = reader.readLine().toUpperCase();
                switch (comando) {
                    case "L", "X" -> {
                        output.writeObject(comando);
                        if (comando.equals("X")) {
                            break OUTER;
                        }
                    }
                    case "E", "S" -> {
                        output.writeObject(comando);
                        System.out.println("Digite o ID da pessoa: ");
                        String idPessoa = reader.readLine();
                        output.writeObject(idPessoa);
                        System.out.println("Digite o ID do produto: ");
                        String idProduto = reader.readLine();
                        output.writeObject(idProduto);
                        System.out.println("Digite a quantidade: ");
                        String quantidade = reader.readLine();
                        output.writeObject(quantidade);
                        System.out.println("Digite o valor unitário: ");
                        String valorUnitario = reader.readLine();
                        output.writeObject(valorUnitario);
                    }
                    default -> System.out.println("Comando inválido!");
                }
            }

        } catch (Exception ex) {
            System.out.println("Erro no cliente: " + ex.getMessage());
        }
    }
}
