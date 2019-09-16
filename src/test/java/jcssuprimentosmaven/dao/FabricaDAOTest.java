/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.math.BigDecimal;
import jcssuprimentosmaven.domain.Fabrica;
import org.junit.Test;

/**
 *
 * @author Gustavo
 */
public class FabricaDAOTest {
    @Test
    public void salvar(){
        Fabrica fab = new Fabrica();
        fab.setCustoTransformacao(BigDecimal.TEN);
        fab.setDemanda("10");
        fab.setFatorTransformacao(BigDecimal.TEN);
        fab.setNomeFantasia("Test");
        fab.setPesquisa(BigDecimal.ZERO);
        fab.setTaxaServicoCliente(BigDecimal.ZERO);
        fab.setTempoTransformacao(1);
        EmpresaDAO edao = new EmpresaDAO();
        //fab.setEmpresa(edao.buscarPorCodigo(1L));
        PessoaDAO pudao = new PessoaDAO();
        
        //fab.setPessoa(pudao.buscarPorCodigo(1L));
        
        FabricaDAO fdao = new FabricaDAO();
        fdao.salvar(fab);
        
    }
}
