/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.listar", query = "SELECT empresa FROM Empresa empresa"),
    @NamedQuery(name = "Empresa.buscarPorCodigo", query = "SELECT empresa FROM Empresa empresa WHERE empresa.id = :codigo")
})
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_empresa")
    private Long id;
    
    @NotEmpty(message = "O campo nome fantasia é obrigatório")
    @Size(min = 1, max = 50, message = "O campo nome fantasia deve ter entre 1 e 50 caracteres")
    @Column(name = "nome_fantasia", length = 50, nullable = false)
    private String nomeFantasia;
    
    @NotEmpty(message = "O campo logomarca é obrigatório")
    @Size(min = 1, max = 100, message = "O campo logomarca deve ter entre 1 e 100 caracteres")
    @Column(name = "logomarca", length = 100, nullable = false)
    private String logomarca;
    
    @NotEmpty(message = "O campo Transportadora é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_transportadora", referencedColumnName = "cod_transportadora", nullable = false)
    private Transportadora transportadora;
    
    @NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fornecedor", referencedColumnName = "cod_fornecedor", nullable = false)
    private Fornecedor fornecedor;
    
    @NotEmpty(message = "O campo jogador é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_jogador", referencedColumnName = "cod_jogador", nullable = false)
    private Jogador jogador;
    
    @NotEmpty(message = "O campo rotada é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_rodada", referencedColumnName = "cod_rodada", nullable = false)
    private Rodada rodada;

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

    public String getLogomarca() {
        return logomarca;
    }

    public void setLogomarca(String logomarca) {
        this.logomarca = logomarca;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nomeFantasia=" + nomeFantasia + ", logomarca=" + logomarca + ", transportadora=" + transportadora + ", fornecedor=" + fornecedor + ", jogador=" + jogador + ", rodada=" + rodada + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
