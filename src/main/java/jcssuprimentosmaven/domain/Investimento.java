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
import jcssuprimentosmaven.converter.EmpresaConverter;
import jcssuprimentosmaven.converter.FabricaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_investimento")
@NamedQueries({
    @NamedQuery(name = "Investimento.listar", query = "SELECT investimento FROM Investimento investimento"),
    @NamedQuery(name = "Investimento.buscarPorCodigo", query = "SELECT investimento FROM Investimento investimento WHERE investimento.id = :codigo"),
    @NamedQuery(name = "Investimento.buscarPorNome", query = "SELECT investimento FROM Investimento investimento WHERE investimento.descricao = :nome"),
    @NamedQuery(name = "Investimento.buscarPorFabrica", query = "SELECT investimento FROM Investimento investimento WHERE investimento.fabrica = :fabrica")
})
public class Investimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_investimento")
    private Long id;
    
    @NotEmpty(message = "O campo descrição é obrigatório")
    @Size(min = 1, max = 100, message = "A descrição deve ter entre 1 e 100 caracteres")
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
    
    @NotNull(message="o campo valor é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o valor")
    @Column(name = "valor", precision = 9, scale = 2, nullable = false)
    private BigDecimal valor;
    
    //@NotEmpty(message = "o campo empresa é obrigatório")
    @ElementCollection
    @Convert(converter = FabricaConverter.class, attributeName = "fk_fabrica")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabrica", referencedColumnName = "cod_fabrica", nullable = false)
    private Fabrica fabrica;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public String toString() {
        return "Investimento: " + descricao + ", valor:" + valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Investimento other = (Investimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
}