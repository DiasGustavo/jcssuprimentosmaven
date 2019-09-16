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
import jcssuprimentosmaven.controller.JuizController;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaJuiz;
import jcssuprimentosmaven.domain.Juiz;
import jcssuprimentosmaven.view.ViewDadosJuiz;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaJuiz {

    ViewPesquisaJuiz viewPesquisaJuiz;
    ViewDadosJuiz viewDadosJuiz;

    public OuvinteViewPesquisaJuiz(ViewPesquisaJuiz view) {
        this.viewPesquisaJuiz = view;
        this.viewPesquisaJuiz.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosJuizes());
        this.viewPesquisaJuiz.bPesquisarAddActionListener(new OuvintePesquisarPorNome());
        this.viewPesquisaJuiz.bExcluirAddActionListener(new OuvintePesquisarExcluir());
    }

    class OuvintePesquisarTodosJuizes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JuizController juizController = new JuizController();
                juizController.listar();
                List juizes = juizController.getListaJuizes();
                viewPesquisaJuiz.listar(juizes);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Jogadores para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarPorNome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JuizController juizController = new JuizController();
                String nome = viewPesquisaJuiz.getNomePesquisar();
                List juizes;
                juizes = juizController.buscarPorNome(nome);
                viewPesquisaJuiz.listar(juizes);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar os juízes " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarExcluir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Juiz juiz = viewPesquisaJuiz.getJuiz();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Juiz abaixo: ");
                mensagem.append("\ncodigo: " + juiz.getId());
                mensagem.append("\nnome: " + juiz.getNome());
                int resposta = viewPesquisaJuiz.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    JuizController juizController = new JuizController();
                    juizController.setJuizCadastro(juiz);
                    juizController.excluir();
                    juizController.listar();
                    List juizes = juizController.getListaJuizes();
                    viewPesquisaJuiz.listar(juizes);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao exlcuir o juiz");
            }
        }

    }    
}
