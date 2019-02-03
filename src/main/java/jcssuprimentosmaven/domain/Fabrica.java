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
import jcssuprimentosmaven.converter.EmpresaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_fabrica")
@NamedQueries({
    @NamedQuery(name = "Fabrica.listar", query = "SELECT fabrica FROM Fabrica fabrica"),
    @NamedQuery(name = "Fabrica.buscarPorNome", query = "SELECT fabrica FROM Fabrica fabrica WHERE fabrica.nomeFantasia = :nome"),
    @NamedQuery(name = "Fabrica.buscarPorCodigo", query = "SELECT fabrica FROM Fabrica fabrica WHERE fabrica.id = :codigo"),
    @NamedQuery(name = "Fabrica.buscarPorEmpresa", query = "SELECT fabrica FROM Fabrica fabrica WHERE fabrica.empresa = :empresa")
})
public class Fabrica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_fabrica")
    private Long id;
    
    @NotEmpty(message = "O campo nome fantasia é obrigatório")
    @Size(min = 1, max = 50, message = "O campo nome fantasia deve ter entre 1 e 50 caracteres")
    @Column(name = "nome_fantasia", length = 50, nullable = false)
    private String nomeFantasia;
    
    @Column(name = "tempo_transformacao")
    private int tempoTransformacao;
    
    @NotNull(message="o campo custo de transformação é obrigatório.")
    @DecimalMin(value="0.00", message="o campo custo de transformação deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o custo de transformação")
    @Column(name = "custo_transformacao", precision = 9, scale = 2, nullable = false)
    private BigDecimal custoTransformacao;
    
    @NotNull(message="o campo fator de transformação é obrigatório.")
    @DecimalMin(value="0.00", message="o campo fator de transformação deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o fator de transformação")
    @Column(name = "fator_transformacao", precision = 9, scale = 2, nullable = false)
    private BigDecimal fatorTransformacao;
    
    @NotNull(message="o campo taxa de serviço cliente é obrigatório.")
    @DecimalMin(value="0.00", message="o campo taxa de serviço cliente deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a taxa de serviço cliente")
    @Column(name = "taxa_cliente", precision = 9, scale = 2, nullable = false)
    private BigDecimal taxaServicoCliente;
    
    @NotNull(message="o campo valor pesquisa cliente é obrigatório.")
    @DecimalMin(value="0.00", message="o campo valor pesquisa cliente deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para o valor de pesquisa")
    @Column(name = "pesquisa", precision = 9, scale = 2, nullable = false)
    private BigDecimal pesquisa;
    
    @NotEmpty(message = "O campo demanda é obrigatório")
    @Size(min = 1, max = 10, message = "O campo texto deve ter entre 1 e 10 caracteres")
    @Column(name = "demanda", length = 10)
    private String demanda;
    
   /* @ElementCollection
    @Convert(converter = FornecedorConverter.class, attributeName = "fk_investimento")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_investimento", referencedColumnName = "cod_investimento", nullable = false)
    private Investimento investimento;*/
    
    @ElementCollection
    @Convert(converter = EmpresaConverter.class, attributeName = "fk_empresa")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empresa", referencedColumnName = "cod_empresa", nullable = false)
    private Empresa empresa;
    
    /*@ElementCollection
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_jogador", referencedColumnName = "cod_jogador", nullable = false)
    private Jogador jogador;*/
       

    public Long getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public int getTempoTransformacao() {
        return tempoTransformacao;
    }

    public void setTempoTransformacao(int tempoTransformacao) {
        this.tempoTransformacao = tempoTransformacao;
    }

    public BigDecimal getCustoTransformacao() {
        return custoTransformacao;
    }

    public void setCustoTransformacao(BigDecimal custoTransformacao) {
        this.custoTransformacao = custoTransformacao;
    }

    public BigDecimal getFatorTransformacao() {
        return fatorTransformacao;
    }

    public void setFatorTransformacao(BigDecimal fatorTransformacao) {
        this.fatorTransformacao = fatorTransformacao;
    }

    public BigDecimal getTaxaServicoCliente() {
        return taxaServicoCliente;
    }

    public void setTaxaServicoCliente(BigDecimal taxaServicoCliente) {
        this.taxaServicoCliente = taxaServicoCliente;
    }

    public BigDecimal getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(BigDecimal pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(String demanda) {
        this.demanda = demanda;
    }

    /*public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }*/
  
    /*public Investimento getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Investimento investimento) {
        this.investimento = investimento;
    }*/

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }   
    

    @Override
    public String toString() {
        return id + ". " + nomeFantasia + " - " + empresa.getNomeFantasia();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Fabrica other = (Fabrica) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
