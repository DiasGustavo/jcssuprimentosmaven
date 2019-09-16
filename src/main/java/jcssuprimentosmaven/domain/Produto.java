/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.FabricaConverter;

/**
<<<<<<< HEAD
 * @author Gustavo
 * Criação: 26/07/2018 
 * Última Alteração 26/07/2018
 * @version 1.0
 * obs.: crição do produtos
=======
 *
 * @author Gustavo
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
 */
@Entity
@Table(name = "tbl_produto")
@NamedQueries({
    @NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
    @NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.id = :codigo"),
    @NamedQuery(name = "Produto.buscarPorFabrica", query = "SELECT produto FROM Produto produto WHERE produto.fabrica = :fabrica")
})
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_produto")
    private Long id;
    
    @Size(min = 1, max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;
    
<<<<<<< HEAD
    @NotNull(message="o campo valor é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o campo valor")
    @Column(name = "valor", precision = 9, scale = 2, nullable = false)
    private BigDecimal valor;
    
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    @ElementCollection
    @Convert(converter = FabricaConverter.class, attributeName = "fk_fabrica")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabrica", referencedColumnName = "cod_fabrica", nullable = false)
    private Fabrica fabrica;   
    
    @NotNull(message="o campo quantidade de produto é obrigatório.")
    @DecimalMin(value="0.00", message="o campo quantidade de produto deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade do produto")
    @Column(name = "quantidade", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidade; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

<<<<<<< HEAD
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    @Override
    public String toString() {
        return id + ": " +descricao + " - " + fabrica.getNomeFantasia() + " - qtd: " + quantidade ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
