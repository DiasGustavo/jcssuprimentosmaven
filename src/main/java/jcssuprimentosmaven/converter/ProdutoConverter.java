/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import jcssuprimentosmaven.dao.ProdutoDAO;
import jcssuprimentosmaven.domain.Produto;
/**
 *
 * @author Gustavo
 */
public class ProdutoConverter implements AttributeConverter<Produto, String> {

    @Override
    public String convertToDatabaseColumn(Produto x) {
        try{
            return x.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Produto convertToEntityAttribute(String y) {
        try{
            Long codigo = Long.parseLong(y);
            ProdutoDAO pdao = new ProdutoDAO();
            Produto produto = pdao.buscarPorCodigo(codigo);
            return produto;
        }catch(RuntimeException ex){
            return null;
        }
    }
    
}
