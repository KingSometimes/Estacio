package cadastrobd.model;

public class PessoasJuridicas extends Pessoas {
    private String CNPJ;

    // Construtor padrão
    public PessoasJuridicas() {
    }

    // Construtor completo
    public PessoasJuridicas(int ID, String Nome, String Endereco, String Telefone, String CNPJ) {
        super(ID, Nome, Endereco, Telefone);
        this.CNPJ = CNPJ;
    }

    // Método para exibir os dados
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + CNPJ);
    }

    // Getters e setters
    public String getCnpj() {
        return CNPJ;
    }

    public void setCnpj(String cnpj) {
        this.CNPJ = cnpj;
    }
}
