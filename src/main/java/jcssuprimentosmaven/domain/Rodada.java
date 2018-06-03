/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_rodada")
@NamedQueries({
    @NamedQuery(name = "Rodada.listar", query = "SELECT rodada FROM Rodada rodada"),
    @NamedQuery(name = "Rodada.buscarPorCodigo", query = "SELECT rodada FROM Rodada rodada WHERE rodada.id = :codigo")
})
public class Rodada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_rodada")
    private Long id;
    
    @NotNull(message = "o campo data de início é obrigatório")
    @Temporal(value = TemporalType.DATE)
    @Column(name = "dt_inicio", nullable = false)
    private Date dataInicio;
    
    @NotNull(message = "o campo data fim é obrigatório")
    @Temporal(value = TemporalType.DATE)
    @Column(name = "dt_fim", nullable = false)
    private Date dataFim;
    
    @NotEmpty(message = "O campo demanda é obrigatório")
    @Size(min = 1, max = 10, message = "O campo texto deve ter entre 1 e 10 caracteres")
    @Column(name = "demanda", length = 10)
    private String demanda;
    
    @Column(name = "carga_rodoviaria")
    private int cargaRodoviaria;
    
    @Column(name = "carga_ferroviaria")
    private int cargaFerroviaria;
    
    @NotNull(message = "O campo Juiz é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_juiz", referencedColumnName = "cod_juiz", nullable = false)
    private Juiz juiz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(String demanda) {
        this.demanda = demanda;
    }

    public int getCargaRodoviaria() {
        return cargaRodoviaria;
    }

    public void setCargaRodoviaria(int cargaRodoviaria) {
        this.cargaRodoviaria = cargaRodoviaria;
    }

    public int getCargaFerroviaria() {
        return cargaFerroviaria;
    }

    public void setCargaFerroviaria(int cargaFerroviaria) {
        this.cargaFerroviaria = cargaFerroviaria;
    }

    public Juiz getJuiz() {
        return juiz;
    }

    public void setJuiz(Juiz juiz) {
        this.juiz = juiz;
    }

    @Override
    public String toString() {
        return "Rodada{" + "id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", demanda=" + demanda + ", cargaRodoviaria=" + cargaRodoviaria + ", cargaFerroviaria=" + cargaFerroviaria + ", juiz=" + juiz + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Rodada other = (Rodada) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
