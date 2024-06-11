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
@Table(name = "PessoasFisicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoasFisicas.findAll", query = "SELECT p FROM PessoasFisicas p"),
    @NamedQuery(name = "PessoasFisicas.findById", query = "SELECT p FROM PessoasFisicas p WHERE p.id = :id"),
    @NamedQuery(name = "PessoasFisicas.findByCpf", query = "SELECT p FROM PessoasFisicas p WHERE p.cpf = :cpf")})
public class PessoasFisicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "CPF")
    private String cpf;
    @JoinColumn(name = "Pessoas_ID", referencedColumnName = "ID")
    @ManyToOne
    private Pessoas pessoasID;

    public PessoasFisicas() {
    }

    public PessoasFisicas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        if (!(object instanceof PessoasFisicas)) {
            return false;
        }
        PessoasFisicas other = (PessoasFisicas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cadastroee.model.PessoasFisicas[ id=" + id + " ]";
    }
    
}
