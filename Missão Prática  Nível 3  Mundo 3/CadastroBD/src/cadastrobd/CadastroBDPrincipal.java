package cadastrobd;

import cadastrobd.model.PessoasFisicas;
import cadastrobd.model.PessoasFisicasDAO;
import cadastrobd.model.PessoasJuridicas;
import cadastrobd.model.PessoasJuridicasDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CadastroBDPrincipal {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                System.out.println("Selecione uma opção:");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Exibir pelo ID");
                System.out.println("5. Exibir todos");
                System.out.println("0. Finalizar");

                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer de entrada

                switch (opcao) {
                    case 1 ->
                        incluir(scanner);
                    case 2 ->
                        alterar(scanner);
                    case 3 ->
                        excluir(scanner);
                    case 4 ->
                        exibirPorId(scanner);
                    case 5 ->
                        exibirTodos();
                    case 0 ->
                        System.out.println("Finalizando o programa...");
                    default ->
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. O programa será encerrado.");
        }
    }

    private static void incluir(Scanner scanner) {
        System.out.println("Selecione o tipo de pessoa (1 para Física, 2 para Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada
        switch (tipo) {
            case 1 ->
                incluirPessoaFisica(scanner);
            case 2 ->
                incluirPessoaJuridica(scanner);
            default ->
                System.out.println("Tipo de pessoa inválido.");
        }
    }

    private static void incluirPessoaFisica(Scanner scanner) {
        // Obter dados da pessoa física do usuário
        System.out.println("Digite o nome da pessoa física:");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF da pessoa física:");
        String cpf = scanner.nextLine();

        System.out.println("Digite o endereço da pessoa física:");
        String endereco = scanner.nextLine();

        System.out.println("Digite o telefone da pessoa física:");
        String telefone = scanner.nextLine();

        // Criar objeto PessoasFisicas e persistir no banco de dados
        PessoasFisicas pessoaFisica = new PessoasFisicas();
        pessoaFisica.setNome(nome);
        pessoaFisica.setCpf(cpf);
        pessoaFisica.setEndereco(endereco);
        pessoaFisica.setTelefone(telefone);

        try {
            PessoasFisicasDAO pessoaFisicaDAO = new PessoasFisicasDAO();
            pessoaFisicaDAO.incluir(pessoaFisica);
            System.out.println("Pessoa física incluída com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao incluir pessoa física: " + e.getMessage());
        }
    }

    private static void incluirPessoaJuridica(Scanner scanner) {
        // Obter dados da pessoa jurídica do usuário
        System.out.println("Digite o nome da pessoa jurídica:");
        String nome = scanner.nextLine();

        System.out.println("Digite o CNPJ da pessoa jurídica:");
        String cnpj = scanner.nextLine();

        System.out.println("Digite o endereço da pessoa jurídica:");
        String endereco = scanner.nextLine();

        System.out.println("Digite o telefone da pessoa jurídica:");
        String telefone = scanner.nextLine();

        // Criar objeto PessoasJuridicas e persistir no banco de dados
        PessoasJuridicas pessoaJuridica = new PessoasJuridicas();
        pessoaJuridica.setNome(nome);
        pessoaJuridica.setCnpj(cnpj);
        pessoaJuridica.setEndereco(endereco);
        pessoaJuridica.setTelefone(telefone);

        // Chamar o método para incluir a pessoa jurídica no banco de dados
        try {
            PessoasJuridicasDAO pessoaJuridicaDAO = new PessoasJuridicasDAO();
            pessoaJuridicaDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa jurídica incluída com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao incluir pessoa jurídica: " + ex.getMessage());
        }
    }

    private static void alterar(Scanner scanner) {
        System.out.println("Selecione o tipo de pessoa para alterar (1 para Física, 2 para Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Digite o ID da pessoa:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada
        switch (tipo) {
            case 1 ->
                alterarPessoaFisica(scanner, id);
            case 2 ->
                alterarPessoaJuridica(scanner, id);
            default ->
                System.out.println("Tipo de pessoa inválido.");
        }
    }

    private static void alterarPessoaFisica(Scanner scanner, int id) {
        try {
            // Instanciar o DAO para acessar os métodos de acesso ao banco de dados
            PessoasFisicasDAO pessoaFisicaDAO = new PessoasFisicasDAO();

            // Obter a pessoa física com o ID informado
            PessoasFisicas pessoaFisica = pessoaFisicaDAO.getPessoas(id);

            if (pessoaFisica != null) {
                // Exibir os dados atuais da pessoa física
                System.out.println("Nome atual: " + pessoaFisica.getNome());
                System.out.println("CPF atual: " + pessoaFisica.getCpf());
                System.out.println("Endereço atual: " + pessoaFisica.getEndereco());
                System.out.println("Telefone atual: " + pessoaFisica.getTelefone());

                // Solicitar os novos dados da pessoa física ao usuário
                System.out.println("Digite o novo nome:");
                String novoNome = scanner.nextLine();
                System.out.println("Digite o novo CPF:");
                String novoCpf = scanner.nextLine();
                System.out.println("Digite o novo endereço:");
                String novoEndereco = scanner.nextLine();
                System.out.println("Digite o novo telefone:");
                String novoTelefone = scanner.nextLine();

                // Atualizar os dados da pessoa física
                pessoaFisica.setNome(novoNome);
                pessoaFisica.setCpf(novoCpf);
                pessoaFisica.setEndereco(novoEndereco);
                pessoaFisica.setTelefone(novoTelefone);

                // Chamar o método para alterar a pessoa física no banco de dados
                pessoaFisicaDAO.alterar(pessoaFisica);
                System.out.println("Pessoa física alterada com sucesso!");
            } else {
                System.out.println("Pessoa física não encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar pessoa física: " + e.getMessage());
        }
    }

    private static void alterarPessoaJuridica(Scanner scanner, int id) {
    try {
        // Instanciar o DAO para acessar os métodos de acesso ao banco de dados
        PessoasJuridicasDAO pessoaJuridicaDAO = new PessoasJuridicasDAO();
        
        // Obter a pessoa jurídica com o ID informado
        PessoasJuridicas pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

        if (pessoaJuridica != null) {
            // Exibir os dados atuais da pessoa jurídica
            System.out.println("Nome atual: " + pessoaJuridica.getNome());
            System.out.println("CNPJ atual: " + pessoaJuridica.getCnpj());
            System.out.println("Endereço atual: " + pessoaJuridica.getEndereco());
            System.out.println("Telefone atual: " + pessoaJuridica.getTelefone());
            
            // Solicitar os novos dados da pessoa jurídica ao usuário
            System.out.println("Digite o novo nome:");
            String novoNome = scanner.nextLine();
            System.out.println("Digite o novo CNPJ:");
            String novoCnpj = scanner.nextLine();
            System.out.println("Digite o novo endereço:");
            String novoEndereco = scanner.nextLine();
            System.out.println("Digite o novo telefone:");
            String novoTelefone = scanner.nextLine();
            
            // Atualizar os dados da pessoa jurídica
            pessoaJuridica.setNome(novoNome);
            pessoaJuridica.setCnpj(novoCnpj);
            pessoaJuridica.setEndereco(novoEndereco);
            pessoaJuridica.setTelefone(novoTelefone);

            // Chamar o método para alterar a pessoa jurídica no banco de dados
            pessoaJuridicaDAO.alterar(pessoaJuridica);
            System.out.println("Pessoa jurídica alterada com sucesso!");
        } else {
            System.out.println("Pessoa jurídica não encontrada com o ID informado.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao alterar pessoa jurídica: " + e.getMessage());
    }
}


    private static void excluir(Scanner scanner) {
        System.out.println("Selecione o tipo de pessoa para excluir (1 para Física, 2 para Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Digite o ID da pessoa:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada
        switch (tipo) {
            case 1 ->
                excluirPessoaFisica(id);
            case 2 ->
                excluirPessoaJuridica(id);
            default ->
                System.out.println("Tipo de pessoa inválido.");
        }
    }

    private static void excluirPessoaFisica(int id) {
        try {
            PessoasFisicasDAO pessoaFisicaDAO = new PessoasFisicasDAO();
            pessoaFisicaDAO.excluir(id);
            System.out.println("Pessoa física excluída com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir pessoa física: " + e.getMessage());
        }
    }

    private static void excluirPessoaJuridica(int id) {
        try {
            PessoasJuridicasDAO pessoaJuridicaDAO = new PessoasJuridicasDAO();
            pessoaJuridicaDAO.excluir(id);
            System.out.println("Pessoa jurídica excluída com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir pessoa jurídica: " + e.getMessage());
        }
    }

    private static void exibirPorId(Scanner scanner) {
        System.out.println("Selecione o tipo de pessoa para exibir pelo ID (1 para Física, 2 para Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        System.out.println("Digite o ID da pessoa:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada
        switch (tipo) {
            case 1 ->
                exibirPessoaFisicaPorId(id);
            case 2 ->
                exibirPessoaJuridicaPorId(id);
            default ->
                System.out.println("Tipo de pessoa inválido.");
        }
    }

    private static void exibirPessoaFisicaPorId(int id) {
        try {
            PessoasFisicasDAO pessoaFisicaDAO = new PessoasFisicasDAO();
            PessoasFisicas pessoaFisica = pessoaFisicaDAO.getPessoas(id);

            if (pessoaFisica != null) {
                System.out.println("Dados da pessoa física:");
                System.out.println("ID: " + pessoaFisica.getId());
                System.out.println("Nome: " + pessoaFisica.getNome());
                System.out.println("CPF: " + pessoaFisica.getCpf());
            } else {
                System.out.println("Pessoa física não encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir pessoa física por ID: " + e.getMessage());
        }
    }

    private static void exibirPessoaJuridicaPorId(int id) {
        try {
            PessoasJuridicasDAO pessoaJuridicaDAO = new PessoasJuridicasDAO();
            PessoasJuridicas pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

            if (pessoaJuridica != null) {
                System.out.println("Dados da pessoa jurídica:");
                System.out.println("ID: " + pessoaJuridica.getId());
                System.out.println("Nome: " + pessoaJuridica.getNome());
                System.out.println("CNPJ: " + pessoaJuridica.getCnpj());
            } else {
                System.out.println("Pessoa jurídica não encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir pessoa jurídica por ID: " + e.getMessage());
        }
    }

    private static void exibirTodos() {
        try {
            PessoasFisicasDAO pessoaFisicaDAO = new PessoasFisicasDAO();
            List<PessoasFisicas> pessoasFisicas = pessoaFisicaDAO.getPessoas();

            System.out.println("Pessoas físicas cadastradas:");
            for (PessoasFisicas pessoaFisica : pessoasFisicas) {
                System.out.println("ID: " + pessoaFisica.getId() + ", Nome: " + pessoaFisica.getNome() + ", CPF: " + pessoaFisica.getCpf());
            }

            PessoasJuridicasDAO pessoaJuridicaDAO = new PessoasJuridicasDAO();
            List<PessoasJuridicas> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();

            System.out.println("Pessoas jurídicas cadastradas:");
            for (PessoasJuridicas pessoaJuridica : pessoasJuridicas) {
                System.out.println("ID: " + pessoaJuridica.getId() + ", Nome: " + pessoaJuridica.getNome() + ", CNPJ: " + pessoaJuridica.getCnpj());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir todas as pessoas: " + e.getMessage());
        }
    }
}
