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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.ArmazemDistribuicaoConverter;
import jcssuprimentosmaven.converter.ProdutoConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_estoque_distribuicao")
@NamedQueries({
    @NamedQuery(name = "EstoqueDistribuicao.listar", query = "SELECT estoqueDistribuicao FROM EstoqueDistribuicao estoqueDistribuicao"),
    @NamedQuery(name = "EstoqueDistribuicao.buscarPorCodigo", query = "SELECT estoqueDistribuicao FROM EstoqueDistribuicao estoqueDistribuicao WHERE estoqueDistribuicao.id =:codigo"),
    @NamedQuery(name = "EstoqueDistribuicao.buscarPorProduto", query = "SELECT estoqueDistribuicao FROM EstoqueDistribuicao estoqueDistribuicao WHERE estoqueDistribuicao.produto = :produto"),
    @NamedQuery(name = "EstoqueDistribuicao.buscarPorArmazem", query = "SELECT estoqueDistribuicao FROM EstoqueDistribuicao estoqueDistribuicao WHERE estoqueDistribuicao.armazemDistribuicao = :armazem")
})
public class EstoqueDistribuicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_estoque_distribuicao")
    private Long id;
            
    @Size(min = 1, max = 100)
    @Column(name = "descricao", length = 20)
    private String descricao;
    
    @NotNull(message="o campo quantidade de produto é obrigatório.")
    @DecimalMin(value="0.00", message="o campo quantidade de produto deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade do produto")
    @Column(name = "quantidade", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidade;   
    
    @ElementCollection
    @Convert(converter = ArmazemDistribuicaoConverter.class, attributeName = "fk_armazem_distribuicao")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_armazem_distribuicao", referencedColumnName = "cod_armazem", nullable = false)
    private ArmazemDistribuicao armazemDistribuicao;
    
    @ElementCollection
    @Convert(converter = ProdutoConverter.class, attributeName = "fk_produto")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_produto", referencedColumnName = "cod_produto", nullable = false)
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public ArmazemDistribuicao getArmazemDistribuicao() {
        return armazemDistribuicao;
    }

    public void setArmazemDistribuicao(ArmazemDistribuicao armazemDistribuicao) {
        this.armazemDistribuicao = armazemDistribuicao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    

    @Override
    public String toString() {
        return id + ". " + descricao + "- produto: " + produto.getDescricao() + ", Qtd: " + quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final EstoqueDistribuicao other = (EstoqueDistribuicao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
