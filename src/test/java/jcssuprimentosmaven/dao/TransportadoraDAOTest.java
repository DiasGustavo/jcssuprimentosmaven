/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import jcssuprimentosmaven.domain.Transportadora;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class TransportadoraDAOTest {

    public TransportadoraDAOTest() {
    }

    /**
     * Test of salvar method, of class TransportadoraDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        Transportadora transportadora = new Transportadora();
        transportadora.setnomeFantasia("TransporteBem");

        TransportadoraDAO tdao = new TransportadoraDAO();
        tdao.salvar(transportadora);
    }

    /**
     * Test of listar method, of class TransportadoraDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        TransportadoraDAO tdao = new TransportadoraDAO();
        List<Transportadora> listaTransportadoras = tdao.listar();

        for (Transportadora transportadora : listaTransportadoras) {
            System.out.println(transportadora);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class TransportadoraDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        TransportadoraDAO tdao = new TransportadoraDAO();
        Transportadora transportadora = tdao.buscarPorCodigo(1L);

        System.out.println(transportadora);
    }

    /**
     * Test of editar method, of class TransportadoraDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
        TransportadoraDAO tdao = new TransportadoraDAO();
        Transportadora transportadora = tdao.buscarPorCodigo(1L);

        transportadora.setnomeFantasia("transporteBem LTDA");
        tdao.editar(transportadora);

        System.out.println(transportadora);
    }

    /**
     * Test of excluir method, of class TransportadoraDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
        TransportadoraDAO tdao = new TransportadoraDAO();
        Transportadora transportadora = tdao.buscarPorCodigo(1L);
        
        tdao.excluir(transportadora);

    }

}
