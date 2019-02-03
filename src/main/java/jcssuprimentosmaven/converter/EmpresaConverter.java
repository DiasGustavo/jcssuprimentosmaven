/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import jcssuprimentosmaven.dao.EmpresaDAO;
import jcssuprimentosmaven.domain.Empresa;

/**
 *
 * @author Gustavo
 */
@Converter
public class EmpresaConverter implements AttributeConverter<Empresa, String>{

    @Override
    public String convertToDatabaseColumn(Empresa attribute) {
        try{
            return attribute.getId().toString();
        }catch(RuntimeException ex){
            return null;
        }
    }

    @Override
    public Empresa convertToEntityAttribute(String dbData) {
        try{
            Long codigo = Long.parseLong(dbData);
            EmpresaDAO edao = new EmpresaDAO();
            Empresa empresa = edao.buscarPorCodigo(codigo);
            
            return empresa;
        }catch(RuntimeException ex){
            return null;
        }
    
    }
    
}
