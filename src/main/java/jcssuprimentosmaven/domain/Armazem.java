/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.RodadaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_armazem")
@NamedQueries({
    @NamedQuery(name = "Armazem.listar", query = "SELECT armazem FROM Armazem armazem"),
    @NamedQuery(name = "Armazem.buscarPorCodigo", query = "SELECT armazem FROM Armazem armazem WHERE armazem.id =:codigo"),
    @NamedQuery(name = "Armazem.buscarPorNome", query = "SELECT armazem FROM Armazem armazem WHERE armazem.nomeFantasia =:nome")
})
public class Armazem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_armazem")
    private Long id;
    
    @NotEmpty(message = "O campo nome fantasia é obrigatório")
    @Size(min = 1, max = 100, message = "O campo nome fantasia deve ter entre 1 e 100 caracteres")
    @Column(name = "nome_fantasia", length = 100)
    private String nomeFantasia;
    
    @NotNull(message="o campo taxa de seguro é obrigatório.")
    @DecimalMin(value="0.00", message="o campo taxa de seguro deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a taxa de seguro")
    @Column(name = "taxa_seguro", precision = 9, scale = 2, nullable = false)
    private BigDecimal taxaSeguro;
    
    @NotNull(message="o campo taxa diária é obrigatório.")
    @DecimalMin(value="0.00", message="o campo taxa diária deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a taxa diária")
    @Column(name = "taxa_diaria", precision = 9, scale = 2, nullable = false)
    private BigDecimal taxaDiaria;
    
    @ElementCollection
    @Convert(converter = RodadaConverter.class, attributeName = "fk_rodada")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_rodada", referencedColumnName = "cod_rodada", nullable = false)
    private Rodada rodada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }   

    public BigDecimal getTaxaSeguro() {
        return taxaSeguro;
    }

    public void setTaxaSeguro(BigDecimal taxaSeguro) {
        this.taxaSeguro = taxaSeguro;
    }

    public BigDecimal getTaxaDiaria() {
        return taxaDiaria;
    }

    public void setTaxaDiaria(BigDecimal taxaDiaria) {
        this.taxaDiaria = taxaDiaria;
    }

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    @Override
    public String toString() {
        return "Armazem{" + "id=" + id + ", nomeFantasia=" + nomeFantasia + ", taxaSeguro=" + taxaSeguro + ", taxaDiaria=" + taxaDiaria + ", rodada=" + rodada + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Armazem other = (Armazem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }   
}
