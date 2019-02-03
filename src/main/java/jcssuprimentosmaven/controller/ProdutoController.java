/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.ProdutoDAO;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class ProdutoController {
    private Produto produtoCadastro;
    
    private List<Produto> listaProdutos;
    private List<Produto> listaProdutosFiltrados;
    
    private Long codigo;

    public Produto getProdutoCadastro() {
        return produtoCadastro;
    }

    public void setProdutoCadastro(Produto produtoCadastro) {
        this.produtoCadastro = produtoCadastro;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Produto> getListaProdutosFiltrados() {
        return listaProdutosFiltrados;
    }

    public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
        this.listaProdutosFiltrados = listaProdutosFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public Long salvar(){
        Long id = null;
        try{
            ProdutoDAO pdao = new ProdutoDAO();
            id = pdao.salvar(produtoCadastro);
            
            ViewUtil.addMsgInfo("Produto Cadastrado com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Produto " + ex.getMessage());
        }
        return id;
    }
    
    public void listar(){
        try{
            ProdutoDAO pdao = new ProdutoDAO();
            listaProdutos = pdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Listar o Produto " + ex.getMessage());
        }
    }
    
    public List<Produto> buscarPorFabrica(Fabrica fabrica){
        List<Produto> produtos = null;
        try{
            ProdutoDAO pdao = new ProdutoDAO();
            produtos = pdao.buscarPorFabrica(fabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Listar o Produto " + ex.getMessage());
        }
        return produtos;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                ProdutoDAO pdao = new ProdutoDAO();
                produtoCadastro = pdao.buscarPorCodigo(codigo);
            }else{
                produtoCadastro = new Produto();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Carregar os dados do Produto " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            ProdutoDAO pdao = new ProdutoDAO();
            pdao.editar(produtoCadastro);
            
            ViewUtil.addMsgInfo("Produto editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o Produto! " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            ProdutoDAO pdao = new ProdutoDAO();
            pdao.excluir(produtoCadastro);
            
            ViewUtil.addMsgInfo("Produto excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o Produto! " + ex.getMessage());
        }
    }
}
