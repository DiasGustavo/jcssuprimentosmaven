/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.TransportadoraDAO;
import jcssuprimentosmaven.domain.Transportadora;

/**
 *
 * @author Gustavo
 */
@Converter
public class TransportadoraConverter implements AttributeConverter<Transportadora, String> {

    @Override
    public String convertToDatabaseColumn(Transportadora attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Transportadora convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            TransportadoraDAO tdao = new TransportadoraDAO();
            Transportadora transportadora = tdao.buscarPorCodigo(codigo);
            
            return transportadora;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
