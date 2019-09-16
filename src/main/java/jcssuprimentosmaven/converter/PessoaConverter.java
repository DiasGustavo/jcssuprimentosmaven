/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.PessoaDAO;
import jcssuprimentosmaven.domain.Pessoa;
import jcssuprimentosmaven.domain.Produto;

/**
 *
 * @author Gustavo
 */
public class PessoaConverter implements AttributeConverter<Pessoa, String>{

    @Override
    public String convertToDatabaseColumn(Pessoa x) {
        try{
            return x.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Pessoa convertToEntityAttribute(String y) {
        try{
            Long codigo = Long.parseLong(y);
            PessoaDAO pdao = new PessoaDAO();
            Pessoa pessoa = pdao.buscarPorCodigo(codigo);
            
            return pessoa;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
