/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.TransportadoraDAO;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class TransportadoraController {
 
    private Transportadora transportadoraCadastro;
    
    private List<Transportadora> listaTransportadoras;
    private List<Transportadora> listaTransportadorasFiltradas;
    
    private Long codigo;

    public Transportadora getTransportadoraCadastro() {
        return transportadoraCadastro;
    }

    public void setTransportadoraCadastro(Transportadora transportadoraCadastro) {
        this.transportadoraCadastro = transportadoraCadastro;
    }

    public List<Transportadora> getListaTransportadoras() {
        return listaTransportadoras;
    }

    public void setListaTransportadoras(List<Transportadora> listaTransportadoras) {
        this.listaTransportadoras = listaTransportadoras;
    }

    public List<Transportadora> getListaTransportadorasFiltradas() {
        return listaTransportadorasFiltradas;
    }

    public void setListaTransportadorasFiltradas(List<Transportadora> listaTransportadorasFiltradas) {
        this.listaTransportadorasFiltradas = listaTransportadorasFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            TransportadoraDAO tdao = new TransportadoraDAO();
            tdao.salvar(transportadoraCadastro);
            
            ViewUtil.addMsgInfo("Transportadora Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao salvar o Transportadora " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            TransportadoraDAO tdao = new TransportadoraDAO();
            listaTransportadoras = tdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as Transportadoras " + ex.getMessage());
        }
    }
    
    public List<Transportadora> buscarPorNome (String nome){
        List<Transportadora> transportadoras = null;
        try{
            TransportadoraDAO tdao = new TransportadoraDAO();
            transportadoras = tdao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Transportadora " + ex.getMessage());
        }
        return transportadoras;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                TransportadoraDAO tdao = new TransportadoraDAO();
                transportadoraCadastro = tdao.buscarPorCodigo(codigo);
            }else{
                transportadoraCadastro = new Transportadora();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da Transportadora " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            TransportadoraDAO tdao = new TransportadoraDAO();
            tdao.editar(transportadoraCadastro);
            
            ViewUtil.addMsgInfo("Transportadora Editada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o Transportadora " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            TransportadoraDAO tdao = new TransportadoraDAO();
            tdao.excluir(transportadoraCadastro);
            
            ViewUtil.addMsgInfo("Transportadora Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o Transportadora " + ex.getMessage());
        }
    }
}
