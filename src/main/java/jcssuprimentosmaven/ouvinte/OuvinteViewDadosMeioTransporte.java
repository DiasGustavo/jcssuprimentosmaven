/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.MeioTransporteController;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.view.ViewDadosMeioTransporte;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosMeioTransporte {
    ViewDadosMeioTransporte viewMeioTransporte;
    
    public OuvinteViewDadosMeioTransporte(ViewDadosMeioTransporte view){
        this.viewMeioTransporte = view;
        viewMeioTransporte.bGravarMeioTransporteAddActionListener(new OuvinteGravarMeioTransporte());
        viewMeioTransporte.bEditarMeioTransporteAddActionListener(new OuvinteEditarMeioTransporte());
        viewMeioTransporte.bExcluirMeioTransporteAddActionListener(new OuvinteExcluirMeioTransporte());
    }
    
    class OuvinteGravarMeioTransporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MeioTransporte meioTransporte = viewMeioTransporte.preencherMeioTransporte();
            if (!viewMeioTransporte.getErro()){
                MeioTransporteController meioTransporteController = new MeioTransporteController();
                meioTransporteController.setMeioTransporteCadastro(meioTransporte);
                meioTransporteController.salvar();
                viewMeioTransporte.limparCampos();
            }else{
                viewMeioTransporte.setError(false);
            }
                
            
        }
        
    }
    
    class OuvinteEditarMeioTransporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MeioTransporte meioTransporte = viewMeioTransporte.preencherMeioTransporte();
            if (!viewMeioTransporte.getErro()){
                MeioTransporteController meioTransporteController = new MeioTransporteController();
                meioTransporteController.setMeioTransporteCadastro(meioTransporte);
                meioTransporteController.editar();
                viewMeioTransporte.limparCampos();
            }else{
                viewMeioTransporte.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirMeioTransporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MeioTransporte meioTransporte = viewMeioTransporte.getMeioTransporte();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Meio de Transporte abaixo: ");
            mensagem.append("\ncodigo: " + meioTransporte.getId());
            mensagem.append("\nDescrição: " + meioTransporte.getDescricao());
            int resposta = viewMeioTransporte.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                MeioTransporteController meioTransporteController = new MeioTransporteController();
                meioTransporteController.setMeioTransporteCadastro(meioTransporte);
                meioTransporteController.excluir();
            }
        }
        
    }
    
}
