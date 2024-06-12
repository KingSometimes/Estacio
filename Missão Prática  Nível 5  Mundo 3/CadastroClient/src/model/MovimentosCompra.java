/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ramon
 */
@Entity
@Table(name = "MovimentosCompra")
@NamedQueries({
    @NamedQuery(name = "MovimentosCompra.findAll", query = "SELECT m FROM MovimentosCompra m"),
    @NamedQuery(name = "MovimentosCompra.findById", query = "SELECT m FROM MovimentosCompra m WHERE m.id = :id"),
    @NamedQuery(name = "MovimentosCompra.findByIDUsuario", query = "SELECT m FROM MovimentosCompra m WHERE m.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "MovimentosCompra.findByIDProduto", query = "SELECT m FROM MovimentosCompra m WHERE m.iDProduto = :iDProduto"),
    @NamedQuery(name = "MovimentosCompra.findByIDPessoaJuridica", query = "SELECT m FROM MovimentosCompra m WHERE m.iDPessoaJuridica = :iDPessoaJuridica"),
    @NamedQuery(name = "MovimentosCompra.findByQuantidade", query = "SELECT m FROM MovimentosCompra m WHERE m.quantidade = :quantidade"),
    @NamedQuery(name = "MovimentosCompra.findByPrecoUnitario", query = "SELECT m FROM MovimentosCompra m WHERE m.precoUnitario = :precoUnitario")})
public class MovimentosCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ID_Usuario")
    private Integer iDUsuario;
    @Column(name = "ID_Produto")
    private Integer iDProduto;
    @Column(name = "ID_PessoaJuridica")
    private Integer iDPessoaJuridica;
    @Column(name = "Quantidade")
    private Integer quantidade;
    @Column(name = "PrecoUnitario")
    private Long precoUnitario;
    @JoinColumn(name = "Produtos_ID", referencedColumnName = "ID")
    @ManyToOne
    private Produtos produtosID;
    @JoinColumn(name = "Usuarios_ID", referencedColumnName = "ID")
    @ManyToOne
    private Usuarios usuariosID;

    public MovimentosCompra() {
    }

    public MovimentosCompra(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Integer iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Integer getIDProduto() {
        return iDProduto;
    }

    public void setIDProduto(Integer iDProduto) {
        this.iDProduto = iDProduto;
    }

    public Integer getIDPessoaJuridica() {
        return iDPessoaJuridica;
    }

    public void setIDPessoaJuridica(Integer iDPessoaJuridica) {
        this.iDPessoaJuridica = iDPessoaJuridica;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Long precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Produtos getProdutosID() {
        return produtosID;
    }

    public void setProdutosID(Produtos produtosID) {
        this.produtosID = produtosID;
    }

    public Usuarios getUsuariosID() {
        return usuariosID;
    }

    public void setUsuariosID(Usuarios usuariosID) {
        this.usuariosID = usuariosID;
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
        if (!(object instanceof MovimentosCompra)) {
            return false;
        }
        MovimentosCompra other = (MovimentosCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MovimentosCompra[ id=" + id + " ]";
    }
    
}
