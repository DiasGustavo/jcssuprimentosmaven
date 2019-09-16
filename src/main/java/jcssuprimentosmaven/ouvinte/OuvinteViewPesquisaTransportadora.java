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
import jcssuprimentosmaven.controller.FornecedorController;
import jcssuprimentosmaven.controller.TransportadoraController;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaFornecedor;
import jcssuprimentosmaven.view.ViewPesquisaTransportadora;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaTransportadora {
    ViewPesquisaTransportadora viewPesquisaTransportadora;
    
    public OuvinteViewPesquisaTransportadora(ViewPesquisaTransportadora view){
        this.viewPesquisaTransportadora = view;
        viewPesquisaTransportadora.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosTransportadoras());
        viewPesquisaTransportadora.bPesquisarAddActionListener(new OuvintePesquisarTransportadora());
        viewPesquisaTransportadora.bExcluirAddActionListener(new OuvinteExcluirTransportadora());
    }
    
    class OuvintePesquisarTodosTransportadoras implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                TransportadoraController transportadoraController = new TransportadoraController();
                transportadoraController.listar();
                List transportadoras = transportadoraController.getListaTransportadoras();
                viewPesquisaTransportadora.listar(transportadoras);
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Não existe Transportadoras para listar " + ex.getMessage());
            }
        }        
    } 
    
    class OuvintePesquisarTransportadora implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                TransportadoraController transportadoraController = new TransportadoraController();
                String nome = viewPesquisaTransportadora.getNomePesquisar();
                List transportadoras;
                transportadoras = transportadoraController.buscarPorNome(nome);
                viewPesquisaTransportadora.listar(transportadoras);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar os transportadoras " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirTransportadora implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Transportadora transportadora = viewPesquisaTransportadora.getTransportadora();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Transportadora abaixo: ");
                mensagem.append("\ncodigo: " + transportadora.getId());
                mensagem.append("\nnome: " + transportadora.getnomeFantasia());
                int resposta = viewPesquisaTransportadora.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    TransportadoraController transportadoraController = new TransportadoraController();
                    transportadoraController.setTransportadoraCadastro(transportadora);
                    transportadoraController.excluir();
                    transportadoraController.listar();
                    List transportadoras = transportadoraController.getListaTransportadoras();
                    viewPesquisaTransportadora.listar(transportadoras);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao excluir os transportadoras " + ex.getMessage());
            }
        }
        
    }
}
