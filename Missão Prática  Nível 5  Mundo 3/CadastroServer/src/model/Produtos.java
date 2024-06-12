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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ramon
 */
@Entity
@Table(name = "Produtos")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findById", query = "SELECT p FROM Produtos p WHERE p.id = :id"),
    @NamedQuery(name = "Produtos.findByNome", query = "SELECT p FROM Produtos p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produtos.findByQuantidade", query = "SELECT p FROM Produtos p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "Produtos.findByPreco", query = "SELECT p FROM Produtos p WHERE p.preco = :preco")})
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Quantidade")
    private Integer quantidade;
    @Column(name = "Preco")
    private Long preco;
    @OneToMany(mappedBy = "produtosID")
    private Collection<MovimentosVenda> movimentosVendaCollection;
    @OneToMany(mappedBy = "produtosID")
    private Collection<MovimentosCompra> movimentosCompraCollection;

    public Produtos() {
    }

    public Produtos(Integer id) {
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public Collection<MovimentosVenda> getMovimentosVendaCollection() {
        return movimentosVendaCollection;
    }

    public void setMovimentosVendaCollection(Collection<MovimentosVenda> movimentosVendaCollection) {
        this.movimentosVendaCollection = movimentosVendaCollection;
    }

    public Collection<MovimentosCompra> getMovimentosCompraCollection() {
        return movimentosCompraCollection;
    }

    public void setMovimentosCompraCollection(Collection<MovimentosCompra> movimentosCompraCollection) {
        this.movimentosCompraCollection = movimentosCompraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Produtos[ id=" + id + " ]";
    }
    
}
