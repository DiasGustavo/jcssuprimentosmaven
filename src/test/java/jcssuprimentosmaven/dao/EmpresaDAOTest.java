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
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Rodada;
import org.junit.Ignore;

/**
 *
 * @author Gustavo
 */
public class EmpresaDAOTest {
    
    public EmpresaDAOTest() {
    }

    /**
     * Test of salvar method, of class EmpresaDAO.
     */
    @Test
    @Ignore
    public void testSalvar() {
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("Primeira Empresa");
        
        TransportadoraDAO tdao = new TransportadoraDAO();
        Transportadora transportadora = tdao.buscarPorCodigo(1L);
        //empresa.setTransportadora(transportadora);
        
        FornecedorDAO fdao = new FornecedorDAO();
        Fornecedor fornecedor = fdao.buscarPorCodigo(1L);
        //empresa.setFornecedor(fornecedor);
        
        JogadorDAO jdao = new JogadorDAO();
        Jogador jogador = jdao.buscarPorCodigo(1L);
        empresa.setJogador(jogador);
        
        empresa.setLogomarca("logomarcaPadrão");
        
        RodadaDAO rdao = new RodadaDAO();
        Rodada rodada = rdao.buscarPorCodigo(1L);
        //empresa.setRodada(rodada);
        
        FabricaDAO fbdao = new FabricaDAO();
        Fabrica fabrica = fbdao.buscarPorCodigo(1L);
        //empresa.setFabrica(fabrica);
        
        //empresa.setPrecoCusto(BigDecimal.ZERO);
        //empresa.setPrecoVenda(BigDecimal.ZERO);
        
        EmpresaDAO edao = new EmpresaDAO();
        edao.salvar(empresa);
    }

    /**
     * Test of listar method, of class EmpresaDAO.
     */
    @Test
    @Ignore
    public void testListar() {
        EmpresaDAO edao = new EmpresaDAO();
        List<Empresa> listaEmpresas = edao.listar();
        
        for(Empresa empresa: listaEmpresas){
            System.out.println(empresa);
        }
    }

    /**
     * Test of buscarPorCodigo method, of class EmpresaDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorCodigo() {
        EmpresaDAO edao = new EmpresaDAO();
        Empresa empresa = edao.buscarPorCodigo(1L);
        
        System.out.println(empresa);
    }

    /**
     * Test of editar method, of class EmpresaDAO.
     */
    @Test
    @Ignore
    public void testEditar() {
       EmpresaDAO edao = new EmpresaDAO();
       Empresa empresa = edao.buscarPorCodigo(1L); 
       
       empresa.setLogomarca("sem logomarcar");
       
       edao.editar(empresa);
       
       System.out.println(empresa);
    }

    /**
     * Test of excluir method, of class EmpresaDAO.
     */
    @Test
    @Ignore
    public void testExcluir() {
       EmpresaDAO edao = new EmpresaDAO();
       Empresa empresa = edao.buscarPorCodigo(1L);
       
       edao.excluir(empresa);
    }
    
}
