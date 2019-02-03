/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.JogadorController;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaJogador;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaJogador {

    ViewPesquisaJogador viewPesquisaJogador;

    public OuvinteViewPesquisaJogador(ViewPesquisaJogador view) {
        this.viewPesquisaJogador = view;

        viewPesquisaJogador.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosJogadores());
        viewPesquisaJogador.bPesquisarAddActionListener(new OuvintePesquisarPorNomeJogadores());
        viewPesquisaJogador.bExcluirAddActionListener(new OuvinteExcluirJogador());
    }

    class OuvintePesquisarTodosJogadores implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JogadorController jogadorController = new JogadorController();
                jogadorController.listar();
                List jogadores = jogadorController.getListaJogadores();
                viewPesquisaJogador.listar(jogadores);

            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Jogadores para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarPorNomeJogadores implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JogadorController jogadorController = new JogadorController();
                String nome = viewPesquisaJogador.getNomePesquisar();
                List jogadores;
                jogadores = jogadorController.buscarPorNome(nome);
                viewPesquisaJogador.listar(jogadores);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar os jogadores " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirJogador implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Jogador jogador = viewPesquisaJogador.getJogador();

                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Juiz abaixo: ");
                mensagem.append("\ncodigo: " + jogador.getId());
                mensagem.append("\nnome: " + jogador.getNome());
                int resposta = viewPesquisaJogador.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    JogadorController jogadorController = new JogadorController();
                    jogadorController.setJogadorCadastro(jogador);
                    jogadorController.excluir();
                    jogadorController.listar();
                    List jogadores = jogadorController.getListaJogadores();
                    viewPesquisaJogador.listar(jogadores);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao exlcuir o jogador");
            }

        }

    }
}
