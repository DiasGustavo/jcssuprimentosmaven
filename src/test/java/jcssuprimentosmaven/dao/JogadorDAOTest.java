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
    @Ignore
    public void testSalvar() {
        Jogador jogador = new Jogador();
        jogador.setEmail("jogador@gmail.com");
        jogador.setNome("gustavo");
        jogador.setLogin("gustavo");
        jogador.setSenha("qwe123");
        jogador.setFuncao(0);
        jogador.setStatus(0);
        
        JogadorDAO jdao = new JogadorDAO();
        jdao.salvar(jogador);
    }
    @Test
    
    public void autenticar(){
        Jogador jogador = new Jogador();
        jogador.setLogin("gustavo");
        jogador.setSenha("qwe123");
        jogador.setFuncao(2);
        
        JogadorDAO jdao = new JogadorDAO();
        Jogador jogadorAutenticado = jdao.autenticarUsuario(jogador.getLogin(), jogador.getSenha(), jogador.getFuncao());
        
        System.out.println(jogadorAutenticado);
        
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
    
    public void testEditar() {
        JogadorDAO jdao = new JogadorDAO();
        Jogador jogador = jdao.buscarPorCodigo(1L);
        
        jogador.setFuncao(2);
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
