/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.math.BigDecimal;
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
import jcssuprimentosmaven.converter.ArmazemFabricaConverter;


/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_producao")
@NamedQueries({
    @NamedQuery(name = "Producao.listar", query = "SELECT producao FROM Producao producao"),
    @NamedQuery(name = "Producao.buscarPorCodigo", query = "SELECT producao FROM Producao producao WHERE producao.id = :codigo"),
<<<<<<< HEAD
    @NamedQuery(name = "Producao.buscarPorArmazemFabrica", query = "SELECT producao FROM Producao producao WHERE producao.armazemFabrica = :fabrica")
=======
    @NamedQuery(name = "Producao.buscarPorFabrica", query = "SELECT producao FROM Producao producao WHERE producao.armazemFabrica = :fabrica")
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    
})
public class Producao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_producao")
    private Long id;
    
    @NotNull(message="o campo quantidade da materia prima é obrigatório.")
    @DecimalMin(value="0.00", message="o campo quantidade da materia prima deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade da materia prima")
    @Column(name = "quantidadeA", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidadeMateriaA;  
    
    @NotNull(message="o campo quantidade da materia prima é obrigatório.")
    @DecimalMin(value="0.00", message="o campo quantidade da materia prima deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade da materia prima")
    @Column(name = "quantidadeB", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidadeMateriaB; 
    
<<<<<<< HEAD
    @DecimalMin(value="0.00", message="o campo produto deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade de produtos")
    @Column(name = "produtos", precision = 9, scale = 2, nullable = false)
    private BigDecimal produtos; 
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
       
    @ElementCollection
    @Convert(converter = ArmazemFabricaConverter.class, attributeName = "fk_armazem_fabrica")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_armazem_fabrica", referencedColumnName = "cod_armazem", nullable = false)
    private ArmazemFabrica armazemFabrica;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantidadeMateriaA() {
        return quantidadeMateriaA;
    }

    public void setQuantidadeMateriaA(BigDecimal quantidadeMateriaA) {
        this.quantidadeMateriaA = quantidadeMateriaA;
    }

    public BigDecimal getQuantidadeMateriaB() {
        return quantidadeMateriaB;
    }

    public void setQuantidadeMateriaB(BigDecimal quantidadeMateriaB) {
        this.quantidadeMateriaB = quantidadeMateriaB;
    }
    
    public ArmazemFabrica getArmazemFabrica() {
        return armazemFabrica;
    }

    public void setArmazemFabrica(ArmazemFabrica armazemFabrica) {
        this.armazemFabrica = armazemFabrica;
    }

<<<<<<< HEAD
    public BigDecimal getProdutos() {
        return produtos;
    }

    public void setProdutos(BigDecimal produtos) {
        this.produtos = produtos;
    }

=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    @Override
    public String toString() {
        return "Producao{" + "id=" + id + ", quantidadeMateriaA=" + quantidadeMateriaA + ", quantidadeMateriaB=" + quantidadeMateriaB + ", armazemFabrica=" + armazemFabrica + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Producao other = (Producao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
