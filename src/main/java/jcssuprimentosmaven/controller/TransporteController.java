/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.TransporteDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Transporte;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class TransporteController {
    private Transporte transporteCadastro;
    
    private List<Transporte> listaTransportes;
    private List<Transporte> listaTransportesFiltrados;
    
    private Long codigo;

    public Transporte getTransporteCadastro() {
        return transporteCadastro;
    }

    public void setTransporteCadastro(Transporte transporteCadastro) {
        this.transporteCadastro = transporteCadastro;
    }

    public List<Transporte> getListaTransportes() {
        return listaTransportes;
    }

    public void setListaTransportes(List<Transporte> listaTransportes) {
        this.listaTransportes = listaTransportes;
    }

    public List<Transporte> getListaTransportesFiltrados() {
        return listaTransportesFiltrados;
    }

    public void setListaTransportesFiltrados(List<Transporte> listaTransportesFiltrados) {
        this.listaTransportesFiltrados = listaTransportesFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            TransporteDAO tdao = new TransporteDAO();
            tdao.salvar(transporteCadastro);
            
            ViewUtil.addMsgInfo("Transporte Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao cadastrar o transporte " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            TransporteDAO tdao = new TransporteDAO();
            listaTransportes = tdao.listar();
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os transportes " + ex.getMessage());
        }
    }
    
    public List<Transporte> buscarPorEmpresa(Empresa empresa){
        List<Transporte> listaTransportes = null;
        
        try{
            TransporteDAO tdao = new TransporteDAO();
            listaTransportes = tdao.buscarPorEmpresa(empresa);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o transporte " + ex.getMessage());
        }
        return listaTransportes;
    }
    
    public List<Transporte> buscarPorArmazemFabrica(ArmazemFabrica armazemFabrica){
        List<Transporte> listaTransportes = null;
        
        try{
            TransporteDAO tdao = new TransporteDAO();
            listaTransportes = tdao.buscarPorArmazemFabrica(armazemFabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o transporte " + ex.getMessage());
        }
        return listaTransportes;
    }
    
    public List<Transporte> buscarPorDestino(String destino){
        List<Transporte> listaTransportes = null;
        
        try{
            TransporteDAO tdao = new TransporteDAO();
            listaTransportes = tdao.buscarPorDestino(destino);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o transporte " + ex.getMessage());
        }
        return listaTransportes;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                TransporteDAO tdao = new TransporteDAO();
                transporteCadastro = tdao.buscarPorCodigo(codigo);
            }else{
                transporteCadastro = new Transporte();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Transporte " + ex.getMessage());
        }
    }
    public void editar(){
        try{
            TransporteDAO tdao = new TransporteDAO();
            tdao.editar(transporteCadastro);
            
            ViewUtil.addMsgInfo("Transporte editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o transporte " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            TransporteDAO tdao = new TransporteDAO();
            tdao.excluir(transporteCadastro);
            
            ViewUtil.addMsgInfo("Transporte excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o transporte " + ex.getMessage());
        }
    }
}
