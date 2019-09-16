/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.MateriaPrimaDAO;
import jcssuprimentosmaven.domain.MateriaPrima;

/**
 *
 * @author Gustavo
 */
public class MateriaPrimaConverter implements AttributeConverter<MateriaPrima, String>{

    @Override
    public String convertToDatabaseColumn(MateriaPrima attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }   
    }
    

    @Override
    public MateriaPrima convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            MateriaPrimaDAO mpdao = new MateriaPrimaDAO();
            MateriaPrima materia = mpdao.buscarPorCodigo(codigo);
            
            return materia;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
