/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.MeioTransporteDAO;
import jcssuprimentosmaven.domain.MeioTransporte;

/**
 *
 * @author Gustavo
 */
public class MeioTransporteConverter implements AttributeConverter<MeioTransporte, String>{

    @Override
    public String convertToDatabaseColumn(MeioTransporte attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public MeioTransporte convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            MeioTransporte meioTransporte = mtdao.buscarPorCodigo(codigo);
            return meioTransporte;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
