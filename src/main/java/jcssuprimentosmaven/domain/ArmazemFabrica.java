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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.FabricaConverter;
import jcssuprimentosmaven.converter.MateriaPrimaConverter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 26/07/2018
 * @version 1.1
 * obs.: adição do atributo fabrica
 */
@Entity
@Table(name = "tbl_armazem_fabrica")
@NamedQueries({
    @NamedQuery(name = "ArmazemFabrica.listar", query = "SELECT armazem FROM ArmazemFabrica armazem"),
    @NamedQuery(name = "ArmazemFabrica.buscarPorCodigo", query = "SELECT armazem FROM ArmazemFabrica armazem WHERE armazem.id =:codigo"),
    @NamedQuery(name = "ArmazemFabrica.buscarPorFabrica", query = "SELECT armazem FROM ArmazemFabrica armazem WHERE armazem.fabrica = :fabrica"),
    @NamedQuery(name = "ArmazemFabrica.buscarPorFabricaMateriaPrima", query = "SELECT armazem FROM ArmazemFabrica armazem WHERE armazem.fabrica =:fabrica AND armazem.materiaPrima =:materia")
})
public class ArmazemFabrica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_armazem")
    private Long id;
               
    @NotNull(message="o campo quantidade da materia prima é obrigatório.")
    @DecimalMin(value="0.00", message="o campo quantidade da materia prima deve ser maior do que 0.00")
    @Digits(integer = 7, fraction = 2, message = "coloque um valor válido para a quantidade da materia prima")
    @Column(name = "quantidade", precision = 9, scale = 2, nullable = false)
    private BigDecimal quantidade;    
       
    @ElementCollection
    @Convert(converter = MateriaPrimaConverter.class, attributeName = "fk_materia_prima")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_materia_prima", referencedColumnName = "cod_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;
    
    @ElementCollection
    @Convert(converter = FabricaConverter.class, attributeName = "fk_fabrica")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabrica", referencedColumnName = "cod_fabrica", nullable = false)
    private Fabrica fabrica;
    
   
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

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public String toString() {
        return "Armazém: " + fabrica.getNomeFantasia();
    }
    
    
   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final ArmazemFabrica other = (ArmazemFabrica) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }   
}
