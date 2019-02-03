/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.EmpresaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_fornecedor")
@NamedQueries({
    @NamedQuery(name = "Fornecedor.listar", query = "SELECT fornecedor FROM Fornecedor fornecedor"),
    @NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.id = :codigo"),
    @NamedQuery(name = "Fornecedor.buscarPorNome", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.fantasia = :nome"),
    @NamedQuery(name = "Fornecedor.buscarPorEmpresa", query = "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.empresa = :empresa")
})
public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//trocado de auto para identity para ser autoincremento
    @Column(name = "cod_fornecedor")
    private Long id;
    
    @NotEmpty(message = "O nome fantasia é obrigatório")
    @Size(min = 1, max = 50, message = "O campo fantasia tem que ter entre 1 e 50 caracteres" )
    @Column(name = "fantasia", length = 50)
    private String fantasia;
    
    @ElementCollection
    @Convert(converter = EmpresaConverter.class, attributeName = "fk_empresa")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empresa", referencedColumnName = "cod_empresa", nullable = false)
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }   

    @Override
    public String toString() {
        return id + ". " + fantasia + " - "+ empresa.getNomeFantasia();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
