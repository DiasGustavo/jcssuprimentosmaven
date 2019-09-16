/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.ArmazemSuprimentoDAO;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class ArmazemSuprimentoController {
    private ArmazemSuprimento armazemCadastro;
    
    private List<ArmazemSuprimento> listaArmazens;
    private List<ArmazemSuprimento> listaArmazensFiltrados;
    
    private Long codigo;

    public ArmazemSuprimento getArmazemCadastro() {
        return armazemCadastro;
    }

    public void setArmazemCadastro(ArmazemSuprimento armazemCadastro) {
        this.armazemCadastro = armazemCadastro;
    }

    public List<ArmazemSuprimento> getListaArmazens() {
        return listaArmazens;
    }

    public void setListaArmazens(List<ArmazemSuprimento> listaArmazens) {
        this.listaArmazens = listaArmazens;
    }

    public List<ArmazemSuprimento> getListaArmazensFiltrados() {
        return listaArmazensFiltrados;
    }

    public void setListaArmazensFiltrados(List<ArmazemSuprimento> listaArmazensFiltrados) {
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
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            adao.salvar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Armazem " + ex.getMessage());
        }
    }
    
    public void salvarSemAviso(){
        try{
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            adao.salvar(armazemCadastro);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Armazem " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            listaArmazens = adao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os Armazens " + ex.getMessage());
        }
    }
    
    public List<ArmazemSuprimento> buscarPorNome(String nome){
        List<ArmazemSuprimento> armazens = null;
        try{
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            armazens = adao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public List<ArmazemSuprimento> buscarPorFabrica(Fabrica fabrica){
        List<ArmazemSuprimento> armazens = null;
        try{
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            armazens = adao.buscarPorFabrica(fabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
                armazemCadastro = adao.buscarPorCodigo(codigo);
            }else{
                armazemCadastro = new ArmazemSuprimento();
            }
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Armazem " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            adao.editar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o Armazem " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            ArmazemSuprimentoDAO adao = new ArmazemSuprimentoDAO();
            adao.excluir(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o Armazem " + ex.getMessage());
        }
    }
}
