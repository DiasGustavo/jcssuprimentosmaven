/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import jcssuprimentosmaven.domain.Jogador;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class JogadorDAOTest {
    
    public JogadorDAOTest() {
    }

    /**
     * Test of salvar method, of class JogadorDAO.
     */
    @Test
    
    public void testSalvar() {
        Jogador jogador = new Jogador();
        jogador.setEmail("jogador@gmail.com");
        jogador.setLogin("jogador1");
        jogador.setSenha("qwe123");
        jogador.setStatus("A");
        
        JogadorDAO jdao = new JogadorDAO();
        jdao.salvar(jogador);
    }

    /**
     * Test of listar method, of class JogadorDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        JogadorDAO jdao = new JogadorDAO();
        List<Jogador> listaJogadores = jdao.listar();
        
        for(Jogador jogador: listaJogadores){
            System.out.println(jogador);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class JogadorDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        JogadorDAO jdao = new JogadorDAO();
        Jogador jogador = jdao.buscarPorCodigo(1L);
        
        System.out.println(jogador);
    }

    /**
     * Test of editar method, of class JogadorDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        JogadorDAO jdao = new JogadorDAO();
        Jogador jogador = jdao.buscarPorCodigo(1L);
        
        jogador.setLogin("fulano");
        jdao.editar(jogador);
        
        System.out.println(jogador);
        
    }

    /**
     * Test of excluir method, of class JogadorDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
        JogadorDAO jdao = new JogadorDAO();
        Jogador jogador = jdao.buscarPorCodigo(1L);
        
        jdao.excluir(jogador);
    }
    
}
