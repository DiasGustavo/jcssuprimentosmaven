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
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.ArmazemFabricaConverter;
import jcssuprimentosmaven.converter.MateriaPrimaConverter;
import jcssuprimentosmaven.converter.MeioTransporteConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_transporte")
@NamedQueries({
    @NamedQuery(name = "Transporte.listar", query = "SELECT transporte FROM Transporte transporte"),
    @NamedQuery(name = "Transporte.buscarPorCodigo", query = "SELECT transporte FROM Transporte transporte WHERE transporte.id =:codigo"),
<<<<<<< HEAD
    @NamedQuery(name = "Transporte.buscarPorArmazemFabrica", query = "SELECT transporte FROM Transporte transporte WHERE transporte.armazemFabrica =:armazemFabrica"),
    @NamedQuery(name = "Transporte.buscarPorArmazemFabricaStatus", query = "SELECT transporte FROM Transporte transporte WHERE transporte.armazemFabrica = :armazemFabrica AND transporte.status = :status")
=======
    @NamedQuery(name = "Transporte.buscarPorArmazemFabrica", query = "SELECT transporte FROM Transporte transporte WHERE transporte.armazemFabrica =:armazemFabrica")
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    
})
public class Transporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_transporte")
    private Long id;
            
    @NotNull(message="o campo quantidade é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade")
    @Column(name = "quantidade", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidade;
    
    @NotEmpty(message = "O campo tempo é obrigatório")
    @Size(min = 1, max = 3, message = "O campo tempo deve ter entre 1 e 3 caracteres")
    @Column(name = "tempo", length = 3)
    private String tempo;
        
    
    @NotNull(message="o campo valor é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o valor")
    @Column(name = "valor", precision = 9, scale = 2, nullable = false)
    private BigDecimal valor;
    
    @ElementCollection
    @Convert(converter = MateriaPrimaConverter.class, attributeName = "fk_materia_prima")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_materia_prima", referencedColumnName = "cod_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;
    
    @ElementCollection
    @Convert(converter = MeioTransporteConverter.class, attributeName = "fk_meio_transporte")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_meio_transporte", referencedColumnName = "cod_meiotransporte", nullable = false)
    private MeioTransporte meioTransporte;
    
    @ElementCollection
    @Convert(converter = ArmazemFabricaConverter.class, attributeName = "fk_armazem_fabrica")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_armazem_fabrica", referencedColumnName = "cod_armazem", nullable = false)
    private ArmazemFabrica armazemFabrica;
    
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

    public ArmazemFabrica getArmazemFabrica() {
        return armazemFabrica;
    }

    public void setArmazemFabrica(ArmazemFabrica armazemFabrica) {
        this.armazemFabrica = armazemFabrica;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
   
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /*public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }*/

    public MeioTransporte getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(MeioTransporte meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    /*public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ". qtd: " + quantidade + ", tempo: " + tempo + ", v.:" + valor + ", materiaPrima: " + materiaPrima + ", meioTransporte: " + meioTransporte + ", Dst: " + armazemFabrica + ", status: " + status + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Transporte other = (Transporte) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
