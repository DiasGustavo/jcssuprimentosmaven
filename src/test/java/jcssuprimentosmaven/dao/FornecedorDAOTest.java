/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import jcssuprimentosmaven.domain.Fornecedor;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class FornecedorDAOTest {
    
    public FornecedorDAOTest() {
    }

    /**
     * Test of salvar method, of class FornecedorDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setFantasia("FornecedorCompleto");
        
        FornecedorDAO fdao = new FornecedorDAO();
        fdao.salvar(fornecedor);
    }

    /**
     * Test of listar method, of class FornecedorDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        FornecedorDAO fdao = new FornecedorDAO();
        List<Fornecedor> listaFornecedores = fdao.listar();
        
        for(Fornecedor fornecedor : listaFornecedores){
            System.out.println(fornecedor);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class FornecedorDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        FornecedorDAO fdao = new FornecedorDAO();
        Fornecedor fornecedor = fdao.buscarPorCodigo(1L);
        
        System.out.println(fornecedor);
    }

    /**
     * Test of editar method, of class FornecedorDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        FornecedorDAO fdao = new FornecedorDAO();
        Fornecedor fornecedor = fdao.buscarPorCodigo(1L);
        
        fornecedor.setFantasia("FantasiaAlterada");
        
        fdao.editar(fornecedor);
        
        System.out.println(fornecedor);
    }

    /**
     * Test of excluir method, of class FornecedorDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
        FornecedorDAO fdao = new FornecedorDAO();
        Fornecedor fornecedor = fdao.buscarPorCodigo(1L);
        
        fdao.excluir(fornecedor);
    }
    
}
