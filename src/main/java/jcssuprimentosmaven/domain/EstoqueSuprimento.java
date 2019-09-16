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
import jcssuprimentosmaven.converter.ArmazemSuprimentoConverter;
import jcssuprimentosmaven.converter.EmpresaConverter;
import jcssuprimentosmaven.converter.MateriaPrimaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_estoque_suprimento")
@NamedQueries({
    @NamedQuery(name = "EstoqueSuprimento.listar", query = "SELECT estoqueSuprimento FROM EstoqueSuprimento estoqueSuprimento"),
    @NamedQuery(name = "EstoqueSuprimento.buscarPorCodigo", query = "SELECT estoqueSuprimento FROM EstoqueSuprimento estoqueSuprimento WHERE estoqueSuprimento.id =:codigo"),
    @NamedQuery(name = "EstoqueSuprimento.buscarPorArmazem",query = "SELECT estoqueSuprimento FROM EstoqueSuprimento estoqueSuprimento WHERE estoqueSuprimento.armazemSuprimento = :armazem"),
    @NamedQuery(name = "EstoqueSuprimento.buscarPorMateriaArmazem", query = "SELECT estoqueSuprimento FROM EstoqueSuprimento estoqueSuprimento WHERE estoqueSuprimento.materiaPrima = :materia AND estoqueSuprimento.armazemSuprimento = :armazem")
    //@NamedQuery(name = "EstoqueSuprimento.buscarPorNomeEmpresa", query = "SELECT estoqueSuprimento FROM EstoqueSuprimento estoqueSuprimento WHERE estoqueSuprimento.empresa.nomeFantasia = :nome"),
    //@NamedQuery(name = "EstoqueSuprimento.buscarPorEmpresaEstoque",query = "SELECT estoqueSuprimento FROM EstoqueSuprimento estoqueSuprimento WHERE estoqueSuprimento.empresa = :empresa AND estoqueSuprimento.estoque = :estoque"),
})
public class EstoqueSuprimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_estoque_suprimento")
    private Long id;
    //cadastrar local
    //@NotEmpty(message = "O campo Local é obrigatório")
    @Size(min = 1, max = 100)
    @Column(name = "descricao", length = 20)
    private String descricao;
    
    @NotNull(message="o campo quantidade é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade")
    @Column(name = "quantidade", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidade;
    
    @ElementCollection
    @Convert(converter = MateriaPrimaConverter.class, attributeName = "fk_materia_prima")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_materia_prima", referencedColumnName = "cod_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;
    
    @ElementCollection
    @Convert(converter = ArmazemSuprimentoConverter.class, attributeName = "fk_armazem_suprimento")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_armazem_suprimento", referencedColumnName = "cod_armazem", nullable = false)
    private ArmazemSuprimento armazemSuprimento;

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

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public ArmazemSuprimento getArmazemSuprimento() {
        return armazemSuprimento;
    }

    public void setArmazemSuprimento(ArmazemSuprimento armazemSuprimento) {
        this.armazemSuprimento = armazemSuprimento;
    }

    @Override
    public String toString() {
        return   id + "-" + armazemSuprimento.getNomeFantasia() + "- materiaPrima: " + materiaPrima.getDescricao() + "Qtd: " + quantidade ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final EstoqueSuprimento other = (EstoqueSuprimento) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }    
}
