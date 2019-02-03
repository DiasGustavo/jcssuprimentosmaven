/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.EstoqueDistribuicaoDAO;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
/**
 *
 * @author Gustavo
 */
public class EstoqueDistribuicaoConverter implements AttributeConverter<EstoqueDistribuicao, String>{

    @Override
    public String convertToDatabaseColumn(EstoqueDistribuicao x) {
        try{
            return x.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public EstoqueDistribuicao convertToEntityAttribute(String y) {
        try{
            Long codigo = Long.parseLong(y);
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            EstoqueDistribuicao estoqueDistribuicao = eddao.buscarPorCodigo(codigo);
            return estoqueDistribuicao;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
