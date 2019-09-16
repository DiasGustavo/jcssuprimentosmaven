/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.EstoqueSuprimentoController;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.view.ViewDadosEstoqueSuprimento;


/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosEstoqueSuprimento {
     ViewDadosEstoqueSuprimento viewDadosEstoqueSuprimento;
     
     public OuvinteViewDadosEstoqueSuprimento(ViewDadosEstoqueSuprimento view){
         this.viewDadosEstoqueSuprimento = view;
         viewDadosEstoqueSuprimento.bGravarAddActionListener(new OuvinteGravarEstoque());
         viewDadosEstoqueSuprimento.bEditarAddActionListener(new OuvinteEditarEstoque());
         viewDadosEstoqueSuprimento.bExcluirAddActionListener(new OuvinteExcluirEstoque());
     }
     
     class OuvinteGravarEstoque implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueSuprimento estoque = viewDadosEstoqueSuprimento.preencherEstoqueSuprimento();
            if(!viewDadosEstoqueSuprimento.getErro()){
                EstoqueSuprimentoController estoqueController = new EstoqueSuprimentoController();
                estoqueController.setEstoqueCadastro(estoque);
                estoqueController.salvar();
                viewDadosEstoqueSuprimento.limparCampos();
            }else{
                viewDadosEstoqueSuprimento.setError(false);
            }
        }
         
     }
     
     class OuvinteEditarEstoque implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueSuprimento estoque = viewDadosEstoqueSuprimento.preencherEstoqueSuprimento();
            if(!viewDadosEstoqueSuprimento.getErro()){
                EstoqueSuprimentoController estoqueController = new EstoqueSuprimentoController();
                estoqueController.setEstoqueCadastro(estoque);
                estoqueController.editar();
                estoqueController.listar();
            }else{
                viewDadosEstoqueSuprimento.setError(false);
            }
        }
         
     }
     
     class OuvinteExcluirEstoque implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueSuprimento estoque = viewDadosEstoqueSuprimento.getEstoqueSuprimento();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Estoque abaixo: ");
            mensagem.append("\ncodigo: " + estoque.getId());
            mensagem.append("\nQuantidade: " + estoque.getQuantidade());
            mensagem.append("\nFabrica: " + estoque.getArmazemSuprimento().getNomeFantasia());
            int resposta = viewDadosEstoqueSuprimento.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                EstoqueSuprimentoController estoqueController = new EstoqueSuprimentoController();
                estoqueController.setEstoqueCadastro(estoque);
                estoqueController.excluir();
                viewDadosEstoqueSuprimento.limparCampos();
            }
        }
         
     }
}
