/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.RodadaDAO;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class RodadaController {
    private Rodada rodadaCadastro;
    
    private List<Rodada> listaRodadas;
    private List<Rodada> listaRodadasFiltradas;
    
    private Long codigo;

    public Rodada getRodadaCadastro() {
        return rodadaCadastro;
    }

    public void setRodadaCadastro(Rodada rodadaCadastro) {
        this.rodadaCadastro = rodadaCadastro;
    }

    public List<Rodada> getListaRodadas() {
        return listaRodadas;
    }

    public void setListaRodadas(List<Rodada> listaRodadas) {
        this.listaRodadas = listaRodadas;
    }

    public List<Rodada> getListaRodadasFiltradas() {
        return listaRodadasFiltradas;
    }

    public void setListaRodadasFiltradas(List<Rodada> listaRodadasFiltradas) {
        this.listaRodadasFiltradas = listaRodadasFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            
            RodadaDAO rdao = new RodadaDAO();
            rdao.salvar(rodadaCadastro);
            
            ViewUtil.addMsgInfo("Rodada Cadastrada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao salvar a Rodada " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            RodadaDAO rdao = new RodadaDAO();
            listaRodadas = rdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as rodadas " + ex.getMessage());
        }
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                RodadaDAO rdao = new RodadaDAO();
                rodadaCadastro = rdao.buscarPorCodigo(codigo);
            }else{
                rodadaCadastro = new Rodada();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da rodada " + ex.getMessage());
        }
    }
    
     public void editar(){
        try{
            
            RodadaDAO rdao = new RodadaDAO();
            rdao.editar(rodadaCadastro);
            
            ViewUtil.addMsgInfo("Rodada editada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar a Rodada " + ex.getMessage());
        }
    }
     
     public void excluir(){
        try{
            
            RodadaDAO rdao = new RodadaDAO();
            rdao.excluir(rodadaCadastro);
            
            ViewUtil.addMsgInfo("Rodada Cadastrada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao salvar a Rodada " + ex.getMessage());
        }
    }
}
