/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ramon
 */
@Entity
@Table(name = "Pessoas")
@NamedQueries({
    @NamedQuery(name = "Pessoas.findAll", query = "SELECT p FROM Pessoas p"),
    @NamedQuery(name = "Pessoas.findById", query = "SELECT p FROM Pessoas p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoas.findByNome", query = "SELECT p FROM Pessoas p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoas.findByEndereco", query = "SELECT p FROM Pessoas p WHERE p.endereco = :endereco"),
    @NamedQuery(name = "Pessoas.findByTelefone", query = "SELECT p FROM Pessoas p WHERE p.telefone = :telefone")})
public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Endereco")
    private String endereco;
    @Column(name = "Telefone")
    private String telefone;
    @OneToMany(mappedBy = "pessoasID")
    private Collection<PessoasJuridicas> pessoasJuridicasCollection;
    @JoinColumn(name = "MovimentosVenda_ID", referencedColumnName = "ID")
    @ManyToOne
    private MovimentosVenda movimentosVendaID;
    @JoinColumn(name = "Usuarios_ID", referencedColumnName = "ID")
    @ManyToOne
    private Usuarios usuariosID;
    @OneToMany(mappedBy = "pessoasID")
    private Collection<PessoasFisicas> pessoasFisicasCollection;

    public Pessoas() {
    }

    public Pessoas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Collection<PessoasJuridicas> getPessoasJuridicasCollection() {
        return pessoasJuridicasCollection;
    }

    public void setPessoasJuridicasCollection(Collection<PessoasJuridicas> pessoasJuridicasCollection) {
        this.pessoasJuridicasCollection = pessoasJuridicasCollection;
    }

    public MovimentosVenda getMovimentosVendaID() {
        return movimentosVendaID;
    }

    public void setMovimentosVendaID(MovimentosVenda movimentosVendaID) {
        this.movimentosVendaID = movimentosVendaID;
    }

    public Usuarios getUsuariosID() {
        return usuariosID;
    }

    public void setUsuariosID(Usuarios usuariosID) {
        this.usuariosID = usuariosID;
    }

    public Collection<PessoasFisicas> getPessoasFisicasCollection() {
        return pessoasFisicasCollection;
    }

    public void setPessoasFisicasCollection(Collection<PessoasFisicas> pessoasFisicasCollection) {
        this.pessoasFisicasCollection = pessoasFisicasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoas)) {
            return false;
        }
        Pessoas other = (Pessoas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pessoas[ id=" + id + " ]";
    }
    
}
