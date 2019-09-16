/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.MateriaPrimaController;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.view.ViewDadosMateriaPrima;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosMateriaPrima {
    ViewDadosMateriaPrima viewDadosMateriaPrima;
    
    public OuvinteViewDadosMateriaPrima(ViewDadosMateriaPrima view){
        this.viewDadosMateriaPrima = view;
        viewDadosMateriaPrima.bGravarJuizAddActionListener(new OuvinteGravarMateriaPrima());
        viewDadosMateriaPrima.bEditarJuizAddActionListener(new OuvinteEditarMateriaPrima());
    }
    
    class OuvinteGravarMateriaPrima implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MateriaPrima materia = viewDadosMateriaPrima.preencherMateriaPrima();
            
            if(!viewDadosMateriaPrima.getErro()){
                MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                materiaPrimaController.setMateriaPrimaCadastro(materia);
                materiaPrimaController.salvar();
                viewDadosMateriaPrima.limparCampos();
            }else{
                viewDadosMateriaPrima.setError(false);
            }
        }
        
    }
    
    class OuvinteEditarMateriaPrima implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MateriaPrima materiaPrima = viewDadosMateriaPrima.preencherMateriaPrima();
            if(!viewDadosMateriaPrima.getErro()){
                MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                materiaPrimaController.setMateriaPrimaCadastro(materiaPrima);
                materiaPrimaController.editar();
                materiaPrimaController.listar();                
            }else{
                viewDadosMateriaPrima.setError(false);
            }            
            
        }
        
    }
    
    class OuvinteExcluirMateriaPrima implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MateriaPrima materiaPrima = viewDadosMateriaPrima.getMateriaPrima();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Materia-Prima abaixo: ");
            mensagem.append("\ncodigo: " + materiaPrima.getId());
            mensagem.append("\nnome: " + materiaPrima.getDescricao());
            int resposta = viewDadosMateriaPrima.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                materiaPrimaController.setMateriaPrimaCadastro(materiaPrima);
                materiaPrimaController.excluir();
                viewDadosMateriaPrima.limparCampos();
            }
        }
        
    }
}
