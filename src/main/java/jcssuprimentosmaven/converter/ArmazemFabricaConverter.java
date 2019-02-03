/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.ArmazemFabricaDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;

/**
 *
 * @author Gustavo
 */
public class ArmazemFabricaConverter implements AttributeConverter<ArmazemFabrica, String> {

    @Override
    public String convertToDatabaseColumn(ArmazemFabrica attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public ArmazemFabrica convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            ArmazemFabricaDAO afdao = new ArmazemFabricaDAO();
            ArmazemFabrica armazemFabrica = afdao.buscarPorCodigo(codigo);
            return armazemFabrica;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
