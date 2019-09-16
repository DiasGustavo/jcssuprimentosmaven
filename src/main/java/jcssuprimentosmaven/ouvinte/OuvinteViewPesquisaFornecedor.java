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
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaFornecedor;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaFornecedor {
    ViewPesquisaFornecedor viewPesquisaFornecedor;
    
    public OuvinteViewPesquisaFornecedor(ViewPesquisaFornecedor view){
        this.viewPesquisaFornecedor = view;
        viewPesquisaFornecedor.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosFornecedores());
        viewPesquisaFornecedor.bPesquisarAddActionListener(new OuvintePesquisarFornecedor());
        viewPesquisaFornecedor.bExcluirAddActionListener(new OuvinteExcluirFornecedor());
    }
    
    class OuvintePesquisarTodosFornecedores implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                FornecedorController fornecedorController = new FornecedorController();
                fornecedorController.listar();
                List fornecedores = fornecedorController.getListaFornecedores();
                viewPesquisaFornecedor.listar(fornecedores);
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Não existe Jogadores para listar " + ex.getMessage());
            }
        }        
    } 
    
    class OuvintePesquisarFornecedor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                FornecedorController fornecedorController = new FornecedorController();
                String nome = viewPesquisaFornecedor.getNomePesquisar();
                List fornecedores;
                fornecedores = fornecedorController.buscarPorNome(nome);
                viewPesquisaFornecedor.listar(fornecedores);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar os fornecedores " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirFornecedor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Fornecedor fornecedor = viewPesquisaFornecedor.getFornecedor();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Fornecedor abaixo: ");
                mensagem.append("\ncodigo: " + fornecedor.getId());
                mensagem.append("\nnome: " + fornecedor.getFantasia());
                int resposta = viewPesquisaFornecedor.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    FornecedorController fornecedorController = new FornecedorController();
                    fornecedorController.setFornecedorCadastro(fornecedor);
                    fornecedorController.excluir();
                    fornecedorController.listar();
                    List fornecedores = fornecedorController.getListaFornecedores();
                    viewPesquisaFornecedor.listar(fornecedores);
                }
            }catch(RuntimeException ex){
                
            }
        }
        
    }
}
