/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.FornecedorDAO;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class FornecedorController {
    
    private Fornecedor fornecedorCadastro;
    
    private List<Fornecedor> listaFornecedores;
    private List<Fornecedor> listaFornecedoresFiltrados;
    
    private Long codigo;

    public Fornecedor getFornecedorCadastro() {
        return fornecedorCadastro;
    }

    public void setFornecedorCadastro(Fornecedor fornecedorCadastro) {
        this.fornecedorCadastro = fornecedorCadastro;
    }

    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public List<Fornecedor> getListaFornecedoresFiltrados() {
        return listaFornecedoresFiltrados;
    }

    public void setListaFornecedoresFiltrados(List<Fornecedor> listaFornecedoresFiltrados) {
        this.listaFornecedoresFiltrados = listaFornecedoresFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.salvar(fornecedorCadastro);
            
            ViewUtil.addMsgInfo("Fornecdor Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao salvar o Fornecedor " + ex.getMessage());
        }
    }
    
    public void salvarSemAviso(){
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.salvar(fornecedorCadastro);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao salvar o Fornecedor " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            listaFornecedores = fdao.listar();
                    
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os fornecedores " + ex.getMessage());
        }
    }
    
    public List<Fornecedor> buscarPorNome(String nome){
        List<Fornecedor> fornecedores = null;
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            fornecedores = fdao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o fornecedor " + ex.getMessage());
        }
        return fornecedores;
    }
    
    public List<Fornecedor> buscarPorEmpresa(Empresa empresa){
        List<Fornecedor> fornecedores = null;
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            fornecedores = fdao.buscarPorEmpresa(empresa);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o fornecedor " + ex.getMessage());
        }
        return fornecedores;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                FornecedorDAO fdao = new FornecedorDAO();
                fornecedorCadastro = fdao.buscarPorCodigo(codigo);
            }else{
                fornecedorCadastro = new Fornecedor();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Fornecedor " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.editar(fornecedorCadastro);
            
            ViewUtil.addMsgInfo("Fornecdor Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o Fornecedor " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.excluir(fornecedorCadastro);
            
            ViewUtil.addMsgInfo("Fornecdor Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o Fornecedor " + ex.getMessage());
        }
    }
}
