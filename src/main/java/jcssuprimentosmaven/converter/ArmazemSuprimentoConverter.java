/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.ArmazemSuprimentoDAO;
import jcssuprimentosmaven.domain.ArmazemSuprimento;

/**
 *
 * @author Gustavo
 */
public class ArmazemSuprimentoConverter implements AttributeConverter<ArmazemSuprimento, String> {

    @Override
    public String convertToDatabaseColumn(ArmazemSuprimento x) {
        try{
            return x.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public ArmazemSuprimento convertToEntityAttribute(String y) {
        try{
            Long codigo = Long.parseLong(y);
            ArmazemSuprimentoDAO asdao = new ArmazemSuprimentoDAO();
            ArmazemSuprimento armazemSuprimento = asdao.buscarPorCodigo(codigo);
            return armazemSuprimento;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
