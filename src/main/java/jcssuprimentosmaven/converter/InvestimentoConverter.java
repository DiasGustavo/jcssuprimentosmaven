/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.InvestimentoDAO;
import jcssuprimentosmaven.domain.Investimento;

/**
 *
 * @author Gustavo
 */
@Converter
public class InvestimentoConverter implements AttributeConverter<Investimento, String>{

    @Override
    public String convertToDatabaseColumn(Investimento attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Investimento convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            InvestimentoDAO idao = new InvestimentoDAO();
            Investimento investimento = idao.buscarPorCodigo(codigo);
            
            return investimento;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
