package cadastropoo;

import java.io.IOException;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String prefixoArquivos = "dados";

        PessoaFisicaRepo repoPessoaFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoPessoaJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("\nOpções do Programa:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Salvar Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Execução");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 ->
                    incluir(scanner, repoPessoaFisica, repoPessoaJuridica);
                case 2 ->
                    alterar(scanner, repoPessoaFisica, repoPessoaJuridica);
                case 3 ->
                    excluir(scanner, repoPessoaFisica, repoPessoaJuridica);
                case 4 ->
                    exibirPorId(scanner, repoPessoaFisica, repoPessoaJuridica);
                case 5 ->
                    exibirTodos(repoPessoaFisica, repoPessoaJuridica);
                case 6 ->
                    salvarDados(scanner, prefixoArquivos, repoPessoaFisica, repoPessoaJuridica);
                case 7 ->
                    recuperarDados(scanner, prefixoArquivos, repoPessoaFisica, repoPessoaJuridica);
                case 0 ->
                    System.out.println("Finalizando execução...");
                default ->
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void incluir(Scanner scanner, PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        System.out.println("\n1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Escolha o tipo (1 ou 2): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        switch (tipo) {
            case 1 -> {
                System.out.println("Incluindo Pessoa Física...");
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                repoPessoaFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                System.out.println("Pessoa Física incluída com sucesso!");
            }
            case 2 -> {
                System.out.println("Incluindo Pessoa Jurídica...");
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                repoPessoaJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                System.out.println("Pessoa Jurídica incluída com sucesso!");
            }
            default ->
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        System.out.println("\n1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Escolha o tipo (1 ou 2): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("ID da entidade a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        switch (tipo) {
            case 1 -> {
                PessoaFisica pessoaFisica = repoPessoaFisica.obter(id);
                if (pessoaFisica != null) {
                    System.out.println("Dados atuais:");
                    pessoaFisica.exibir();
                    System.out.println("Entre com os novos dados:");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    pessoaFisica.setNome(nome);
                    pessoaFisica.setCpf(cpf);
                    pessoaFisica.setIdade(idade);

                    repoPessoaFisica.alterar(pessoaFisica);
                    System.out.println("Pessoa Física alterada com sucesso!");
                } else {
                    System.out.println("Pessoa Física com o ID especificado não encontrada.");
                }
            }
            case 2 -> {
                PessoaJuridica pessoaJuridica = repoPessoaJuridica.obter(id);
                if (pessoaJuridica != null) {
                    System.out.println("Dados atuais:");
                    pessoaJuridica.exibir();
                    System.out.println("Entre com os novos dados:");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();

                    pessoaJuridica.setNome(nome);
                    pessoaJuridica.setCnpj(cnpj);

                    repoPessoaJuridica.alterar(pessoaJuridica);
                    System.out.println("Pessoa Jurídica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa Jurídica com o ID especificado não encontrada.");
                }
            }
            default ->
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        System.out.println("\n1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Escolha o tipo (1 ou 2): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("ID da entidade a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        switch (tipo) {
            case 1 -> {
                repoPessoaFisica.excluir(id);
                System.out.println("Pessoa Física excluída com sucesso!");
            }
            case 2 -> {
                repoPessoaJuridica.excluir(id);
                System.out.println("Pessoa Jurídica excluída com sucesso!");
            }
            default ->
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void exibirPorId(Scanner scanner, PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        System.out.println("\n1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Escolha o tipo (1 ou 2): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("ID da entidade a ser exibida: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        switch (tipo) {
            case 1 -> {
                PessoaFisica pessoaFisica = repoPessoaFisica.obter(id);
                if (pessoaFisica != null) {
                    System.out.println("Dados da Pessoa Física:");
                    pessoaFisica.exibir();
                } else {
                    System.out.println("Pessoa Física com o ID especificado não encontrada.");
                }
            }
            case 2 -> {
                PessoaJuridica pessoaJuridica = repoPessoaJuridica.obter(id);
                if (pessoaJuridica != null) {
                    System.out.println("Dados da Pessoa Jurídica:");
                    pessoaJuridica.exibir();
                } else {
                    System.out.println("Pessoa Jurídica com o ID especificado não encontrada.");
                }
            }
            default ->
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void exibirTodos(PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        System.out.println("\n1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        System.out.print("Escolha o tipo (1 ou 2): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        switch (tipo) {
            case 1 -> {
                System.out.println("Pessoas Físicas:");
                for (PessoaFisica pessoa : repoPessoaFisica.obterTodos()) {
                    pessoa.exibir();
                    System.out.println();
                }
            }
            case 2 -> {
                System.out.println("Pessoas Jurídicas:");
                for (PessoaJuridica pessoa : repoPessoaJuridica.obterTodos()) {
                    pessoa.exibir();
                    System.out.println();
                }
            }
            default ->
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void salvarDados(Scanner scanner, String prefixoArquivos, PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        try {
            System.out.print("Informe o prefixo dos arquivos: ");
            String prefixo = scanner.nextLine();

            repoPessoaFisica.persistir(prefixo + ".fisica.bin");
            repoPessoaJuridica.persistir(prefixo + ".juridica.bin");

            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados(Scanner scanner, String prefixoArquivos, PessoaFisicaRepo repoPessoaFisica, PessoaJuridicaRepo repoPessoaJuridica) {
        try {
            System.out.print("Informe o prefixo dos arquivos: ");
            String prefixo = scanner.nextLine();

            repoPessoaFisica.recuperar(prefixo + ".fisica.bin");
            repoPessoaJuridica.recuperar(prefixo + ".juridica.bin");

            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
}
