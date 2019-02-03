/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import jcssuprimentosmaven.domain.Juiz;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class JuizDAOTest {
    
    public JuizDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salvar method, of class JuizDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        System.out.println("salvar");
        Juiz juiz = new Juiz();
        juiz.setEmail("juiz@gmail.com");
        juiz.setLogin("juiz2");
        juiz.setSenha("12345678");
        juiz.setStatus(0);
        JuizDAO instance = new JuizDAO();
        instance.salvar(juiz);
        
    }
    
    @Test
    @Ignore
    public void listar(){
        JuizDAO jdao = new JuizDAO();
        List<Juiz> listaJuizes = jdao.listar();
        
        for(Juiz juiz : listaJuizes){
            System.out.println(juiz);
        }
    }
    
    @Test
    @Ignore
    public void buscarPorcodigo(){
        JuizDAO jdao = new JuizDAO();
        Juiz juiz = jdao.buscarPorCodigo(1L);
        
        System.out.println(juiz);
    }
    
    @Test
    @Ignore
    public void editar(){
        JuizDAO jdao = new JuizDAO();
        Juiz juiz = jdao.buscarPorCodigo(1L);
        
        juiz.setSenha("senha");
        jdao.editar(juiz);
        
        System.out.println(juiz);
    }
    
    @Test
    @Ignore
    public void excluir(){
        JuizDAO jdao = new JuizDAO();
        Juiz juiz = jdao.buscarPorCodigo(1L);
        
        jdao.excluir(juiz);
    }
    
}