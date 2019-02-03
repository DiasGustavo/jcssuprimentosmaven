/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.constantes.StatusEnum;
import jcssuprimentosmaven.dao.JogadorDAO;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class JogadorController {

    private Jogador jogadorCadastro;

    private List<Jogador> listaJogadores;
    private List<Jogador> listaJogadoresFiltrados;

    private Long codigo;

    public Jogador getJogadorCadastro() {
        return jogadorCadastro;
    }

    public void setJogadorCadastro(Jogador jogadorCadastro) {
        this.jogadorCadastro = jogadorCadastro;
    }

    public List<Jogador> getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(List<Jogador> listaJogadores) {
        this.listaJogadores = listaJogadores;
    }

    public List<Jogador> getListaJogadoresFiltrados() {
        return listaJogadoresFiltrados;
    }

    public void setListaJogadoresFiltrados(List<Jogador> listaJogadoresFiltrados) {
        this.listaJogadoresFiltrados = listaJogadoresFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public StatusEnum[] getStatus() {
        return StatusEnum.values();
    }

    public void salvar() {
        try {
            JogadorDAO jdao = new JogadorDAO();
            jdao.salvar(jogadorCadastro);

            ViewUtil.addMsgInfo("Jogador Cadastrado com sucesso!");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao salvar o Jogador " + ex.getMessage());
        }
    }

    public Jogador autenticarUsuario() {
        Jogador autenticado = null;
        try {
            JogadorDAO jdao = new JogadorDAO();
            Jogador jogadorAutenticado = jdao.autenticarUsuario(jogadorCadastro.getLogin(), jogadorCadastro.getSenha(), jogadorCadastro.getFuncao());
            if (jogadorAutenticado != null) {
                ViewUtil.addMsgInfo("Jogador Autenticado com sucesso!");
                autenticado = jogadorAutenticado;
            }else{
                ViewUtil.addMsgInfo("Jogador não preenchido!");
            }
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao autenticar o Jogador " + ex.getMessage());
        }
        return autenticado;
    }

    public void listar() {
        try {
            JogadorDAO jdao = new JogadorDAO();
            listaJogadores = jdao.listar();
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os jogadores " + ex.getMessage());
        }
    }

    public List<Jogador> buscarPorNome(String nome) {
        List<Jogador> jogadores = null;
        try {
            JogadorDAO jdao = new JogadorDAO();
            jogadores = jdao.buscarPorNome(nome);
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o jogador " + ex.getMessage());
        }
        return jogadores;
    }

    public void carregarDados() {
        try {
            if (codigo != null) {
                JogadorDAO jdao = new JogadorDAO();
                jogadorCadastro = jdao.buscarPorCodigo(codigo);
            } else {
                jogadorCadastro = new Jogador();
            }
        } catch (RuntimeException ex) {
            ViewUtil.addMsgInfo("Ocorreu ao carregar os dados do Jogador " + ex.getMessage());
        }
    }

    public void editar() {
        try {
            JogadorDAO jdao = new JogadorDAO();
            jdao.editar(jogadorCadastro);

            ViewUtil.addMsgInfo("Jogador editado com sucesso!");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o Jogador " + ex.getMessage());
        }
    }

    public void excluir() {
        try {
            JogadorDAO jdao = new JogadorDAO();
            jdao.excluir(jogadorCadastro);

            ViewUtil.addMsgInfo("Jogador excluído com sucesso!");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o Jogador " + ex.getMessage());
        }
    }
}
