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
import jcssuprimentosmaven.domain.Investimento;
import jcssuprimentosmaven.domain.Empresa;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class InvestimentoDAOTest {
    
    public InvestimentoDAOTest() {
    }

    /**
     * Test of salvar method, of class InvestimentoDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        Investimento investimento = new Investimento();
        investimento.setDescricao("Propaganda");
        investimento.setValor(new BigDecimal(10));
        
        EmpresaDAO edao = new EmpresaDAO();
        Empresa empresa = edao.buscarPorCodigo(2L);
        investimento.setEmpresa(empresa);
        
        InvestimentoDAO idao = new InvestimentoDAO();
        idao.salvar(investimento);
        
    }

    /**
     * Test of listar method, of class InvestimentoDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        InvestimentoDAO idao = new InvestimentoDAO();
        List<Investimento> listaInvestimentos = idao.listar();
        
        for(Investimento investimento : listaInvestimentos){
            System.out.println(investimento);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class InvestimentoDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        InvestimentoDAO idao = new InvestimentoDAO();
        Investimento investimento = idao.buscarPorCodigo(1L);
        
        System.out.println(investimento);
    }

    /**
     * Test of editar method, of class InvestimentoDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        InvestimentoDAO idao = new InvestimentoDAO();
        Investimento investimento = idao.buscarPorCodigo(1L);
        
        investimento.setDescricao("invest empresa");
        idao.editar(investimento);
        
        System.out.println(investimento);
    }

    /**
     * Test of excluir method, of class InvestimentoDAO.
     */
    @Test
    public void testExcluir() {
        InvestimentoDAO idao = new InvestimentoDAO();
        Investimento investimento = idao.buscarPorCodigo(1L);
        
        idao.excluir(investimento);
    }
    
}
