/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.PessoaUsuario;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Gustavo
 */
public class PessoaTest {
    
    @Test
    @Ignore
    public void testSalvar(){
        PessoaUsuario pu = new PessoaUsuario();
        pu.setLogin("teste");
        pu.setNome("usuario");
        pu.setSenha("qwe123");
        pu.setStatus(1);
        pu.setEmail("teste@gmail.com");
        
        FabricaDAO fdao = new FabricaDAO();       
        pu.setFabrica(fdao.buscarPorCodigo(1L));
        
        PessoaUsuarioDAO pudao = new PessoaUsuarioDAO();
        pudao.salvar(pu);
    }
    @Test
    @Ignore
    public void listarTest(){
        PessoaUsuarioDAO pudao = new PessoaUsuarioDAO();
        List<PessoaUsuario> listaRodadas = pudao.listar();
        
        for(PessoaUsuario pessoa: listaRodadas){
            System.out.println(pessoa);
        }
    }
    @Test
    //@Ignore
    public void testBuscarPorCodigo() {
        PessoaUsuarioDAO pudao = new PessoaUsuarioDAO();
        PessoaUsuario rodada = pudao.buscarPorCodigo(1L);
        
        System.out.println(rodada);
    }
}
