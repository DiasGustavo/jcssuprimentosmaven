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
import jcssuprimentosmaven.controller.ArmazemController;
import jcssuprimentosmaven.domain.Armazem;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaArmazem;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaArmazem {

    ViewPesquisaArmazem viewPesquisaArmazem;

    public OuvinteViewPesquisaArmazem(ViewPesquisaArmazem view) {
        this.viewPesquisaArmazem = view;
        viewPesquisaArmazem.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosArmazens());
        viewPesquisaArmazem.bPesquisarAddActionListener(new OuvintePesquisarArmazem());
        viewPesquisaArmazem.bExcluirAddActionListener(new OuvinteExcluirArmazem());
    }

    class OuvintePesquisarTodosArmazens implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArmazemController armazemController = new ArmazemController();
                armazemController.listar();
                List armazens = armazemController.getListaArmazens();
                viewPesquisaArmazem.listar(armazens);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe armazens para listar " + ex.getMessage());
            }
        }

    }
    
    class OuvintePesquisarArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ArmazemController armazemController = new ArmazemController();
                String nome = viewPesquisaArmazem.getNomePesquisar();
                List armazens;
                armazens = armazemController.buscarPorNome(nome);
                viewPesquisaArmazem.listar(armazens);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar os armazens " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Armazem armazem = viewPesquisaArmazem.getArmazem();
                 StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Armazem abaixo: ");
                mensagem.append("\ncodigo: " + armazem.getId());
                mensagem.append("\nnome: " + armazem.getNomeFantasia());
                int resposta = viewPesquisaArmazem.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ArmazemController armazemController = new ArmazemController();
                    armazemController.setArmazemCadastro(armazem);
                    armazemController.excluir();
                    List armazens = armazemController.getListaArmazens();
                    viewPesquisaArmazem.listar(armazens);
                }
            }catch(RuntimeException ex){
               ViewUtil.addMsgErro("Erro ao excluir o armazem " + ex.getMessage()); 
            }
        }
        
    }
}
