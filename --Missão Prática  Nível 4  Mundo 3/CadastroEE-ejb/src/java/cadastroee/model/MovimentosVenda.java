/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroee.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author ramon
 */
@Entity
@Table(name = "MovimentosVenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimentosVenda.findAll", query = "SELECT m FROM MovimentosVenda m"),
    @NamedQuery(name = "MovimentosVenda.findById", query = "SELECT m FROM MovimentosVenda m WHERE m.id = :id"),
    @NamedQuery(name = "MovimentosVenda.findByIDUsuario", query = "SELECT m FROM MovimentosVenda m WHERE m.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "MovimentosVenda.findByIDProduto", query = "SELECT m FROM MovimentosVenda m WHERE m.iDProduto = :iDProduto"),
    @NamedQuery(name = "MovimentosVenda.findByIDPessoaFisica", query = "SELECT m FROM MovimentosVenda m WHERE m.iDPessoaFisica = :iDPessoaFisica"),
    @NamedQuery(name = "MovimentosVenda.findByQuantidade", query = "SELECT m FROM MovimentosVenda m WHERE m.quantidade = :quantidade"),
    @NamedQuery(name = "MovimentosVenda.findByPrecoUnitario", query = "SELECT m FROM MovimentosVenda m WHERE m.precoUnitario = :precoUnitario")})
public class MovimentosVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ID_Usuario")
    private Integer iDUsuario;
    @Column(name = "ID_Produto")
    private Integer iDProduto;
    @Column(name = "ID_PessoaFisica")
    private Integer iDPessoaFisica;
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
    @OneToMany(mappedBy = "movimentosVendaID")
    private Collection<Pessoas> pessoasCollection;

    public MovimentosVenda() {
    }

    public MovimentosVenda(Integer id) {
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

    public Integer getIDPessoaFisica() {
        return iDPessoaFisica;
    }

    public void setIDPessoaFisica(Integer iDPessoaFisica) {
        this.iDPessoaFisica = iDPessoaFisica;
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

    @XmlTransient
    public Collection<Pessoas> getPessoasCollection() {
        return pessoasCollection;
    }

    public void setPessoasCollection(Collection<Pessoas> pessoasCollection) {
        this.pessoasCollection = pessoasCollection;
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
        if (!(object instanceof MovimentosVenda)) {
            return false;
        }
        MovimentosVenda other = (MovimentosVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroee.model.MovimentosVenda[ id=" + id + " ]";
    }
    
}
