/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.SolicitacaoDAO;
<<<<<<< HEAD
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class SolicitacaoController {
    private Solicitacao solicitacaoCadastro;
    
    private List<Solicitacao> listaSolicitacoes;
    private List<Solicitacao> listaSolicitacoesFiltradas;
    
    private Long codigo;

    public Solicitacao getSolicitacaoCadastro() {
        return solicitacaoCadastro;
    }

    public void setSolicitacaoCadastro(Solicitacao solicitacaoCadastro) {
        this.solicitacaoCadastro = solicitacaoCadastro;
    }

    public List<Solicitacao> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public List<Solicitacao> getListaSolicitacoesFiltradas() {
        return listaSolicitacoesFiltradas;
    }

    public void setListaSolicitacoesFiltradas(List<Solicitacao> listaSolicitacoesFiltradas) {
        this.listaSolicitacoesFiltradas = listaSolicitacoesFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            SolicitacaoDAO sdao = new SolicitacaoDAO();
            sdao.salvar(solicitacaoCadastro);
            
            ViewUtil.addMsgInfo("A Solicitação Cadastrada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Solicitação! " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            SolicitacaoDAO sdao = new SolicitacaoDAO();
            listaSolicitacoes = sdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Listar as Solicitações " + ex.getMessage());
        }
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                SolicitacaoDAO sdao = new SolicitacaoDAO();
                solicitacaoCadastro = sdao.buscarPorCodigo(codigo);
            }else{
                solicitacaoCadastro = new Solicitacao();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Carregar os dados do Produto " + ex.getMessage());
        }
    }
    
<<<<<<< HEAD
    public List<Solicitacao> buscarPorArmazemStatus(ArmazemDistribuicao armazem, String status){
        List<Solicitacao> solicitacoes = null;
        try{
            SolicitacaoDAO sdao = new SolicitacaoDAO();
            solicitacoes = sdao.buscarPorArmazemStatus(armazem, status);            
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar as solicitações " + ex.getMessage());
        }
        return solicitacoes;
    }
    
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    public void editar(){
        try{
            SolicitacaoDAO sdao = new SolicitacaoDAO();
            sdao.editar(solicitacaoCadastro);
            
            ViewUtil.addMsgInfo("A Solicitação Editada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar a Solicitação! " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            SolicitacaoDAO sdao = new SolicitacaoDAO();
            sdao.excluir(solicitacaoCadastro);
            
            ViewUtil.addMsgInfo("A Solicitação Excluída com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir a Solicitação! " + ex.getMessage());
        }
    }
}
