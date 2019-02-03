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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.EstoqueDistribuicaoConverter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_distribuidor")
@NamedQueries({
    @NamedQuery(name = "Distribuidor.listar", query = "SELECT distribuidor FROM Distribuidor distribuidor"),
    @NamedQuery(name = "Distribuidor.buscarPorCodigo", query = "SELECT distribuidor FROM Distribuidor distribuidor WHERE distribuidor.id = :codigo"),
    @NamedQuery(name = "Distribuidor.buscarPorNome", query = "SELECT distribuidor FROM Distribuidor distribuidor WHERE distribuidor.nomeFantasia = :nome"),
    @NamedQuery(name = "Distribuidor.buscarPorEstoque", query = "SELECT distribuidor FROM Distribuidor distribuidor WHERE distribuidor.estoqueDistribuicao = :estoque")
})
public class Distribuidor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_distribuidor")
    private Long id;
    
    @NotEmpty(message = "O campo nome fantasia é obrigatório")
    @Size(min = 1, max = 50, message = "O campo nome fantasia deve ter entre 1 e 50 caracteres")
    @Column(name = "nome_fantasia", length = 50, nullable = false)
    private String nomeFantasia;
    
    @NotNull(message="o campo preço de custo é obrigatório.")
    @DecimalMin(value="0.00", message="o campo preço de custo deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o preço de custo")
    @Column(name = "preco_custo", precision = 9, scale = 2, nullable = false)
    private BigDecimal precoCusto;
    
    @NotNull(message="o campo preço de venda é obrigatório.")
    @DecimalMin(value="0.00", message="o campo preço de venda deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o preço de venda")
    @Column(name = "preco_venda", precision = 9, scale = 2, nullable = false)
    private BigDecimal precoVenda;
    
    /*@ElementCollection
    @Convert(converter = EmpresaConverter.class, attributeName = "fk_empresa")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empresa", referencedColumnName = "cod_empresa", nullable = false)
    private Empresa empresa;*/
    
    @ElementCollection
    @Convert(converter = EstoqueDistribuicaoConverter.class, attributeName = "fk_estoque_distribuicao")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estoque_distribuicao", referencedColumnName = "cod_estoque_distribuicao", nullable = false)
    private EstoqueDistribuicao estoqueDistribuicao;

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
    
    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public EstoqueDistribuicao getEstoqueDistribuicao() {
        return estoqueDistribuicao;
    }

    public void setEstoqueDistribuicao(EstoqueDistribuicao estoqueDistribuicao) {
        this.estoqueDistribuicao = estoqueDistribuicao;
    }
    
    /*public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }*/
    

    @Override
    public String toString() {
        return  id + ". "+nomeFantasia +" , precoCusto=" + precoCusto + ", precoVenda=" + precoVenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Distribuidor other = (Distribuidor) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    } 
    
}
