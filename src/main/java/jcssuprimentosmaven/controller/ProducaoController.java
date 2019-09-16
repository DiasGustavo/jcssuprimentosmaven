/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.ProducaoDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Producao;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ProducaoController {
    private Producao producaoCadastro;
    
    private List<Producao> listaProducoes;
    private List<Producao> listaProducoesFiltradas;
    
    private Long codigo;

    public Producao getProducaoCadastro() {
        return producaoCadastro;
    }

    public void setProducaoCadastro(Producao producaoCadastro) {
        this.producaoCadastro = producaoCadastro;
    }

    public List<Producao> getListaProducoes() {
        return listaProducoes;
    }

    public void setListaProducoes(List<Producao> listaProducoes) {
        this.listaProducoes = listaProducoes;
    }

    public List<Producao> getListaProducoesFiltradas() {
        return listaProducoesFiltradas;
    }

    public void setListaProducoesFiltradas(List<Producao> listaProducoesFiltradas) {
        this.listaProducoesFiltradas = listaProducoesFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            ProducaoDAO pdao = new ProducaoDAO();
            pdao.salvar(producaoCadastro);
            
            ViewUtil.addMsgInfo("Produção Cadastrada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao cadastrar a produção " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            ProducaoDAO pdao = new ProducaoDAO();
            listaProducoes = pdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as produções "+ ex.getMessage());
        }
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                ProducaoDAO pdao = new ProducaoDAO();
                producaoCadastro = pdao.buscarPorCodigo(codigo);
            }else{
                producaoCadastro = new Producao();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da Producao " + ex.getMessage());
        }
    }
    
    /*public List<Producao> buscarPorMateria(String nome){
        List<Producao> materiasPrima = null;
        try{
            ProducaoDAO mdao = new ProducaoDAO();
            materiasPrima = mdao.buscarPorMateria(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Produção " + ex.getMessage());
        }
        return materiasPrima;
    }*/
    
    public List<Producao> buscarPorFabrica(Fabrica fabrica){
        List<Producao> materiasPrima = null;
        try{
            ProducaoDAO mdao = new ProducaoDAO();
            materiasPrima = mdao.buscarPorFabrica(fabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Produção " + ex.getMessage());
        }
        return materiasPrima;
    }
    
    public Producao buscarPorArmazem(ArmazemFabrica armazem){
        Producao producao = null;
        try{
            ProducaoDAO mdao = new ProducaoDAO();
            producao = mdao.buscarPorArmazem(armazem);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Produção " + ex.getMessage());
        }
        return producao;
    }
    
    public void editar(){
        try{
            ProducaoDAO pdao = new ProducaoDAO();
            pdao.editar(producaoCadastro);
            
            ViewUtil.addMsgInfo("Produção Editada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar a produção " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            ProducaoDAO pdao = new ProducaoDAO();
            pdao.excluir(producaoCadastro);
            
            ViewUtil.addMsgInfo("Produção Excluída com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir a produção " + ex.getMessage());
        }
    }
}
