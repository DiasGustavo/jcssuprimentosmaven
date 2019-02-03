/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.ArmazemController;
import jcssuprimentosmaven.domain.Armazem;
import jcssuprimentosmaven.view.ViewDadosArmazem;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosArmazem {
    ViewDadosArmazem viewDadosArmazem;
    
    public OuvinteViewDadosArmazem(ViewDadosArmazem view){
        this.viewDadosArmazem = view;
        viewDadosArmazem.bGravarAddActionListener(new OuvinteGravarArmazem());
        viewDadosArmazem.bEditarAddActionListener(new OuvinteEditarArmazem());
        viewDadosArmazem.bExcluirAddActionListener(new OuvinteExcluirArmazem());
    }
    
    class OuvinteGravarArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Armazem armazem = viewDadosArmazem.preencherArmazem();
            if(!viewDadosArmazem.getErro()){
                ArmazemController armazemController = new ArmazemController();
                armazemController.setArmazemCadastro(armazem);
                armazemController.salvar();
                viewDadosArmazem.limparCampos();
            }else{
                viewDadosArmazem.setError(false);
            }
        }
        
    }
    
    class OuvinteEditarArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Armazem armazem = viewDadosArmazem.preencherArmazem();
            if(!viewDadosArmazem.getErro()){
                ArmazemController armazemController = new ArmazemController();
                armazemController.setArmazemCadastro(armazem);
                armazemController.editar();
                armazemController.listar();
            }else{
                viewDadosArmazem.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Armazem armazem = viewDadosArmazem.getArmazem();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Armazem abaixo: ");
            mensagem.append("\ncodigo: " + armazem.getId());
            mensagem.append("\nnome: " + armazem.getNomeFantasia());
            int resposta = viewDadosArmazem.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                ArmazemController armazemController = new ArmazemController();
                armazemController.setArmazemCadastro(armazem);
                armazemController.excluir();
                viewDadosArmazem.limparCampos();
            }
        }
        
    }
}
