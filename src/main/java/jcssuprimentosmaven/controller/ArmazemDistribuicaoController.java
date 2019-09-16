/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.ArmazemDistribuicaoDAO;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class ArmazemDistribuicaoController {
    private ArmazemDistribuicao armazemCadastro;
    
    private List<ArmazemDistribuicao> listaArmazens;
    private List<ArmazemDistribuicao> listaArmazensFiltrados;
    
    private Long codigo;

    public ArmazemDistribuicao getArmazemCadastro() {
        return armazemCadastro;
    }

    public void setArmazemCadastro(ArmazemDistribuicao armazemCadastro) {
        this.armazemCadastro = armazemCadastro;
    }

    public List<ArmazemDistribuicao> getListaArmazens() {
        return listaArmazens;
    }

    public void setListaArmazens(List<ArmazemDistribuicao> listaArmazens) {
        this.listaArmazens = listaArmazens;
    }

    public List<ArmazemDistribuicao> getListaArmazensFiltrados() {
        return listaArmazensFiltrados;
    }

    public void setListaArmazensFiltrados(List<ArmazemDistribuicao> listaArmazensFiltrados) {
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
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            adao.salvar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Armazem " + ex.getMessage());
        }
    }
    
    public void salvarSemAviso(){
        try{
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            adao.salvar(armazemCadastro);
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Armazem " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            listaArmazens = adao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os Armazens " + ex.getMessage());
        }
    }
    
    public List<ArmazemDistribuicao> buscarPorNome(String nome){
        List<ArmazemDistribuicao> armazens = null;
        try{
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            armazens = adao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public List<ArmazemDistribuicao> buscarPorFabrica(Fabrica fabrica){
        List<ArmazemDistribuicao> armazens = null;
        try{
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            armazens = adao.buscarPorFabrica(fabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
                armazemCadastro = adao.buscarPorCodigo(codigo);
            }else{
                armazemCadastro = new ArmazemDistribuicao();
            }
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Armazem " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            adao.editar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o Armazem " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            adao.excluir(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o Armazem " + ex.getMessage());
        }
    }
}
