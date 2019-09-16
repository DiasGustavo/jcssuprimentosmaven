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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.ArmazemDistribuicaoConverter;
<<<<<<< HEAD
import jcssuprimentosmaven.converter.MeioTransporteConverter;
import jcssuprimentosmaven.converter.ProdutoConverter;
import org.hibernate.validator.constraints.NotEmpty;
=======
import jcssuprimentosmaven.converter.ArmazemSuprimentoConverter;
import jcssuprimentosmaven.converter.MateriaPrimaConverter;
import jcssuprimentosmaven.converter.MeioTransporteConverter;
import jcssuprimentosmaven.converter.ProdutoConverter;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_solicitacao")
@NamedQueries({
    @NamedQuery(name = "Solicitacao.listar", query = "SELECT solicitacao FROM Solicitacao solicitacao"),
<<<<<<< HEAD
    @NamedQuery(name = "Solicitacao.buscarPorCodigo", query = "SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.id = :codigo"),
    @NamedQuery(name = "Solicitacao.buscarPorArmazemStatus", query = "SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.armazemDistribuicao = :armazem AND solicitacao.status = :status")
=======
    @NamedQuery(name = "Solicitacao.buscarPorCodigo", query = "SELECT solicitacao FROM Solicitacao solicitacao WHERE solicitacao.id = :codigo")
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
})
public class Solicitacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_solicitacao")
    private Long id;
    
<<<<<<< HEAD
=======
    @Size(min = 1, max = 100)
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    @Column(name = "descricao", length = 100)
    private String descricao;
    
    @NotNull(message="o campo quantidade é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade")
    @Column(name = "quantidade", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidade;
    
    @ElementCollection
    @Convert(converter = ProdutoConverter.class, attributeName = "fk_produto")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_produto", referencedColumnName = "cod_produto", nullable = false)
    private Produto produto;
    
    @ElementCollection
    @Convert(converter = ArmazemDistribuicaoConverter.class, attributeName = "fk_armazem_distribuicao")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_armazem_distribuicao", referencedColumnName = "cod_armazem", nullable = false)
    private ArmazemDistribuicao armazemDistribuicao;    
    
    @ElementCollection
    @Convert(converter = MeioTransporteConverter.class, attributeName = "fk_meio_transporte")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_meio_transporte", referencedColumnName = "cod_meiotransporte", nullable = false)
    private MeioTransporte meioTransporte;
    
    @NotNull(message="o campo valor é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o campo valor")
    @Column(name = "valor", precision = 9, scale = 2, nullable = false)
    private BigDecimal valor;
<<<<<<< HEAD
    
    @NotEmpty(message = "O campo Status é obrigatório")
    @Size(min = 1, max = 20, message = "O campo Status deve ter entre 1 e 20 caracteres")
    @Column(name = "status", length = 20)
    private String status;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ArmazemDistribuicao getArmazemDistribuicao() {
        return armazemDistribuicao;
    }

    public void setArmazemDistribuicao(ArmazemDistribuicao armazemDistribuicao) {
        this.armazemDistribuicao = armazemDistribuicao;
    }

    public MeioTransporte getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(MeioTransporte meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
<<<<<<< HEAD

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    

    @Override
    public String toString() {
        return "Solicitacao{" + "id=" + id + ", descricao=" + descricao + ", produto=" + produto + ", armazemDistribuicao=" + armazemDistribuicao + ", meioTransporte=" + meioTransporte + ", valor=" + valor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Solicitacao other = (Solicitacao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
