/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.MensagemDAO;
import jcssuprimentosmaven.domain.Mensagem;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class MensagemController {
    private Mensagem mensagemCadastro;
    
    private List<Mensagem> listaMensagens;
    private List<Mensagem> listaMensagensFiltradas;
    
    private Long codigo;

    public Mensagem getMensagemCadastro() {
        return mensagemCadastro;
    }

    public void setMensagemCadastro(Mensagem mensagemCadastro) {
        this.mensagemCadastro = mensagemCadastro;
    }

    public List<Mensagem> getListaMensagens() {
        return listaMensagens;
    }

    public void setListaMensagens(List<Mensagem> listaMensagens) {
        this.listaMensagens = listaMensagens;
    }

    public List<Mensagem> getListaMensagensFiltradas() {
        return listaMensagensFiltradas;
    }

    public void setListaMensagensFiltradas(List<Mensagem> listaMensagensFiltradas) {
        this.listaMensagensFiltradas = listaMensagensFiltradas;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            MensagemDAO mdao = new MensagemDAO();
            mdao.salvar(mensagemCadastro);
            
            ViewUtil.addMsgInfo("Mensagem Cadastrada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Cadastrar a Mensagem " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            MensagemDAO mdao = new MensagemDAO();
            listaMensagens = mdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar as mensagens " + ex.getMessage());
        }
    }
    
    public List<Mensagem> buscarPorDestinatario (String nomeInformado){
        List<Mensagem> mensagens = null;
        try{
            MensagemDAO mdao = new MensagemDAO();
            mensagens = mdao.buscarPorDestinatario(nomeInformado);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar as mensagens " + ex.getMessage());
        }
        return mensagens;
    }
    
    public List<Mensagem> buscarPorRemetente (String nomeInformado){
        List<Mensagem> mensagens = null;
        try{
            MensagemDAO mdao = new MensagemDAO();
            mensagens = mdao.buscarPorRemetente(nomeInformado);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar as mensagens " + ex.getMessage());
        }
        return mensagens;
    }
    
    public void carregarDados(){
        try{
            if(codigo != null){
                MensagemDAO mdao = new MensagemDAO();
                mensagemCadastro = mdao.buscarPorCodigo(codigo);
            }else{
                mensagemCadastro = new Mensagem();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados da Mensagem " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            MensagemDAO mdao = new MensagemDAO();
            mdao.editar(mensagemCadastro);
            
            ViewUtil.addMsgInfo("Mensagem Editada com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Editar a Mensagem " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            MensagemDAO mdao = new MensagemDAO();
            mdao.salvar(mensagemCadastro);
            
            ViewUtil.addMsgInfo("Mensagem Excluída com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao Excluir a Mensagem " + ex.getMessage());
        }
    }
}
