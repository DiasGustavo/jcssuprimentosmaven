/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.PessoaDAO;
import jcssuprimentosmaven.domain.Pessoa;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class PessoaController {
    private Pessoa pessoaCadastro;
    
    private List<Pessoa> listaPessoas;
    private List<Pessoa> listaPessoasFiltradas;
    
    private Long codigo;
    private String matricula;

    public Pessoa getPessoaCadastro() {
        if(pessoaCadastro == null){
            pessoaCadastro = new Pessoa();
        }
        return pessoaCadastro;
    }

    public void setPessoaCadastro(Pessoa pessoaCadastro) {
        this.pessoaCadastro = pessoaCadastro;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public List<Pessoa> getListaPessoasFiltradas() {
        return listaPessoasFiltradas;
    }

    public void setListaPessoasFiltradas(List<Pessoa> listaPessoasFiltradas) {
        this.listaPessoasFiltradas = listaPessoasFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public void novo(){
        pessoaCadastro = new Pessoa();
    }
    
    public void salvar(){
        try{
            PessoaDAO pdao = new PessoaDAO();
            pdao.salvar(pessoaCadastro);
            
            ViewUtil.addMsgInfo("Pessoa Cadastrada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Pessoa " + ex.getMessage() );
        }
    }
    
    public void listar(){
        try{
            PessoaDAO pdao = new PessoaDAO();
            listaPessoas = pdao.listar();
        }catch(RuntimeException ex){
             ViewUtil.addMsgErro("Ocorreu um erro ao Listar as Pessoas " + ex.getMessage());
        }
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                PessoaDAO pdao = new PessoaDAO();
                pessoaCadastro = pdao.buscarPorCodigo(codigo);
            }else{
                pessoaCadastro = new Pessoa();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os dados da Pessoa " + ex.getMessage());
        }
    }
    
    public void buscarPorMatricula(){
        try{
            if(matricula != null){
                PessoaDAO pdao = new PessoaDAO();
                listaPessoas = pdao.buscarPorMatricula(matricula);
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os dados da pessoa " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            PessoaDAO pdao = new PessoaDAO();
            pdao.editar(pessoaCadastro);
            
            ViewUtil.addMsgInfo("Pessoa Editada com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar a Pessoa " + ex.getMessage() );
        }
    }
    
    public void excluir(){
        try{
            PessoaDAO pdao = new PessoaDAO();
            pdao.excluir(pessoaCadastro);
            
            ViewUtil.addMsgInfo("Pessoa Excluída com Sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir a Pessoa " + ex.getMessage() );
        }
    }
}
