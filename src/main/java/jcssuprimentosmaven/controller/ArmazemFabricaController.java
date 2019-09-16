/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.ArmazemFabricaDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;
/**
 * @author Gustavo
 * Criação: 26/07/2018 
 * Última Alteração 26/07/2018
 * @version 1.0
 * obs.: criação do armazém da fábrica
 */
public class ArmazemFabricaController {
    private ArmazemFabrica armazemCadastro;
    
    private List<ArmazemFabrica> listaArmazens;
    private List<ArmazemFabrica> listaArmazensFiltrados;
    
    private Long codigo;

    public ArmazemFabrica getArmazemCadastro() {
        return armazemCadastro;
    }

    public void setArmazemCadastro(ArmazemFabrica armazemCadastro) {
        this.armazemCadastro = armazemCadastro;
    }

    public List<ArmazemFabrica> getListaArmazens() {
        return listaArmazens;
    }

    public void setListaArmazens(List<ArmazemFabrica> listaArmazens) {
        this.listaArmazens = listaArmazens;
    }

    public List<ArmazemFabrica> getListaArmazensFiltrados() {
        return listaArmazensFiltrados;
    }

    public void setListaArmazensFiltrados(List<ArmazemFabrica> listaArmazensFiltrados) {
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
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            adao.salvar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o Armazem " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            listaArmazens = adao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os Armazens " + ex.getMessage());
        }
    }
    
    public List<ArmazemFabrica> buscarPorNome(String nome){
        List<ArmazemFabrica> armazens = null;
        try{
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            armazens = adao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public List<ArmazemFabrica> buscarPorFabrica(Fabrica fabrica){
        List<ArmazemFabrica> armazens = null;
        try{
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            armazens = adao.buscarPorFabrica(fabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public List<ArmazemFabrica> buscarPorFabricaMateriaPrima(Fabrica fabrica, MateriaPrima materia){
        List<ArmazemFabrica> armazens = null;
        try{
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            armazens = adao.buscarPorFabricaMateriaPrima(fabrica,materia);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o Armazem " + ex.getMessage());
        }
        return armazens;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
                armazemCadastro = adao.buscarPorCodigo(codigo);
            }else{
                armazemCadastro = new ArmazemFabrica();
            }
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Armazem " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            adao.editar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o Armazem " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
            adao.excluir(armazemCadastro);
            
            ViewUtil.addMsgInfo("Armazem Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o Armazem " + ex.getMessage());
        }
    }
}
