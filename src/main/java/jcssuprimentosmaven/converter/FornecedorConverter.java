/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.FornecedorDAO;
import jcssuprimentosmaven.domain.Fornecedor;



/**
 *
 * @author Gustavo
 */
@Converter
public class FornecedorConverter implements AttributeConverter<Fornecedor, String>{

    @Override
    public String convertToDatabaseColumn(Fornecedor attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }      
    }

    @Override
    public Fornecedor convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            FornecedorDAO fdao = new FornecedorDAO();
            Fornecedor fornecedor = fdao.buscarPorCodigo(codigo);
            
            return fornecedor;
        }catch(RuntimeException ex){
            return null;
        }
    }

    
    
}
