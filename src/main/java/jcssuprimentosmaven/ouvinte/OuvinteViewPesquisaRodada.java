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
import jcssuprimentosmaven.controller.RodadaController;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaRodada;
import jcssuprimentosmaven.domain.Rodada;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaRodada {
    ViewPesquisaRodada viewPesquisaRodada;
    
    public OuvinteViewPesquisaRodada(ViewPesquisaRodada view){
        this.viewPesquisaRodada = view;
        
        viewPesquisaRodada.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosRodadas ());
        viewPesquisaRodada.bExcluirAddActionListener(new OuvinteExcluirRodada());
        viewPesquisaRodada.bFinalizarAddActionListener(new OuvinteFinalizar());
    }
    
    class OuvintePesquisarTodosRodadas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                RodadaController rodadaController = new RodadaController();
                rodadaController.listar();
                List rodadas = rodadaController.getListaRodadas();
                viewPesquisaRodada.listar(rodadas);
                     
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Não existe Rodadas para listar " + ex.getMessage());
            }
        }
        
    }
     
    
    class OuvinteExcluirRodada implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            Rodada rodada = viewPesquisaRodada.getRodada();
            
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Rodada abaixo: ");
                mensagem.append("\ncodigo: " + rodada.getId());
                
                int resposta = viewPesquisaRodada.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    RodadaController rodadaController = new RodadaController();
                    rodadaController.setRodadaCadastro(rodada);
                    rodadaController.excluir();
                    rodadaController.listar();
                    List rodadas = rodadaController.getListaRodadas();
                    viewPesquisaRodada.listar(rodadas);
                }    
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao exlcuir o rodada");
            }
            
        }
        
    }
    
    class OuvinteFinalizar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Rodada rodada = viewPesquisaRodada.getRodada();
            
        }
        
    }
}
