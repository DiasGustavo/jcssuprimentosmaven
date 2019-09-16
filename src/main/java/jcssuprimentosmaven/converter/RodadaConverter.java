/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.RodadaDAO;
import jcssuprimentosmaven.domain.Rodada;
/**
 *
 * @author Gustavo
 */
@Converter
public class RodadaConverter implements AttributeConverter<Rodada, String>{

    @Override
    public String convertToDatabaseColumn(Rodada attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Rodada convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            RodadaDAO rdao = new RodadaDAO();
            Rodada rodada = rdao.buscarPorCodigo(codigo);
            
            return rodada;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
