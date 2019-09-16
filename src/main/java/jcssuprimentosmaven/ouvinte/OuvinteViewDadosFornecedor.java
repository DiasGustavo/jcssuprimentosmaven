/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.FornecedorController;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.view.ViewDadosFornecedor;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosFornecedor {

    ViewDadosFornecedor viewDadosFornecedor;

    public OuvinteViewDadosFornecedor(ViewDadosFornecedor view) {
        this.viewDadosFornecedor = view;
        viewDadosFornecedor.bGravarFornecedorAddActionListener(new OuvinteGravarFornecedor());
        viewDadosFornecedor.bEditarFornecedorAddActionListener(new OuvinteEditarFornecedor());
        viewDadosFornecedor.bExcluirFornecedorAddActionListener(new OuvinteExcluirFornecedor());
    }

    class OuvinteGravarFornecedor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Fornecedor fornecedor = viewDadosFornecedor.preencherFornecedor();

            if (!viewDadosFornecedor.getErro()) {
                FornecedorController fornecedorController = new FornecedorController();
                fornecedorController.setFornecedorCadastro(fornecedor);
                fornecedorController.salvar();
                viewDadosFornecedor.limparCampos();
            } else {
                viewDadosFornecedor.setError(false);

            }

        }
    }

    class OuvinteEditarFornecedor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Fornecedor fornecedor = viewDadosFornecedor.preencherFornecedor();
            if (!viewDadosFornecedor.getErro()) {
                FornecedorController fornecedorController = new FornecedorController();
                fornecedorController.setFornecedorCadastro(fornecedor);
                fornecedorController.editar();
                viewDadosFornecedor.limparCampos();
            } else {
                viewDadosFornecedor.setError(false);

            }
        }

    }

    class OuvinteExcluirFornecedor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Fornecedor fornecedor = viewDadosFornecedor.getFornecedor();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Fornecedor abaixo: ");
            mensagem.append("\ncodigo: " + fornecedor.getId());
            mensagem.append("\nnome: " + fornecedor.getFantasia());
            int resposta = viewDadosFornecedor.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                FornecedorController fornecedorController = new FornecedorController();
                fornecedorController.setFornecedorCadastro(fornecedor);
                fornecedorController.excluir();
                viewDadosFornecedor.limparCampos();
            }
        }

    }
}

