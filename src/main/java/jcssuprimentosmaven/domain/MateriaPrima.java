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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_materia_prima")
@NamedQueries({
    @NamedQuery(name = "MateriaPrima.listar", query = "SELECT materiaPrima FROM MateriaPrima materiaPrima"),
    @NamedQuery(name = "MateriaPrima.buscarPorCodigo", query = "SELECT materiaPrima FROM MateriaPrima materiaPrima WHERE materiaPrima.id = :codigo"),
    @NamedQuery(name = "MateriaPrima.buscarPorNome", query = "SELECT materiaPrima FROM MateriaPrima materiaPrima WHERE materiaPrima.descricao = :descricao"),
    @NamedQuery(name = "MateriaPrima.buscarPorStatus", query = "SELECT materiaPrima FROM MateriaPrima materiaPrima WHERE materiaPrima.status = :status")
   // @NamedQuery(name = "MateriaPrima.buscarPorFornecedor", query = "SELECT materiaPrima FROM MateriaPrima materiaPrima WHERE materiaPrima.fornecedor = :fornecedor")
})
public class MateriaPrima implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_materia_prima")
    private Long id;
    
    @NotEmpty(message = "O campo descrição é obrigatório")
    @Size(min = 1, max = 100, message = "O campo descrição deve ter entre 1 e 100 caracteres")
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @NotNull(message="o campo preço é obrigatório.")
    @DecimalMin(value="0.00", message="o campo preço deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o preço")
    @Column(name = "preco", precision = 9, scale = 2, nullable = false)
    private BigDecimal preco;
    
    @NotEmpty(message = "O campo tempo de entrega é obrigatório")
    @Size(min = 1, max = 3, message = "O campo tempo deve ter entre 1 e 3 caracteres")
    @Column(name = "tempo_entrega", length = 3)
    private String tempoEntrega;
    
    @NotEmpty(message = "O campo Quantidade é obrigatório")
    @Size(min = 1, max = 3, message = "O campo Quantidade deve ter entre 1 e 3 caracteres")
    @Column(name = "quantidade", length = 3)
    private String quantidade;
    
    @NotEmpty(message = "O campo Status é obrigatório")
    @Size(min = 1, max = 10, message = "O campo status deve ter entre 1 e 10 caracteres")
    @Column(name = "status", length = 10)
    private String status;
   
    /*@ElementCollection
    @Convert(converter = FornecedorConverter.class, attributeName = "fk_fornecedor")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fornecedor", referencedColumnName = "cod_fornecedor", nullable = false)
    private Fornecedor fornecedor;*/

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(String tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    /*public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }*/

    @Override
    public String toString() {
        return id + ". " + descricao + "- preco: " + preco + "- Qtd: " + quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final MateriaPrima other = (MateriaPrima) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
