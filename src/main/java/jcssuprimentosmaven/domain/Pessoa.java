/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import jcssuprimentosmaven.converter.FabricaConverter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */

@Entity
@Table(name = "tbl_pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.listar", query = "SELECT pessoa FROM Pessoa pessoa"),
    @NamedQuery(name = "Pessoa.buscarPorCodigo", query = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.id = :codigo"),
    @NamedQuery(name = "Pessoa.buscarPorMatricula", query = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.matricula = :matricula")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pessoa")
    private Long id;
    
    @NotEmpty(message = "o campo nome é obrigatório")
    @Size(min=1, max=80, message = "O nome tem que ter entre 1 e 80 caracteres ") 
    @Column(name = "nome", length = 80)
    private String nome;
    
    @NotEmpty(message = "o campo matrícula é obrigatório")
    @Size(min=1, max=20, message = "O matrícula tem que ter entre 1 e 20 caracteres ") 
    @Column(name = "matricula", length = 20)
    private String matricula;
          
    @NotEmpty(message = "o campo email é obrigatório")
    @Email(message = "Email informado não é válido")
    @Column(name = "email", length = 60)
    private String email;    
    
    @Column(name = "status")
    private int status;

    @ElementCollection
    @Convert(converter = FabricaConverter.class, attributeName = "fk_jogador")
    //@NotEmpty(message = "O campo fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_jogador", referencedColumnName = "cod_jogador", nullable = false)
    private Jogador jogador; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", email=" + email + ", status=" + status + ", jogador=" + jogador + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
