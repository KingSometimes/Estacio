package cadastrobd.model;

public class PessoasFisicas extends Pessoas {
    private String CPF;

    // Construtor padrão
    public PessoasFisicas() {
    }

    // Construtor completo
    public PessoasFisicas(int ID, String Nome, String Endereco, String Telefone, String cpf) {
        super(ID, Nome, Endereco, Telefone);
        this.CPF = cpf;
    }

    // Método para exibir os dados
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + CPF);
    }

    // Getters e setters
    public String getCpf() {
        return CPF;
    }

    public void setCpf(String cpf) {
        this.CPF = cpf;
    }
}
