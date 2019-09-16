/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.JogadorController;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.view.ViewDadosJogador;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosJogador {
    
    ViewDadosJogador viewDadosJogador;
    
    public OuvinteViewDadosJogador(ViewDadosJogador view){
        this.viewDadosJogador = view;
        viewDadosJogador.bGravarJuizAddActionListener(new OuvinteGravarJogador());
        viewDadosJogador.bEditarJuizAddActionListener(new OuvinteEditarJogador());
        viewDadosJogador.bExcluirJuizAddActionListener(new OuvinteExcluirJogador());
        
    }
    
    class OuvinteGravarJogador implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Jogador jogador = viewDadosJogador.preencherJogador();
            if(!viewDadosJogador.getErro()){
                JogadorController jogadorController = new JogadorController();
                jogadorController.setJogadorCadastro(jogador);
                jogadorController.salvar();
                viewDadosJogador.limparCampos();
            }else{
                viewDadosJogador.setError(false);
            }
        }
        
    }
    
    class OuvinteEditarJogador implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Jogador jogador = viewDadosJogador.preencherJogador();
            if(!viewDadosJogador.getErro()){
                JogadorController jogadorController = new JogadorController();
                jogadorController.setJogadorCadastro(jogador);
                jogadorController.editar();
                jogadorController.listar();
            }else{
                viewDadosJogador.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirJogador implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Jogador jogador = viewDadosJogador.getJogador();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Jogador abaixo: ");
            mensagem.append("\ncodigo: " + jogador.getId());
            mensagem.append("\nnome: " + jogador.getNome());
            int resposta = viewDadosJogador.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                JogadorController jogadorController = new JogadorController();
                jogadorController.setJogadorCadastro(jogador);
                jogadorController.excluir();
                viewDadosJogador.limparCampos();
            }
        }
        
    }
}
