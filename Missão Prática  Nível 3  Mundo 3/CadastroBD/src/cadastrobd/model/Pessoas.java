package cadastrobd.model;

public class Pessoas {
    private int ID;
    private String Nome;
    private String Endereco;
    private String Telefone;
    
    // Construtor padrão
    public Pessoas() {
    }
    
    // Construtor completo
    public Pessoas(int id, String nome, String endereco, String telefone ) {
        this.ID = id;
        this.Nome = nome;
        this.Endereco = endereco;
        this.Telefone = telefone;
    }

    // Método para exibir os dados
    public void exibir() {
        System.out.println("ID: " + ID);
        System.out.println("Nome: " + Nome);
        System.out.println("Logradouro: " + Endereco);
        System.out.println("Telefone: " + Telefone);
    }

    // Getters e setters
    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

}
