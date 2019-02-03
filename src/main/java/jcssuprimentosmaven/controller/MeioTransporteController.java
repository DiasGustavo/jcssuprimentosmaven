/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.MeioTransporteDAO;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class MeioTransporteController {
    private MeioTransporte meioTransporteCadastro;
    
    private List<MeioTransporte> listaMeiosTransportes;
    private List<MeioTransporte> listaMeiosTransportesFiltrados;
    
    private Long codigo;

    public MeioTransporte getMeioTransporteCadastro() {
        return meioTransporteCadastro;
    }

    public void setMeioTransporteCadastro(MeioTransporte meioTransporteCadastro) {
        this.meioTransporteCadastro = meioTransporteCadastro;
    }

    public List<MeioTransporte> getListaMeiosTransportes() {
        return listaMeiosTransportes;
    }

    public void setListaMeiosTransportes(List<MeioTransporte> listaMeiosTransportes) {
        this.listaMeiosTransportes = listaMeiosTransportes;
    }

    public List<MeioTransporte> getListaMeiosTransportesFiltrados() {
        return listaMeiosTransportesFiltrados;
    }

    public void setListaMeiosTransportesFiltrados(List<MeioTransporte> listaMeiosTransportesFiltrados) {
        this.listaMeiosTransportesFiltrados = listaMeiosTransportesFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            mtdao.salvar(meioTransporteCadastro);
            
            ViewUtil.addMsgInfo("Meio Transporte Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao cadastrar o Meio de Transporte " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            listaMeiosTransportes = mtdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os Meios de Transporte " + ex.getMessage());
        }
    }
    
    public List<MeioTransporte> buscarPorNome (String nome){
        List<MeioTransporte> meios = null;
        try{
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            meios = mtdao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Meio de Transporte " + ex.getMessage());
        }
        return meios;
    }
    
    public List<MeioTransporte> buscarPorDestino (String destino){
        List<MeioTransporte> meios = null;
        try{
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            meios = mtdao.buscarPorDestino(destino);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Meio de Transporte " + ex.getMessage());
        }
        return meios;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                MeioTransporteDAO mtdao = new MeioTransporteDAO();
                meioTransporteCadastro = mtdao.buscarPorCodigo(codigo);
            }else{
                meioTransporteCadastro = new MeioTransporte();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Meio de Transporte " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            mtdao.editar(meioTransporteCadastro);
            
            ViewUtil.addMsgInfo("Meio Transporte Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao editar o Meio de Transporte " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            MeioTransporteDAO mtdao = new MeioTransporteDAO();
            mtdao.excluir(meioTransporteCadastro);
            
            ViewUtil.addMsgInfo("Meio Transporte Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao excluir o Meio de Transporte " + ex.getMessage());
        }
    }
}
