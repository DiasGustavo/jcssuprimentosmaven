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
import jcssuprimentosmaven.view.ViewDadosJuiz;
import jcssuprimentosmaven.domain.Juiz;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosJuiz {

    ViewDadosJuiz viewDadosJuiz;

    public OuvinteViewDadosJuiz(ViewDadosJuiz view) {
        this.viewDadosJuiz = view;
        viewDadosJuiz.bGravarJuizAddActionListener(new OuvinteGravarJuiz());
        viewDadosJuiz.bEditarJuizAddActionListener(new OuvinteEditarJuiz());
        viewDadosJuiz.bExcluirJuizAddActionListener(new OuvinteExcluirJuiz());
    }

    class OuvinteGravarJuiz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Juiz juiz = viewDadosJuiz.preencherJuiz();

            if (!viewDadosJuiz.getErro()) {
                JuizController juizController = new JuizController();
                juizController.setJuizCadastro(juiz);
                juizController.salvar();
                viewDadosJuiz.limparCampos();
            } else {
                viewDadosJuiz.setError(false);
            }
        }
    }

    class OuvinteEditarJuiz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Juiz juiz = viewDadosJuiz.preencherJuiz();

            if (!viewDadosJuiz.getErro()) {
                JuizController juizController = new JuizController();
                juizController.setJuizCadastro(juiz);
                juizController.editar();
                juizController.listar();                
            } else {
                viewDadosJuiz.setError(false);
            }
        }

    }

    class OuvinteExcluirJuiz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Juiz juiz = viewDadosJuiz.getJuiz();

            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Juiz abaixo: ");
            mensagem.append("\ncodigo: " + juiz.getId());
            mensagem.append("\nnome: " + juiz.getNome());
            int resposta = viewDadosJuiz.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                JuizController juizController = new JuizController();
                juizController.setJuizCadastro(juiz);
                juizController.excluir();
                viewDadosJuiz.limparCampos();
            }
        }

    }

}
