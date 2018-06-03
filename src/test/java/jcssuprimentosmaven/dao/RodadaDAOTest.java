/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.domain.Juiz;
import org.junit.Ignore;
/**
 *
 * @author Gustavo
 */
public class RodadaDAOTest {
    
    public RodadaDAOTest() {
    }

    /**
     * Test of salvar method, of class RodadaDAO.
     */
    @Test
    
    public void testSalvar() {
        Rodada rodada = new Rodada();
        rodada.setDataInicio(new Date(2017,1,10));
        rodada.setDataFim(new Date(2017,1,20));
        rodada.setDemanda("10");
        rodada.setCargaFerroviaria(10);
        rodada.setCargaRodoviaria(20);
        
        JuizDAO jdao = new JuizDAO();
        Juiz juiz = jdao.buscarPorCodigo(1L);
        rodada.setJuiz(juiz);
        
        RodadaDAO rdao = new RodadaDAO();
        rdao.salvar(rodada);
    }

    /**
     * Test of litar method, of class RodadaDAO.
     */
    @Test
    @Ignore
    public void testLitar() {
        RodadaDAO rdao = new RodadaDAO();
        List<Rodada> listaRodadas = rdao.litar();
        
        for(Rodada rodada: listaRodadas){
            System.out.println(rodada);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class RodadaDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        RodadaDAO rdao = new RodadaDAO();
        Rodada rodada = rdao.buscarPorCodigo(1L);
        
        System.out.println(rodada);
    }

    /**
     * Test of editar method, of class RodadaDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        RodadaDAO rdao = new RodadaDAO();
        Rodada rodada = rdao.buscarPorCodigo(1L);
        
        rodada.setDemanda("200");
        
        System.out.println(rodada);
    }

    /**
     * Test of excluir method, of class RodadaDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
        RodadaDAO rdao = new RodadaDAO();
        Rodada rodada = rdao.buscarPorCodigo(1L);
        
        rdao.excluir(rodada);
    }
    
}
