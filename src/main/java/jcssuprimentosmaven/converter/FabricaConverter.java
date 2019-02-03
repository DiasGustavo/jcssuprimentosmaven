/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.domain.Fabrica;

/**
 *
 * @author Gustavo
 */
@Converter
public class FabricaConverter implements AttributeConverter<Fabrica, String> {

    @Override
    public String convertToDatabaseColumn(Fabrica attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Fabrica convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            FabricaDAO fdao = new FabricaDAO();
            Fabrica fabrica = fdao.buscarPorCodigo(codigo);
            return fabrica;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
