package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoasJuridicasDAO {
    private final Connection conexao;
    
    public PessoasJuridicasDAO() throws SQLException {
        this.conexao = ConectorBD.getConnection();
    }

    public PessoasJuridicas getPessoa(int id) throws SQLException {
        String sql = "SELECT * FROM Pessoas INNER JOIN PessoasJuridicas ON Pessoas.ID = PessoasJuridicas.ID WHERE Pessoas.ID = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return criarPessoaJuridica(rs);
            }
        }
        return null;
    }

    public List<PessoasJuridicas> getPessoas() throws SQLException {
        List<PessoasJuridicas> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoas INNER JOIN PessoasJuridicas ON Pessoas.ID = PessoasJuridicas.ID";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pessoas.add(criarPessoaJuridica(rs));
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
    public void incluir(PessoasJuridicas pessoa) throws SQLException {
        int id = getNextPessoaId();
        String sqlPessoa = "INSERT INTO Pessoas (ID, Nome, Endereco, Telefone) VALUES (?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoasJuridicas (ID, CNPJ) VALUES (?, ?)";
        try (PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conexao.prepareStatement(sqlPessoaJuridica)) {
            stmtPessoa.setInt(1, id);
            stmtPessoa.setString(2, pessoa.getNome());
            stmtPessoa.setString(3, pessoa.getEndereco());
            stmtPessoa.setString(4, pessoa.getTelefone());
            stmtPessoa.executeUpdate();
            
            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.setString(2, pessoa.getCnpj());
            stmtPessoaJuridica.executeUpdate();
        }
    }


    public void alterar(PessoasJuridicas pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoas SET Nome=?, Endereco=?, Telefone=? WHERE ID=?";
        String sqlPessoaJuridica = "UPDATE PessoasJuridicas SET CNPJ=? WHERE id=?";
        try (PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conexao.prepareStatement(sqlPessoaJuridica)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getEndereco());
            stmtPessoa.setString(3, pessoa.getTelefone());
            stmtPessoa.setInt(4, pessoa.getId());
            stmtPessoaJuridica.setString(1, pessoa.getCnpj());
            stmtPessoaJuridica.setInt(2, pessoa.getId());
            stmtPessoa.executeUpdate();
            stmtPessoaJuridica.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaJuridica = "DELETE FROM PessoasJuridicas WHERE ID=?";
        String sqlPessoa = "DELETE FROM Pessoas WHERE ID=?";
        try (PreparedStatement stmtPessoaJuridica = conexao.prepareStatement(sqlPessoaJuridica);
             PreparedStatement stmtPessoa = conexao.prepareStatement(sqlPessoa)) {
            stmtPessoaJuridica.setInt(1, id);
            stmtPessoa.setInt(1, id);
            stmtPessoaJuridica.executeUpdate();
            stmtPessoa.executeUpdate();
        }
    }

    private PessoasJuridicas criarPessoaJuridica(ResultSet rs) throws SQLException {
        PessoasJuridicas pessoa = new PessoasJuridicas();
        pessoa.setId(rs.getInt("id"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setEndereco(rs.getString("Endereco"));
        pessoa.setTelefone(rs.getString("telefone"));
        pessoa.setCnpj(rs.getString("cnpj"));
        return pessoa;
    }
}
