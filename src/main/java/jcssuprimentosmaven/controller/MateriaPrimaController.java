/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.MateriaPrimaDAO;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class MateriaPrimaController {
    
    private MateriaPrima materiaPrimaCadastro;
    
    private List<MateriaPrima> listaMateriaPrimas;
    private List<MateriaPrima> listaMateriaPrimasFiltradas;
    
    private Long codigo;

    public MateriaPrima getMateriaPrimaCadastro() {
        return materiaPrimaCadastro;
    }

    public void setMateriaPrimaCadastro(MateriaPrima materiaPrimaCadastro) {
        this.materiaPrimaCadastro = materiaPrimaCadastro;
    }

    public List<MateriaPrima> getListaMateriaPrimas() {
        return listaMateriaPrimas;
    }

    public void setListaMateriaPrimas(List<MateriaPrima> listaMateriaPrimas) {
        this.listaMateriaPrimas = listaMateriaPrimas;
    }

    public List<MateriaPrima> getListaMateriaPrimasFiltradas() {
        return listaMateriaPrimasFiltradas;
    }

    public void setListaMateriaPrimasFiltradas(List<MateriaPrima> listaMateriaPrimasFiltradas) {
        this.listaMateriaPrimasFiltradas = listaMateriaPrimasFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            mdao.salvar(materiaPrimaCadastro);
            
            ViewUtil.addMsgInfo("Materia Prima Cadastrada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao cadastrar a Materia Prima " + ex.getMessage());
        }
    }
    
    public void salvarSemAviso(){
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            mdao.salvar(materiaPrimaCadastro);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao cadastrar a Materia Prima " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            listaMateriaPrimas = mdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as Materias Prima " + ex.getMessage());
        }
    }
    
    public List<MateriaPrima> buscarPorNome(String nome){
        List<MateriaPrima> materiasPrima = null;
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            materiasPrima = mdao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Materia Prima " + ex.getMessage());
        }
        return materiasPrima;
    }
    
    public List<MateriaPrima> buscarPorStatus(String status){
        List<MateriaPrima> materiasPrima = null;
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            materiasPrima = mdao.buscarPorStatus(status);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Materia Prima " + ex.getMessage());
        }
        return materiasPrima;
    }
    
    /*public List<MateriaPrima> buscarPorFornecedor(Fornecedor fornecedor){
        List<MateriaPrima> materiasPrima = null;
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            materiasPrima = mdao.buscarPorFornecedor(fornecedor);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Materia Prima " + ex.getMessage());
        }
        return materiasPrima;
    }*/
    
    public void carregarDados(){
        try{
            if(codigo != null){
                MateriaPrimaDAO mdao = new MateriaPrimaDAO();
                materiaPrimaCadastro = mdao.buscarPorCodigo(codigo);
            }else{
                materiaPrimaCadastro = new MateriaPrima();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da MateriaPrimada " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            mdao.editar(materiaPrimaCadastro);
            
            ViewUtil.addMsgInfo("Materia Prima Editada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao editar a Materia Prima " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            MateriaPrimaDAO mdao = new MateriaPrimaDAO();
            mdao.excluir(materiaPrimaCadastro);
            
            ViewUtil.addMsgInfo("Materia Prima Excluída com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Erro ao excluir a Materia Prima " + ex.getMessage());
        }
    }
}
