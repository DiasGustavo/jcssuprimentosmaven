/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.DistribuidorDAO;
import jcssuprimentosmaven.domain.Distribuidor;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class DistribuidorController {
    private Distribuidor distribuidorCadastro;
    
    private List<Distribuidor> listaDistribuidores;
    private List<Distribuidor> listaDistribuidoresFiltrados;
    
    private Long codigo;

    public Distribuidor getDistribuidorCadastro() {
        return distribuidorCadastro;
    }

    public void setDistribuidorCadastro(Distribuidor distribuidorCadastro) {
        this.distribuidorCadastro = distribuidorCadastro;
    }

    public List<Distribuidor> getListaDistribuidores() {
        return listaDistribuidores;
    }

    public void setListaDistribuidores(List<Distribuidor> listaDistribuidores) {
        this.listaDistribuidores = listaDistribuidores;
    }

    public List<Distribuidor> getListaDistribuidoresFiltrados() {
        return listaDistribuidoresFiltrados;
    }

    public void setListaDistribuidoresFiltrados(List<Distribuidor> listaDistribuidoresFiltrados) {
        this.listaDistribuidoresFiltrados = listaDistribuidoresFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            edao.salvar(distribuidorCadastro);
            
            ViewUtil.addMsgInfo("Distribuidor Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Distribuidor " + ex.getMessage());
        }
    }
    
     public void salvarSemAviso(){
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            edao.salvar(distribuidorCadastro);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Distribuidor " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            listaDistribuidores = edao.listar();
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os distribuidores " + ex.getMessage());
        }
    }
    
     public void carregarDados(){
        try{
            if(codigo != null){
                DistribuidorDAO edao = new DistribuidorDAO();
                distribuidorCadastro = edao.buscarPorCodigo(codigo);
            }else{
                distribuidorCadastro = new Distribuidor();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da Distribuidor " + ex.getMessage());
        }
    }
     
    public List<Distribuidor> buscarPorNome(String nome){
        List<Distribuidor> empresas = null;
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            empresas = edao.buscarPorNome(nome);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Distribuidor " + ex.getMessage());
        }
        return empresas;
    } 
    
   public List<Distribuidor> buscarPorEstoque(EstoqueDistribuicao estoque){
        List<Distribuidor> empresas = null;
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            empresas = edao.buscarPorEstoque(estoque);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Distribuidor " + ex.getMessage());
        }
        return empresas;
    }
     
    public void editar(){
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            edao.editar(distribuidorCadastro);
            
            ViewUtil.addMsgInfo("Distribuidor Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o Distribuidor " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            DistribuidorDAO edao = new DistribuidorDAO();
            edao.excluir(distribuidorCadastro);
            
            ViewUtil.addMsgInfo("Distribuidor Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o Distribuidor " + ex.getMessage());
        }
    }
}
