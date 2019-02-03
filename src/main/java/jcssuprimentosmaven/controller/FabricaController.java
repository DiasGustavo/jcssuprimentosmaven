/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class FabricaController {
    private Fabrica armazemCadastro;
    
    private List<Fabrica> listaArmazens;
    private List<Fabrica> listaArmazensFiltrados;
    
    private Long codigo;

    public Fabrica getArmazemCadastro() {
        return armazemCadastro;
    }

    public void setArmazemCadastro(Fabrica armazemCadastro) {
        this.armazemCadastro = armazemCadastro;
    }

    public List<Fabrica> getListaArmazens() {
        return listaArmazens;
    }

    public void setListaArmazens(List<Fabrica> listaArmazens) {
        this.listaArmazens = listaArmazens;
    }

    public List<Fabrica> getListaArmazensFiltrados() {
        return listaArmazensFiltrados;
    }

    public void setListaArmazensFiltrados(List<Fabrica> listaArmazensFiltrados) {
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
            FabricaDAO adao = new FabricaDAO();
            adao.salvar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Fábrica Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Fábrica " + ex.getMessage());
        }
    }
    
    public void salvarSemAviso(){
        try{
            FabricaDAO adao = new FabricaDAO();
            adao.salvar(armazemCadastro);          
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Fábrica " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            FabricaDAO adao = new FabricaDAO();
            listaArmazens = adao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as Fábricas " + ex.getMessage());
        }
    }
    
    public List<Fabrica> buscarPorNome(String nome){
        List<Fabrica> armazens = null;
        try{
            FabricaDAO adao = new FabricaDAO();
            armazens = adao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Fábrica " + ex.getMessage());
        }
        return armazens;
    }
    
    public List<Fabrica> buscarPorEmpresa(Empresa empresa){
        List<Fabrica> armazens = null;
        try{
            FabricaDAO adao = new FabricaDAO();
            armazens = adao.buscarPorEmpresa(empresa);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar a Fábrica " + ex.getMessage());
        }
        return armazens;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                FabricaDAO adao = new FabricaDAO();
                armazemCadastro = adao.buscarPorCodigo(codigo);
            }else{
                armazemCadastro = new Fabrica();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da Fábrica " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            FabricaDAO adao = new FabricaDAO();
            adao.editar(armazemCadastro);
            
            ViewUtil.addMsgInfo("Fábrica Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar a Fábrica " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            FabricaDAO adao = new FabricaDAO();
            adao.excluir(armazemCadastro);
            
            ViewUtil.addMsgInfo("Fábrica Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir a Fábrica " + ex.getMessage());
        }
    }
    
}
