/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.EmpresaDAO;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class EmpresaController {
    private Empresa empresaCadastro;
    
    private List<Empresa> listaEmpresas;
    private List<Empresa> listaEmpresasFiltradas;
    
    private Long codigo;

    public Empresa getEmpresaCadastro() {
        return empresaCadastro;
    }

    public void setEmpresaCadastro(Empresa empresaCadastro) {
        this.empresaCadastro = empresaCadastro;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<Empresa> getListaEmpresasFiltradas() {
        return listaEmpresasFiltradas;
    }

    public void setListaEmpresasFiltradas(List<Empresa> listaEmpresasFiltradas) {
        this.listaEmpresasFiltradas = listaEmpresasFiltradas;
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
            EmpresaDAO edao = new EmpresaDAO();
            id = edao.salvar(empresaCadastro);
            
            ViewUtil.addMsgInfo("Empresa Cadastrada com sucesso!");            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Empresa " + ex.getMessage());
        }
        return id;
    }
    
    public Long salvarSemAviso(){
        Long id = null;
        try{
            EmpresaDAO edao = new EmpresaDAO();
            id = edao.salvar(empresaCadastro);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Empresa " + ex.getMessage());
        }
        return id;
    }
    
    
    public void listar(){
        try{
            EmpresaDAO edao = new EmpresaDAO();
            listaEmpresas = edao.listar();
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as empresas " + ex.getMessage());
        }
    }
    
    public List<Empresa> buscarPorNome(String nome){
        List<Empresa> empresas = null;
        try{
            EmpresaDAO edao = new EmpresaDAO();
            empresas = edao.buscarPorNome(nome);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a empresa " + ex.getMessage());
        }
        return empresas;
    }
    
        
    public List<Empresa> buscarPorJogador(Jogador jogador){
        List<Empresa> empresas = null;
        try{
            EmpresaDAO edao = new EmpresaDAO();
            empresas = edao.buscarPorJogador(jogador);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a empresa " + ex.getMessage());
        }
        return empresas;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                EmpresaDAO edao = new EmpresaDAO();
                empresaCadastro = edao.buscarPorCodigo(codigo);
            }else{
                empresaCadastro = new Empresa();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da Empresa " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            EmpresaDAO edao = new EmpresaDAO();
            edao.editar(empresaCadastro);
            
            ViewUtil.addMsgInfo("Empresa Editada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar a Empresa " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            EmpresaDAO edao = new EmpresaDAO();
            edao.excluir(empresaCadastro);
            
            ViewUtil.addMsgInfo("Empresa Excluída com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir a Empresa " + ex.getMessage());
        }
    }
    
}
