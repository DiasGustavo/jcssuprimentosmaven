/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.DistribuidorDAO;
import jcssuprimentosmaven.domain.Distribuidor;

/**
 *
 * @author Gustavo
 */
public class DistribuidorConverter implements AttributeConverter<Distribuidor, String>{

    @Override
    public String convertToDatabaseColumn(Distribuidor attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Distribuidor convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            DistribuidorDAO ddao = new DistribuidorDAO();
            Distribuidor distribuidor = ddao.buscarPorCodigo(codigo);
            return distribuidor;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
