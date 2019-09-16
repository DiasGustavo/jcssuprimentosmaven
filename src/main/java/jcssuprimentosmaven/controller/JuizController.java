/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.constantes.StatusEnum;
import jcssuprimentosmaven.dao.JuizDAO;
import jcssuprimentosmaven.domain.Juiz;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class JuizController {
    
    private Juiz juizCadastro;
    
    private List<Juiz> listaJuizes;
    private List<Juiz> listaJuizesFiltrados;
    
    private Long codigo;

    public Juiz getJuizCadastro() {
        if (juizCadastro == null){
            juizCadastro = new Juiz();
        }
        return juizCadastro;
    }

    public void setJuizCadastro(Juiz juizCadastro) {
        this.juizCadastro = juizCadastro;
    }

    public List<Juiz> getListaJuizes() {
        return listaJuizes;
    }

    public void setListaJuizes(List<Juiz> listaJuizes) {
        this.listaJuizes = listaJuizes;
    }

    public List<Juiz> getListaJuizesFiltrados() {
        return listaJuizesFiltrados;
    }

    public void setListaJuizesFiltrados(List<Juiz> listaJuizesFiltrados) {
        this.listaJuizesFiltrados = listaJuizesFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public StatusEnum[] getStatus(){
        return StatusEnum.values();
    }
    
        
    public void salvar(){
        try{
            JuizDAO jdao = new JuizDAO();
            jdao.salvar(juizCadastro);
            
            ViewUtil.addMsgInfo("Juiz Cadastrado com Sucesso");
        }catch(RuntimeException ex) {
            ViewUtil.addMsgInfo("Ocorreu um erro ao salvar o Juiz " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            JuizDAO jdao = new JuizDAO();
            listaJuizes = jdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu um erro ao listar os Juizes " + ex.getMessage());
        }
    }
    
    public List<Juiz> buscarPorNome(String nome){
        List<Juiz> juizes = null;
        try{
            JuizDAO jdao = new JuizDAO();            
            juizes = jdao.buscarPorNome(nome);            
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Juiz " + ex.getMessage());
        }                
        return juizes;
    }
    
    public void carregarDados(){
        try{
            if (codigo != null){
                JuizDAO jdao = new JuizDAO();
                juizCadastro = jdao.buscarPorCodigo(codigo);
            }else{
                juizCadastro = new Juiz();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Juiz " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            JuizDAO jdao = new JuizDAO();
            jdao.editar(juizCadastro);
            
            ViewUtil.addMsgInfo("Juiz editado com sucesso! ");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro(("Ocorreu um erro ao Editar o Juiz " + ex.getMessage()));
        }
    }
    
    public void excluir(){
        try{
            JuizDAO jdao = new JuizDAO();
            jdao.excluir(juizCadastro);
            
            ViewUtil.addMsgInfo("Juiz excluído com sucesso! ");
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu um erro ao Excluir o Juiz " + ex.getMessage());
        }
    }
    
}
