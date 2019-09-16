/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.EstoqueDistribuicaoDAO;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class EstoqueDistribuicaoController {
    private EstoqueDistribuicao estoqueCadastro;
    
    private List<EstoqueDistribuicao> listaEstoques;
    private List<EstoqueDistribuicao> listaEstoquesFiltrados;
    
    private Long codigo;

    public EstoqueDistribuicao getEstoqueCadastro() {
        return estoqueCadastro;
    }

    public void setEstoqueCadastro(EstoqueDistribuicao estoqueCadastro) {
        this.estoqueCadastro = estoqueCadastro;
    }

    public List<EstoqueDistribuicao> getListaEstoques() {
        return listaEstoques;
    }

    public void setListaEstoques(List<EstoqueDistribuicao> listaEstoques) {
        this.listaEstoques = listaEstoques;
    }

    public List<EstoqueDistribuicao> getListaEstoquesFiltrados() {
        return listaEstoquesFiltrados;
    }

    public void setListaEstoquesFiltrados(List<EstoqueDistribuicao> listaEstoquesFiltrados) {
        this.listaEstoquesFiltrados = listaEstoquesFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            eddao.salvar(estoqueCadastro);
            
            ViewUtil.addMsgInfo("Estoque Cadastrado com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao cadastrar o estoque " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            listaEstoques = eddao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os estoques " + ex.getMessage());
        }
    }
    
    /*public List<EstoqueDistribuicao> buscarPorEmpresa (Empresa empresa){
        List<EstoqueDistribuicao> estoques = null;
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            estoques = eddao.buscarPorEmpresa(empresa);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }
    
     public List<EstoqueDistribuicao> buscarPorEmpresaEstoque (Empresa empresa, String estoque){
        List<EstoqueDistribuicao> estoques = null;
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            estoques = eddao.buscarPorEmpresaEstoque(empresa, estoque);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }*/
    
    public List<EstoqueDistribuicao> buscarPorProduto (Produto produto){
        List<EstoqueDistribuicao> estoques = null;
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            estoques = eddao.buscarPorProduto(produto);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }
    
    public List<EstoqueDistribuicao> buscarPorArmazem (ArmazemDistribuicao armazemDistribuicao){
        List<EstoqueDistribuicao> estoques = null;
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            estoques = eddao.buscarPorArmazem(armazemDistribuicao);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
                estoqueCadastro = eddao.buscarPorCodigo(codigo);
            }else{
                estoqueCadastro = new EstoqueDistribuicao();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu ao carregar os dados do estoque " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            eddao.editar(estoqueCadastro);
            
            ViewUtil.addMsgInfo("Estoque Editado com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o estoque " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            eddao.excluir(estoqueCadastro);
            
            ViewUtil.addMsgInfo("Estoque Excluído com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o estoque " + ex.getMessage());
        }
    }        
}
