/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.view.ViewDadosFabrica;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosFabrica {
    ViewDadosFabrica viewDadosFabrica;
    
    public OuvinteViewDadosFabrica(ViewDadosFabrica view){
        this.viewDadosFabrica = view;
        viewDadosFabrica.bGravarAddActionListener(new OuvinteGravarFabrica());
        viewDadosFabrica.bEditarAddActionListener(new OuvinteEditarFabrica());
        viewDadosFabrica.bExcluirAddActionListener(new OuvinteExcluirFabrica());
    }
    
    class OuvinteGravarFabrica implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Fabrica fabrica = viewDadosFabrica.preencherFabrica();
            if(!viewDadosFabrica.getErro()){
                FabricaController fabricaController = new FabricaController();
                fabricaController.setArmazemCadastro(fabrica);
                fabricaController.salvar();
                viewDadosFabrica.limparCampos();
            }else{
                viewDadosFabrica.setError(false);
            }
        }
        
    }
    
    class OuvinteEditarFabrica implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Fabrica fabrica = viewDadosFabrica.preencherFabrica();
            if(!viewDadosFabrica.getErro()){
                FabricaController fabricaController = new FabricaController();
                fabricaController.setArmazemCadastro(fabrica);
                fabricaController.editar();
                fabricaController.listar();
            }else{
                viewDadosFabrica.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirFabrica implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Fabrica fabrica = viewDadosFabrica.getFabrica();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Fábrica abaixo: ");
            mensagem.append("\ncodigo: " + fabrica.getId());
            mensagem.append("\nnome: " + fabrica.getNomeFantasia());
            int resposta = viewDadosFabrica.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                FabricaController fabricaController = new FabricaController();
                fabricaController.setArmazemCadastro(fabrica);
                fabricaController.excluir();
                fabricaController.listar();
            }
        }
        
    }
}
