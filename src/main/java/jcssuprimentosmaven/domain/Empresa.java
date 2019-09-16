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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.JogadorConverter;
<<<<<<< HEAD
=======
import jcssuprimentosmaven.converter.RodadaConverter;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.listar", query = "SELECT empresa FROM Empresa empresa"),
    @NamedQuery(name = "Empresa.buscarPorCodigo", query = "SELECT empresa FROM Empresa empresa WHERE empresa.id = :codigo"),
    @NamedQuery(name = "Empresa.buscarPorNome", query = "SELECT empresa FROM Empresa empresa WHERE empresa.nomeFantasia = :nome"),
    @NamedQuery(name = "Empresa.buscarPorJogador", query = "SELECT empresa FROM Empresa empresa WHERE empresa.jogador = :jogador")
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
    
    @NotNull(message="o campo capital inicial é obrigatório.")
    @DecimalMin(value="0.00", message="o campo capital inicial deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o capital inicial")
    @Column(name = "capital_inicial", precision = 9, scale = 2, nullable = false)
    private BigDecimal capitalInicial;
    
    @NotNull(message="o campo capital atual é obrigatório.")
    @DecimalMin(value="0.00", message="o campo capital atual deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o capital atual")
    @Column(name = "capital_atual", precision = 9, scale = 2, nullable = false)
    private BigDecimal capitalAtual;
    
    //@NotEmpty(message = "O campo logomarca é obrigatório")
    @Size(min = 1, max = 100, message = "O campo logomarca deve ter entre 1 e 100 caracteres")
    @Column(name = "logomarca", length = 100)
    private String logomarca;
    
    @NotNull(message="o campo margem de lucro é obrigatório.")
    @DecimalMin(value="0.00", message="o campo margem de lucro deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a margem de lucro")
    @Column(name = "margem_lucro", precision = 9, scale = 2, nullable = false)
    private BigDecimal margeLucro;
    
   /* @ElementCollection
    @Convert(converter = TransportadoraConverter.class, attributeName = "fk_transportadora")
    //@NotEmpty(message = "O campo Transportadora é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_transportadora", referencedColumnName = "cod_transportadora", nullable = false)
    private Transportadora transportadora;
    
    @ElementCollection
    @Convert(converter = FornecedorConverter.class, attributeName = "fk_fornecedor")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fornecedor", referencedColumnName = "cod_fornecedor", nullable = false)
    private Fornecedor fornecedor;*/
    
    @ElementCollection
    @Convert(converter = JogadorConverter.class, attributeName = "fk_jogador")
    //@NotEmpty(message = "O campo jogador é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_jogador", referencedColumnName = "cod_jogador", nullable = false)
    private Jogador jogador;
    
    /*@ElementCollection
    @Convert(converter = RodadaConverter.class, attributeName = "fk_rodada")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_rodada", referencedColumnName = "cod_rodada", nullable = false)
    private Rodada rodada;*/
    
    /*@ElementCollection
    @Convert(converter = FabricaConverter.class, attributeName = "fk_fabrica")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabrica", referencedColumnName = "cod_fabrica", nullable = false)
    private Fabrica fabrica;
    
    @ElementCollection
    @Convert(converter = DistribuidorConverter.class, attributeName = "fk_distribuidor")
    //@NotEmpty(message = "O campo rotada é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_distribuidor", referencedColumnName = "cod_distribuidor", nullable = false)
    private Distribuidor distribuidor;*/

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
    
   /* public Transportadora getTransportadora() {
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

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }*/
    
    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /*public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }  */

    public BigDecimal getCapitalInicial() {
        return capitalInicial;
    }

    public void setCapitalInicial(BigDecimal capitalInicial) {
        this.capitalInicial = capitalInicial;
    }

    public BigDecimal getCapitalAtual() {
        return capitalAtual;
    }

    public void setCapitalAtual(BigDecimal capitalAtual) {
        this.capitalAtual = capitalAtual;
    } 

    public BigDecimal getMargeLucro() {
        return margeLucro;
    }

    public void setMargeLucro(BigDecimal margeLucro) {
        this.margeLucro = margeLucro;
    }
    

    @Override
    public String toString() {
        return id + "." + nomeFantasia;
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
