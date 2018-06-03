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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_transportadora")
@NamedQueries({
    @NamedQuery(name = "Transportadora.listar", query = "SELECT transportadora FROM Transportadora transportadora"),
    @NamedQuery(name = "Transportadora.buscarPorCodigo" , query = "SELECT transportadora FROM Transportadora transportadora WHERE transportadora.id = :codigo")
})
public class Transportadora implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Opção para gerar as id com autoincremento
    @Column(name = "cod_transportadora")
    private Long id;
    
    @NotEmpty(message = "o campo fantasia é obrigatório")
    @Size(min=1, max=50, message = "o nome fantasia tem que ter entre 1 e 50 caracteres") 
    @Column(name = "fantasia", length = 50)
    private String nomeFantasia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnomeFantasia() {
        return nomeFantasia;
    }

    public void setnomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public String toString() {
        return "Transportadora{" + "id=" + id + ", Nome Fantasia=" + nomeFantasia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Transportadora other = (Transportadora) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
