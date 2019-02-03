/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */

@Entity
@Table(name = "tbl_pessoa")
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
    
    @NotEmpty(message = "o campo login é obrigatório")
    @Size(min=1, max=20, message = "O login tem que ter entre 1 e 20 caracteres ") 
    @Column(name = "login", length = 20)
    private String login;
    
    @NotEmpty(message = "o campo senha é obrigatório")
    @Size(min=1, max=20, message = "A senha tem que ter entre 1 e 10 caracteres")
    @Column(name = "senha", length = 10)
    private String senha;
    
    @NotEmpty(message = "o campo email é obrigatório")
    @Email(message = "Email informado não é válido")
    @Column(name = "email", length = 60)
    private String email;    
    
    @Column(name = "status")
    private int status;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
    
}
