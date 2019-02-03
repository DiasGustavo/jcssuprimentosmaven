/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.ArmazemDAO;
import jcssuprimentosmaven.domain.Armazem;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class ArmazemController {
    private Armazem armazemCadastro;
    
    private List<Armazem> listaArmazens;
    private List<Armazem> listaArmazensFiltrados;
    
    private Long codigo;

    public Armazem getArmazemCadastro() {
        return armazemCadastro;
    }

    public void setArmazemCadastro(Armazem armazemCadastro) {
        this.armazemCadastro = armazemCadastro;
    }

    public List<Armazem> getListaArmazens() {
        return listaArmazens;
    }

    public void setListaArmazens(List<Armazem> listaArmazens) {
        this.listaArmazens = listaArmazens;
    }

    public List<Armazem> getListaArmazensFiltrados() {
        return listaArmazensFiltrados;
    }

    public void setListaArmazensFiltrados(List<Armazem> listaArmazensFiltrados) {
        this.listaArmazensFiltrados = listaArmazensFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            ArmazemDAO adao = new ArmazemDAO();
            adao.salvar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Armazem " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            ArmazemDAO adao = new ArmazemDAO();
            listaArmazens = adao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os Armazens " + ex.getMessage());
        }
    }
    
    public List<Armazem> buscarPorNome(String nome){
        List<Armazem> armazens = null;
        try{
            ArmazemDAO adao = new ArmazemDAO();
            armazens = adao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                ArmazemDAO adao = new ArmazemDAO();
                armazemCadastro = adao.buscarPorCodigo(codigo);
            }else{
                armazemCadastro = new Armazem();
            }
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Armazem " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            ArmazemDAO adao = new ArmazemDAO();
            adao.editar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o Armazem " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            ArmazemDAO adao = new ArmazemDAO();
            adao.excluir(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o Armazem " + ex.getMessage());
        }
    }
}
