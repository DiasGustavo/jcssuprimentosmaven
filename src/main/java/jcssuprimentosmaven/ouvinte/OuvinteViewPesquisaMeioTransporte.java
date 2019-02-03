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
import jcssuprimentosmaven.controller.MeioTransporteController;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaMeioTransporte;
import jcssuprimentosmaven.domain.MeioTransporte;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaMeioTransporte {

    ViewPesquisaMeioTransporte viewPesquisaMeioTransporte;

    public OuvinteViewPesquisaMeioTransporte(ViewPesquisaMeioTransporte view) {
        this.viewPesquisaMeioTransporte = view;
        viewPesquisaMeioTransporte.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosMeioTransporte());
        viewPesquisaMeioTransporte.bPesquisarAddActionListener(new OuvintePesquisarNomeMeioTransporte());
        viewPesquisaMeioTransporte.bExcluirAddActionListener(new OuvinteExcluirMeioTransporte());
        viewPesquisaMeioTransporte.bPesquisarDestinoAddActionListener(new OuvintePesquisarDestinoMeioTransporte());
    }

    class OuvintePesquisarTodosMeioTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MeioTransporteController meioTransporteController = new MeioTransporteController();
                meioTransporteController.listar();
                List meios = meioTransporteController.getListaMeiosTransportes();
                viewPesquisaMeioTransporte.listar(meios);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Meios de Transporte para listar " + ex.getMessage());
            }
        }

    }
    
     class OuvintePesquisarDestinoMeioTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MeioTransporteController meioTransporteController = new MeioTransporteController();                
                List meios = meioTransporteController.buscarPorDestino(viewPesquisaMeioTransporte.getDestino());
                viewPesquisaMeioTransporte.listar(meios);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Meios de Transporte para listar " + ex.getMessage());
            }
        }

    }
    
    class OuvintePesquisarNomeMeioTransporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                MeioTransporteController mtdao = new MeioTransporteController();
                String nome = viewPesquisaMeioTransporte.getNomePesquisar();
                List meios;
                meios = mtdao.buscarPorNome(nome);
                viewPesquisaMeioTransporte.listar(meios);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar o Meio de Transporte " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirMeioTransporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                MeioTransporte meio = viewPesquisaMeioTransporte.getMeioTransporte();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Meio de Transporte abaixo: ");
                mensagem.append("\ncodigo: " + meio.getId());
                mensagem.append("\nDesrição: " + meio.getDescricao());
                int resposta = viewPesquisaMeioTransporte.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    MeioTransporteController meioTransporteController = new MeioTransporteController();
                    meioTransporteController.setMeioTransporteCadastro(meio);
                    meioTransporteController.excluir();
                    meioTransporteController.listar();
                    List meios = meioTransporteController.getListaMeiosTransportes();
                    viewPesquisaMeioTransporte.listar(meios);
                }
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao exlcuir o Meio de Transporte");
            }
        }
        
    }

}
