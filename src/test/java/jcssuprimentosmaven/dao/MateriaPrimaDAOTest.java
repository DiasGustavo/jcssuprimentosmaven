/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.domain.Fornecedor;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class MateriaPrimaDAOTest {
    
    public MateriaPrimaDAOTest() {
    }

    /**
     * Test of salvar method, of class MateriaPrimaDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        MateriaPrima materia = new MateriaPrima();
        materia.setDescricao("plástico");
        materia.setPreco(new BigDecimal(25));
        materia.setQuantidade("100");
        //materia.setTaxaDiaria(new BigDecimal(20));
        materia.setTempoEntrega("10");
        materia.setStatus("Ativo");
        
        FornecedorDAO fdao = new FornecedorDAO();
        Fornecedor fornecedor = fdao.buscarPorCodigo(1L);
        
       // materia.setFornecedor(fornecedor);
        
        MateriaPrimaDAO mdao = new MateriaPrimaDAO();
        mdao.salvar(materia);
    }

    /**
     * Test of listar method, of class MateriaPrimaDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        MateriaPrimaDAO mdao = new MateriaPrimaDAO();
        List<MateriaPrima> listaMateria = mdao.listar();
        
        for(MateriaPrima materia: listaMateria){
            System.out.println(materia);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class MateriaPrimaDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        MateriaPrimaDAO mdao = new MateriaPrimaDAO();
        MateriaPrima materia = mdao.buscarPorCodigo(1L);
        
        System.out.println(materia);
    }

    /**
     * Test of editar method, of class MateriaPrimaDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        MateriaPrimaDAO mdao = new MateriaPrimaDAO();
        MateriaPrima materia = mdao.buscarPorCodigo(1L);
        
        materia.setDescricao("Plástico_10");
        
        mdao.editar(materia);
    }

    /**
     * Test of excluir method, of class MateriaPrimaDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
        MateriaPrimaDAO mdao = new MateriaPrimaDAO();
        MateriaPrima materia = mdao.buscarPorCodigo(1L);
        
        mdao.excluir(materia);
    }
    
}
