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
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.domain.Transportadora;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class MeioTransporteDAOTest {
    
    public MeioTransporteDAOTest() {
    }

    /**
     * Test of salvar method, of class MeioTransporteDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        MeioTransporte meio = new MeioTransporte();
        meio.setDescricao("Aquático");
        meio.setTaxa_seguro(new BigDecimal(10));
        meio.setTaxa_transporte(new BigDecimal(20));
        meio.setTempo("1");
        
        TransportadoraDAO tdao = new TransportadoraDAO();
        Transportadora transportadora = tdao.buscarPorCodigo(1L);
        //meio.setTransportadora(transportadora);
        
        MeioTransporteDAO mdao = new MeioTransporteDAO();
        mdao.salvar(meio);
    }

    /**
     * Test of listar method, of class MeioTransporteDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        MeioTransporteDAO mdao = new MeioTransporteDAO();
        List<MeioTransporte> listaMeios = mdao.listar();
        
        for(MeioTransporte meio : listaMeios){
            System.out.println(meio);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class MeioTransporteDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        MeioTransporteDAO mdao = new MeioTransporteDAO();
        MeioTransporte meio = mdao.buscarPorCodigo(1L);
        
        System.out.println(meio);
    }

    /**
     * Test of editar method, of class MeioTransporteDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        MeioTransporteDAO mdao = new MeioTransporteDAO();
        MeioTransporte meio = mdao.buscarPorCodigo(1L);
        meio.setTaxa_seguro(new BigDecimal(5));
        
        mdao.editar(meio);
        
        System.out.println(meio);
    }

    /**
     * Test of excluir method, of class MeioTransporteDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
       MeioTransporteDAO mdao = new MeioTransporteDAO();
       MeioTransporte meio = mdao.buscarPorCodigo(1L); 
       
       mdao.excluir(meio);
    }
    
}
