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
import jcssuprimentosmaven.converter.ArmazemSuprimentoConverter;
import jcssuprimentosmaven.converter.MateriaPrimaConverter;
import jcssuprimentosmaven.converter.MeioTransporteConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.listar", query = "SELECT pedido FROM Pedido pedido"),
    @NamedQuery(name = "Pedido.buscarPorCodigo", query = "SELECT pedido FROM Pedido pedido WHERE pedido.id = :codigo"),
    @NamedQuery(name = "Pedido.buscarPorArmazemSuprimento", query = "SELECT pedido FROM Pedido pedido WHERE pedido.armazemSuprimento = :armazemSuprimento"),
    @NamedQuery(name = "Pedido.buscarPorArmazemSuprimentoStatus", query = "SELECT pedido FROM Pedido pedido WHERE pedido.armazemSuprimento = :armazemSuprimento AND pedido.status = :status")
})
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pedido")
    private Long id;
    
    @Size(min = 1, max = 100)
    @Column(name = "descricao", length = 100)
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
    private MateriaPrima materiPrima;
    
    @ElementCollection
    @Convert(converter = ArmazemSuprimentoConverter.class, attributeName = "fk_armazem_suprimento")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_armazem_suprimento", referencedColumnName = "cod_armazem", nullable = false)
    private ArmazemSuprimento armazemSuprimento;    
    
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
    
    @NotEmpty(message = "O campo Status é obrigatório")
    @Size(min = 1, max = 20, message = "O campo Status deve ter entre 1 e 20 caracteres")
    @Column(name = "status", length = 20)
    private String status;

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

    public MateriaPrima getMateriPrima() {
        return materiPrima;
    }

    public void setMateriPrima(MateriaPrima materiPrima) {
        this.materiPrima = materiPrima;
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

    public ArmazemSuprimento getArmazemSuprimento() {
        return armazemSuprimento;
    }

    public void setArmazemSuprimento(ArmazemSuprimento armazemSuprimento) {
        this.armazemSuprimento = armazemSuprimento;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", descricao=" + descricao + ", materiPrima=" + materiPrima + ", meioTransporte=" + meioTransporte + ", valor=" + valor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Pedido other = (Pedido) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}
