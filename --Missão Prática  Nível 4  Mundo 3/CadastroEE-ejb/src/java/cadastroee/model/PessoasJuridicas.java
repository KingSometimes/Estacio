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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ramon
 */
@Entity
@Table(name = "PessoasJuridicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoasJuridicas.findAll", query = "SELECT p FROM PessoasJuridicas p"),
    @NamedQuery(name = "PessoasJuridicas.findById", query = "SELECT p FROM PessoasJuridicas p WHERE p.id = :id"),
    @NamedQuery(name = "PessoasJuridicas.findByCnpj", query = "SELECT p FROM PessoasJuridicas p WHERE p.cnpj = :cnpj")})
public class PessoasJuridicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "CNPJ")
    private String cnpj;
    @JoinColumn(name = "Pessoas_ID", referencedColumnName = "ID")
    @ManyToOne
    private Pessoas pessoasID;

    public PessoasJuridicas() {
    }

    public PessoasJuridicas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Pessoas getPessoasID() {
        return pessoasID;
    }

    public void setPessoasID(Pessoas pessoasID) {
        this.pessoasID = pessoasID;
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
        if (!(object instanceof PessoasJuridicas)) {
            return false;
        }
        PessoasJuridicas other = (PessoasJuridicas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroee.model.PessoasJuridicas[ id=" + id + " ]";
    }
    
}
