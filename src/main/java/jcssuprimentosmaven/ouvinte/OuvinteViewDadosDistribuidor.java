/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.DistribuidorController;
import jcssuprimentosmaven.view.ViewDadosDistribuidor;
import jcssuprimentosmaven.domain.Distribuidor;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosDistribuidor {
     ViewDadosDistribuidor viewDadosDistribuidor;
     
     public OuvinteViewDadosDistribuidor(ViewDadosDistribuidor view){
         this.viewDadosDistribuidor = view;
         viewDadosDistribuidor.bGravarAddActionListener(new OuvinteGravarDistribuidor());
         viewDadosDistribuidor.bEditarAddActionListener(new OuvinteEditarDistribuidor());
         viewDadosDistribuidor.bExcluirAddActionListener(new OuvinteExcluirDistribuidor());
     }
     
     class OuvinteGravarDistribuidor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Distribuidor distribuidor = viewDadosDistribuidor.preencherDistribuidor();
            if(!viewDadosDistribuidor.getErro()){
                DistribuidorController distribuidorController = new DistribuidorController();
                distribuidorController.setDistribuidorCadastro(distribuidor);
                distribuidorController.salvar();
                viewDadosDistribuidor.limparCampos();
            }else{
                viewDadosDistribuidor.setError(false);
            }
        }
         
     }
     
     class OuvinteEditarDistribuidor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Distribuidor distribuidor = viewDadosDistribuidor.preencherDistribuidor();
            if(!viewDadosDistribuidor.getErro()){
                DistribuidorController distribuidorController = new DistribuidorController();
                distribuidorController.setDistribuidorCadastro(distribuidor);
                distribuidorController.editar();
                distribuidorController.listar();
            }else{
                viewDadosDistribuidor.setError(false);
            }
        }
         
     }
     
     class OuvinteExcluirDistribuidor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Distribuidor empresa = viewDadosDistribuidor.getDistribuidor();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Distribuidor abaixo: ");
            mensagem.append("\ncodigo: " + empresa.getId());
            mensagem.append("\nnome: " + empresa.getNomeFantasia());
            int resposta = viewDadosDistribuidor.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                DistribuidorController distribuidorController = new DistribuidorController();
                distribuidorController.setDistribuidorCadastro(empresa);
                distribuidorController.excluir();
                viewDadosDistribuidor.limparCampos();
            }
        }
         
     }
}
