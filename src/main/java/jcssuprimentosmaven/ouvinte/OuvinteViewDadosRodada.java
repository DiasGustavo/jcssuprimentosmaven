/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.RodadaController;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.view.ViewDadosRodada;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosRodada {
    ViewDadosRodada viewDadosRodada;
    
    public OuvinteViewDadosRodada(ViewDadosRodada view){
        this.viewDadosRodada = view;
        viewDadosRodada.bGravarRodadaActionListener(new OuvinteGravarRodada());
        viewDadosRodada.bEditarRodadaActionListener(new OuvinteEditarRodada());
        viewDadosRodada.bExecutarRodadaActionListener(new OuvinteExecutarRodada());
    }
    
    class OuvinteGravarRodada implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Rodada rodada = viewDadosRodada.preencherRodada();
            if(!viewDadosRodada.isError()){
                RodadaController rodadaController = new RodadaController();
                rodadaController.setRodadaCadastro(rodada);
                rodadaController.salvar();
                viewDadosRodada.limaparCampos();
            }else{
                viewDadosRodada.setError(false);
            }
        }
        
    }
    
    class OuvinteExecutarRodada implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class OuvinteEditarRodada implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Rodada rodada = viewDadosRodada.preencherRodada();
            if(!viewDadosRodada.isError()){
                RodadaController rodadaController = new RodadaController();
                rodadaController.setRodadaCadastro(rodada);
                rodadaController.editar();
                viewDadosRodada.limaparCampos();
            }else{
                viewDadosRodada.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirRodada implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Rodada rodada = viewDadosRodada.getRodada();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Rodada abaixo: ");
            mensagem.append("\ncodigo: " + rodada.getId());            
            int resposta = viewDadosRodada.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                RodadaController rodadaController = new RodadaController();
                rodadaController.setRodadaCadastro(rodada);
                rodadaController.excluir();
                viewDadosRodada.limaparCampos();
            }
        }
        
    }
}
