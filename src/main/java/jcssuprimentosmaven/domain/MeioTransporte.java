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
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 25/07/2018
 * @version 1.0
 * obs.: Adicionando o atributo destino
 */
@Entity
@Table(name = "tbl_meio_transporte")
@NamedQueries({
    @NamedQuery(name = "MeioTransporte.listar", query = "SELECT meioTransporte FROM MeioTransporte meioTransporte"),
    @NamedQuery(name = "MeioTransporte.buscarPorCodigo", query = "SELECT meioTransporte FROM MeioTransporte meioTransporte WHERE meioTransporte.id = :codigo"),
    @NamedQuery(name = "MeioTransporte.buscarPorNome", query = "SELECT meioTransporte FROM MeioTransporte meioTransporte WHERE meioTransporte.descricao = :nome"),
    @NamedQuery(name = "MeioTransporte.buscarPorStatus", query = "SELECT meioTransporte FROM MeioTransporte meioTransporte WHERE meioTransporte.status = :status"),
    @NamedQuery(name = "MeioTransporte.buscarPorDestino", query = "SELECT meioTransporte FROM MeioTransporte meioTransporte WHERE meioTransporte.destino =:destino")
})
public class MeioTransporte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_meiotransporte")
    private Long id;
    
    @NotEmpty(message = "O campo descrição é obrigatório")
    @Size(min = 1, max = 100, message = "O campo descrição deve ter entre 1 e 100 caracteres")
    @Column(name = "descricao", length = 100)
    private String descricao;
    
    @NotEmpty(message = "O campo destino é obrigatório")
    @Size(min = 1, max = 100, message = "O campo destino deve ter entre 1 e 100 caracteres")
    @Column(name = "destino", length = 100)
    private String destino;
    
    @NotNull(message="o campo taxa de seguro é obrigatório.")
    @DecimalMin(value="0.00", message="o campo taxa de seguro deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a taxa de seguro")
    @Column(name = "taxa_seguro", precision = 9, scale = 2, nullable = false)
    private BigDecimal taxaSeguro;
    
    @NotNull(message="o campo taxa de transporte é obrigatório.")
    @DecimalMin(value="0.00", message="o campo taxa de transporte deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a taxa de transporte")
    @Column(name = "taxa_transporte", precision = 9, scale = 2, nullable = false)
    private BigDecimal taxaTransporte;
    
    @NotEmpty(message = "O campo tempo é obrigatório")
    @Size(min = 1, max = 3, message = "O campo tempo deve ter entre 1 e 3 caracteres")
    @Column(name = "tempo", length = 3)
    private String tempo;
    
    @NotEmpty(message = "O campo status é obrigatório")
    @Size(min = 1, max = 10, message = "O campo status deve ter entre 1 e 10 caracteres")
    @Column(name = "status", length = 10)
    private String status;
    
    /*@ElementCollection
    @Convert(converter = TransportadoraConverter.class, attributeName = "fk_transportadora")
    @NotNull(message = "O campo transportadora é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_transportadora", referencedColumnName = "cod_transportadora", nullable = false)
    private Transportadora transportadora;*/

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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }  

    public BigDecimal getTaxa_seguro() {
        return taxaSeguro;
    }

    public void setTaxa_seguro(BigDecimal taxa_seguro) {
        this.taxaSeguro = taxa_seguro;
    }

    public BigDecimal getTaxa_transporte() {
        return taxaTransporte;
    }

    public void setTaxa_transporte(BigDecimal taxa_transporte) {
        this.taxaTransporte = taxa_transporte;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    /*public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }*/

    @Override
    public String toString() {
        return id + ". " + descricao + ", Entrega: " + tempo + " - Destino: " + destino ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final MeioTransporte other = (MeioTransporte) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
