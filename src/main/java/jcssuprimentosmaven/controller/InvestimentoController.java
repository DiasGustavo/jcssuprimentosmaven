/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.InvestimentoDAO;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Investimento;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class InvestimentoController {
    private Investimento investimentoCadastro;
    private List<Investimento> listaInvestimentos;
    private List<Investimento> listaInvestimentosFiltrados;
    
    private Long codigo;

    public Investimento getInvestimentoCadastro() {
        return investimentoCadastro;
    }

    public void setInvestimentoCadastro(Investimento investimentoCadastro) {
        this.investimentoCadastro = investimentoCadastro;
    }

    public List<Investimento> getListaInvestimentos() {
        return listaInvestimentos;
    }

    public void setListaInvestimentos(List<Investimento> listaInvestimentos) {
        this.listaInvestimentos = listaInvestimentos;
    }

    public List<Investimento> getListaInvestimentosFiltrados() {
        return listaInvestimentosFiltrados;
    }

    public void setListaInvestimentosFiltrados(List<Investimento> listaInvestimentosFiltrados) {
        this.listaInvestimentosFiltrados = listaInvestimentosFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            InvestimentoDAO idao = new InvestimentoDAO();
            idao.salvar(investimentoCadastro);
            
            ViewUtil.addMsgInfo("Investimento Cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar o investimento " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            InvestimentoDAO idao = new InvestimentoDAO();
            listaInvestimentos = idao.listar();
            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os investimentos " + ex.getMessage());
        }
    }
    
    public List<Investimento> buscarPorNome(String nome){
        List<Investimento> investimentos = null;
        try{
            InvestimentoDAO idao = new InvestimentoDAO();
            investimentos = idao.buscarPorNome(nome);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o investimento " + ex.getMessage());
        }
        return investimentos;
    }
    
    public List<Investimento> buscarPorFabrica(Fabrica fabrica){
        List<Investimento> investimentos = null;
        try{
            InvestimentoDAO idao = new InvestimentoDAO();
            investimentos = idao.buscarPorFabrica(fabrica);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o investimento " + ex.getMessage());
        }
        return investimentos;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                InvestimentoDAO idao = new InvestimentoDAO();
                investimentoCadastro = idao.buscarPorCodigo(codigo);
            }else{
                investimentoCadastro = new Investimento();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Investimento " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            InvestimentoDAO idao = new InvestimentoDAO();
            idao.editar(investimentoCadastro);
            
            ViewUtil.addMsgInfo("Investimento Editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar o investimento " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            InvestimentoDAO idao = new InvestimentoDAO();
            idao.excluir(investimentoCadastro);
            
            ViewUtil.addMsgInfo("Investimento Excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir o investimento " + ex.getMessage());
        }
    }
   
}
