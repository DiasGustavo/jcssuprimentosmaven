/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.view.ViewDadosArmazemFabrica;
import jcssuprimentosmaven.view.ViewDadosArmazemSuprimento;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosArmazemFabrica {
    ViewDadosArmazemFabrica viewDadosArmazem;
    
    public OuvinteViewDadosArmazemFabrica(ViewDadosArmazemFabrica view){
        this.viewDadosArmazem = view;
        viewDadosArmazem.bGravarAddActionListener(new OuvinteGravarArmazem());
        viewDadosArmazem.bEditarAddActionListener(new OuvinteEditarArmazem());
        viewDadosArmazem.bExcluirAddActionListener(new OuvinteExcluirArmazem());
    }
    
    class OuvinteGravarArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArmazemFabrica armazem = viewDadosArmazem.preencherArmazem();
            if(!viewDadosArmazem.getErro()){
                ArmazemFabricaController armazemController = new ArmazemFabricaController();
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
            ArmazemFabrica armazem = viewDadosArmazem.preencherArmazem();
            if(!viewDadosArmazem.getErro()){
                ArmazemFabricaController armazemController = new ArmazemFabricaController();
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
            ArmazemFabrica armazem = viewDadosArmazem.getArmazem();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Armazem abaixo: ");
            mensagem.append("\ncodigo: " + armazem.getId());
            mensagem.append("\nFábrica" + armazem.getFabrica().getNomeFantasia());
            int resposta = viewDadosArmazem.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                ArmazemFabricaController armazemController = new ArmazemFabricaController();
                armazemController.setArmazemCadastro(armazem);
                armazemController.excluir();
                viewDadosArmazem.limparCampos();
            }
        }
        
    }
}
