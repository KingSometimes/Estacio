package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoasFisicasDAO {
    private final Connection conexao;
    
    public PessoasFisicasDAO() throws SQLException {
        this.conexao = ConectorBD.getConnection();
    }

    public PessoasFisicas getPessoas(int id) throws SQLException {
        String sql = "SELECT * FROM Pessoas INNER JOIN PessoasFisicas ON Pessoas.ID = PessoasFisicas.ID WHERE Pessoas.ID = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return criarPessoaFisica(rs);
            }
        }
        return null;
    }

    public List<PessoasFisicas> getPessoas() throws SQLException {
        List<PessoasFisicas> pessoas = new ArrayList<>(255);
        String sql = "SELECT * FROM Pessoas INNER JOIN PessoasFisicas ON Pessoas.ID = PessoasFisicas.ID";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pessoas.add(criarPessoaFisica(rs));
            }
        }
        return pessoas;
    }
    public int getNextPessoaId() throws SQLException {
        String sql = "SELECT NEXT VALUE FOR Seq_Pessoa_ID";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to get next Pessoa ID from sequence");
            }
        }
    }
    public void incluir(PessoasFisicas pessoa) throws SQLException {
        int id = getNextPessoaId();
        String sqlPessoa = "INSERT INTO Pessoas (ID, Nome, Endereco, Telefone) VALUES (?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoasFisicas (ID, CPF) VALUES (?, ?)";
        try (PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conexao.prepareStatement(sqlPessoaFisica)) {
            stmtPessoa.setInt(1, id);
            stmtPessoa.setString(2, pessoa.getNome());
            stmtPessoa.setString(3, pessoa.getEndereco());
            stmtPessoa.setString(4, pessoa.getTelefone());
            stmtPessoa.executeUpdate();            
            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.setString(2, pessoa.getCpf());
            stmtPessoaFisica.executeUpdate();
        }
    }

    

    public void alterar(PessoasFisicas pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoas SET Nome=?, Endereco=?, Telefone=? WHERE ID=?";
        String sqlPessoaFisica = "UPDATE PessoasFisicas SET CPF=? WHERE ID=?";
        try (PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conexao.prepareStatement(sqlPessoaFisica)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getEndereco());
            stmtPessoa.setString(3, pessoa.getTelefone());
            stmtPessoa.setInt(4, pessoa.getId());
            stmtPessoaFisica.setString(1, pessoa.getCpf());
            stmtPessoaFisica.setInt(2, pessoa.getId());
            stmtPessoa.executeUpdate();
            stmtPessoaFisica.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaFisica = "DELETE FROM PessoasFisicas WHERE ID=?";
        String sqlPessoa = "DELETE FROM Pessoas WHERE ID=?";
        try (PreparedStatement stmtPessoaFisica = conexao.prepareStatement(sqlPessoaFisica);
             PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa)) {
            stmtPessoaFisica.setInt(1, id);
            stmtPessoa.setInt(1, id);
            stmtPessoaFisica.executeUpdate();
            stmtPessoa.executeUpdate();
        }
    }

    private PessoasFisicas criarPessoaFisica(ResultSet rs) throws SQLException {
        PessoasFisicas pessoa = new PessoasFisicas();
        pessoa.setId(rs.getInt("ID"));
        pessoa.setNome(rs.getString("Nome"));
        pessoa.setEndereco(rs.getString("Endereco"));
        pessoa.setTelefone(rs.getString("Telefone"));
        pessoa.setCpf(rs.getString("CPF"));
        return pessoa;
    }
}
