/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import jcssuprimentosmaven.domain.Mensagem;
import org.junit.Ignore;
/**
 *
 * @author Gustavo
 */
public class MensagemDAOTest {
    
    public MensagemDAOTest() {
    }

    /**
     * Test of salvar method, of class MensagemDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        Mensagem mensagem = new Mensagem();
        mensagem.setAssunto("texto de teste");
        mensagem.setDestinatario("jogador1");
        mensagem.setRemetente("jogador 2");
        mensagem.setTexto("texto de teste");
        
        MensagemDAO mdao = new MensagemDAO();
        mdao.salvar(mensagem);
        
    }

    /**
     * Test of listar method, of class MensagemDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        MensagemDAO mdao = new MensagemDAO();
        List<Mensagem> listaMensagens = mdao.listar();
        
        for(Mensagem mensagem : listaMensagens){
            System.out.println(mensagem);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class MensagemDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        MensagemDAO mdao = new MensagemDAO();
        Mensagem mensagem = mdao.buscarPorCodigo(1L);
        
        System.out.println(mensagem);
    }

    /**
     * Test of editar method, of class MensagemDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        MensagemDAO mdao = new MensagemDAO();
        Mensagem mensagem = mdao.buscarPorCodigo(1L);
        
        mensagem.setTexto("Corpo da mensagem");
        
        mdao.editar(mensagem);
        
        System.out.println(mensagem);
    }

    /**
     * Test of excluir method, of class MensagemDAO.
     */
    @Test
    
    public void testExcluir() {
        MensagemDAO mdao = new MensagemDAO();
        Mensagem mensagem = mdao.buscarPorCodigo(1L);
        
        mdao.excluir(mensagem);
    }
    
}
