/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.ArmazemDistribuicaoDAO;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
/**
 *
 * @author Gustavo
 */
public class ArmazemDistribuicaoConverter implements AttributeConverter<ArmazemDistribuicao, String> {

    @Override
    public String convertToDatabaseColumn(ArmazemDistribuicao x) {
        try{
            return x.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public ArmazemDistribuicao convertToEntityAttribute(String y) {
        try{
            Long codigo = Long.parseLong(y);
            ArmazemDistribuicaoDAO addao = new ArmazemDistribuicaoDAO();
            ArmazemDistribuicao armazemDistribuicao = addao.buscarPorCodigo(codigo);
            return armazemDistribuicao;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
