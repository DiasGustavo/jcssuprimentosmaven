/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.TransportadoraController;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.view.ViewDadosTransportadora;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosTransportadora {

    ViewDadosTransportadora viewDadosTransportadora;

    public OuvinteViewDadosTransportadora(ViewDadosTransportadora view) {
        this.viewDadosTransportadora = view;
        viewDadosTransportadora.bGravarFornecedorAddActionListener(new OuvinteGravarTransportadora());
        viewDadosTransportadora.bEditarFornecedorAddActionListener(new OuvinteEditarTransportadora());
        viewDadosTransportadora.bExcluirFornecedorAddActionListener(new OuvinteExcluirTransportadora());
    }

    class OuvinteGravarTransportadora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transportadora transportadora = viewDadosTransportadora.preencherTransportadora();

            if (!viewDadosTransportadora.getErro()) {
                TransportadoraController transportadoraController = new TransportadoraController();
                transportadoraController.setTransportadoraCadastro(transportadora);
                transportadoraController.salvar();
                viewDadosTransportadora.limparCampos();
            } else {
                viewDadosTransportadora.setError(false);

            }

        }
    }

    class OuvinteEditarTransportadora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transportadora transportadora = viewDadosTransportadora.preencherTransportadora();
            if (!viewDadosTransportadora.getErro()) {
                TransportadoraController transportadoraController = new TransportadoraController();
                transportadoraController.setTransportadoraCadastro(transportadora);
                transportadoraController.editar();
                viewDadosTransportadora.limparCampos();
            } else {
                viewDadosTransportadora.setError(false);

            }
        }

    }

    class OuvinteExcluirTransportadora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transportadora transportadora = viewDadosTransportadora.getTransportadora();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Fornecedor abaixo: ");
            mensagem.append("\ncodigo: " + transportadora.getId());
            mensagem.append("\nnome: " + transportadora.getnomeFantasia());
            int resposta = viewDadosTransportadora.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                TransportadoraController transportadoraController = new TransportadoraController();
                transportadoraController.setTransportadoraCadastro(transportadora);
                transportadoraController.excluir();
                viewDadosTransportadora.limparCampos();
            }
        }

    }
}

