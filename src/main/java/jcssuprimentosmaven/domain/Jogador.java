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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_jogador")
@NamedQueries({
    @NamedQuery(name = "Jogador.listar", query = "SELECT jogador FROM Jogador jogador"),
    @NamedQuery(name = "Jogador.buscarPorCodigo", query = "SELECT jogador FROM Jogador jogador WHERE jogador.id = :codigo" ),
    @NamedQuery(name = "Jogador.buscarPorNome", query = "SELECT jogador FROM Jogador jogador WHERE jogador.nome = :nome"),
    @NamedQuery(name = "Jogador.autenticarUsuario", query = "SELECT jogador FROM Jogador jogador WHERE jogador.login = :login AND jogador.funcao = :funcao AND jogador.senha = :senha")
})
public class Jogador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//utilizando esta opção para gerar os id de forma autoincremento
    @Column(name = "cod_jogador")
    private Long id;
    
    @NotEmpty(message = "o campo nome é obrigatório")
    @Size(min=1, max=50, message = "O nome tem que ter entre 1 e 80 caracteres") 
    @Column(name = "nome", length = 80)
    private String nome;
    
    @NotEmpty(message = "o campo login é obrigatório")
    @Size(min=1, max=50, message = "O login tem que ter entre 1 e 20 caracteres") 
    @Column(name = "login", length = 20)
    private String login;
    
    @NotEmpty(message = "o campo senha é obrigatório")
    @Size(min=1, max=50, message = "A senha tem que ter entre 1 e 10 caracteres")
    @Column(name = "senha", length = 10)
    private String senha;
    
    @Email(message = "Email informado não é válido")
    @Column(name = "email", length = 60)
    private String email;    
    
    @Column(name = "status")
    private int status;
    
    @Column(name = "funcao")
    private int funcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }
    

    @Override
    public String toString() {
        return id + ". " +  nome + ", login: " + login ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Jogador other = (Jogador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
