/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.JogadorDAO;
import jcssuprimentosmaven.domain.Jogador;

/**
 *
 * @author Gustavo
 */
@Converter
public class JogadorConverter implements AttributeConverter<Jogador, String> {

    @Override
    public String convertToDatabaseColumn(Jogador attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Jogador convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            JogadorDAO jdao = new JogadorDAO();
            Jogador jogador = jdao.buscarPorCodigo(codigo);
            
            return jogador;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
