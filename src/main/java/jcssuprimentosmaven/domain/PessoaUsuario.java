/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import jcssuprimentosmaven.converter.FabricaConverter;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "tbl_pessoa_usuario")
@PrimaryKeyJoinColumn(name = "id_usuario")
@NamedQueries( 
        @NamedQuery(name = "PessoaUsuario.listar", query = "SELECT pessoaUsuario FROM PessoaUsuario pessoaUsuario WHERE pessoaUsuario.id = :codigo")
)
public class PessoaUsuario extends Pessoa {

            
    @ElementCollection
    @Convert(converter = FabricaConverter.class, attributeName = "fk_fabrica")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_fabrica", referencedColumnName = "cod_fabrica", nullable = false)
    private Fabrica fabrica;

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

<<<<<<< HEAD
  
=======
    @Override
    public String toString() {
        return "PessoaUsuario{" + "fabrica=" + fabrica + " nome: " +this.getNome()+" email: "+ this.getEmail() + '}';
    }
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

    
    
}
