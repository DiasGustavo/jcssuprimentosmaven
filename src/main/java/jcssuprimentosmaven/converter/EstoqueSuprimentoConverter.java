/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.EstoqueSuprimentoDAO;
import jcssuprimentosmaven.domain.EstoqueSuprimento;

/**
 *
 * @author Gustavo
 */
public class EstoqueSuprimentoConverter implements AttributeConverter<EstoqueSuprimento, String> {

    @Override
    public String convertToDatabaseColumn(EstoqueSuprimento attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public EstoqueSuprimento convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            EstoqueSuprimento estoque = esdao.buscarPorCodigo(codigo);
            return estoque;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
