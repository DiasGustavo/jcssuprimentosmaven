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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_mensagem")
@NamedQueries({
    @NamedQuery(name = "Mensagem.listar", query = "SELECT mensagem FROM Mensagem mensagem"),
    @NamedQuery(name = "Mensagem.buscarPorCodigo", query = "SELECT mensagem FROM Mensagem mensagem WHERE mensagem.id = :codigo"),
    @NamedQuery(name = "Mensagem.buscarPorDestinatario", query = "SELECT mensagem FROM Mensagem mensagem WHERE mensagem.destinatario = :nome OR mensagem.destinatario = :nomeGeral"),
    @NamedQuery(name = "Mensagem.buscarPorRemetente", query = "SELECT mensagem FROM Mensagem mensagem WHERE mensagem.remetente = :nome")
        
})
public class Mensagem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_mensagem")
    private Long id;
    
    @NotEmpty(message = "O campo assunto é obrigatório")
    @Size(min = 1, max = 100, message = "O campo assunto deve ter entre 1 e 100 caracteres")
    @Column(name = "assunto", length = 100)
    private String assunto;
    
    @NotEmpty(message = "O campo texto é obrigatório")
    @Size(min = 1, max = 200, message = "O campo texto deve ter entre 1 e 200 caracteres")
    @Column(name = "texto", length = 200)
    private String texto;
    
    @NotEmpty(message = "O campo remetente é obrigatório")
    @Size(min = 1, max = 200, message = "O campo remetente deve ter entre 1 e 200 caracteres")
    @Column(name = "remetente", length = 200)
    private String remetente;
    
    @NotEmpty(message = "O campo destinatário é obrigatório")
    @Size(min = 1, max = 200, message = "O campo destinatário deve ter entre 1 e 200 caracteres")
    @Column(name = "destinatario", length = 200)
    private String destinatario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", assunto=" + assunto + ", texto=" + texto + ", remetente=" + remetente + ", destinatario=" + destinatario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
