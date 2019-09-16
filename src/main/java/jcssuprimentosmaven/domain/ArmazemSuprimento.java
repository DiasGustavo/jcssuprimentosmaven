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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.FabricaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Gustavo
 * Criação: 24/06/2018 
 * Última Alteração 25/07/2018
 * @version 1.0
 * obs.: alteração da forma de exibição do armazém de suprimento
 */
@Entity
@Table(name = "tbl_armazem_suprimento")
@NamedQueries({
    @NamedQuery(name = "ArmazemSuprimento.listar", query = "SELECT armazem FROM ArmazemSuprimento armazem"),
    @NamedQuery(name = "ArmazemSuprimento.buscarPorCodigo", query = "SELECT armazem FROM ArmazemSuprimento armazem WHERE armazem.id = :codigo"),
    @NamedQuery(name = "ArmazemSuprimento.buscarPorNome", query = "SELECT armazem FROM ArmazemSuprimento armazem WHERE armazem.nomeFantasia = :nome"),
    @NamedQuery(name = "ArmazemSuprimento.buscarPorFabrica", query = "SELECT armazem FROM ArmazemSuprimento armazem WHERE armazem.fabrica = :fabrica")
})
public class ArmazemSuprimento implements Serializable {
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
    @Convert(converter = FabricaConverter.class, attributeName = "fk_fabrica")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabrica", referencedColumnName = "cod_fabrica", nullable = false)
    private Fabrica fabrica;
    
    /*@ElementCollection
    @Convert(converter = MateriaPrimaConverter.class, attributeName = "fk_materia_prima")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_materia_prima", referencedColumnName = "cod_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;*/
    
   
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

    /*public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }*/

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }
    
    
    @Override
    public String toString() {
        return id + ". " + nomeFantasia ;
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
        final ArmazemSuprimento other = (ArmazemSuprimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }   
}
