/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.EstoqueDistribuicaoController;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.view.ViewDadosEstoqueDistribuicao;


/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosEstoqueDistribuicao {
     ViewDadosEstoqueDistribuicao viewDadosEstoqueDistribuicao;
     
     public OuvinteViewDadosEstoqueDistribuicao(ViewDadosEstoqueDistribuicao view){
         this.viewDadosEstoqueDistribuicao = view;
         viewDadosEstoqueDistribuicao.bGravarAddActionListener(new OuvinteGravarEstoque());
         viewDadosEstoqueDistribuicao.bEditarAddActionListener(new OuvinteEditarEstoque());
         viewDadosEstoqueDistribuicao.bExcluirAddActionListener(new OuvinteExcluirEstoque());
     }
     
     class OuvinteGravarEstoque implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueDistribuicao estoque = viewDadosEstoqueDistribuicao.preencherEstoqueDistribuicao();
            if(!viewDadosEstoqueDistribuicao.getErro()){
                EstoqueDistribuicaoController estoqueController = new EstoqueDistribuicaoController();
                estoqueController.setEstoqueCadastro(estoque);
                estoqueController.salvar();
                viewDadosEstoqueDistribuicao.limparCampos();
            }else{
                viewDadosEstoqueDistribuicao.setError(false);
            }
        }
         
     }
     
     class OuvinteEditarEstoque implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueDistribuicao estoque = viewDadosEstoqueDistribuicao.preencherEstoqueDistribuicao();
            if(!viewDadosEstoqueDistribuicao.getErro()){
                EstoqueDistribuicaoController estoqueController = new EstoqueDistribuicaoController();
                estoqueController.setEstoqueCadastro(estoque);
                estoqueController.editar();
                estoqueController.listar();
            }else{
                viewDadosEstoqueDistribuicao.setError(false);
            }
        }
         
     }
     
     class OuvinteExcluirEstoque implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueDistribuicao estoque = viewDadosEstoqueDistribuicao.getEstoqueDistribuicao();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Estoque abaixo: ");
            mensagem.append("\ncodigo: " + estoque.getId());
            mensagem.append("\nQuantidade: " + estoque.getQuantidade());
            mensagem.append("\nFabrica: " + estoque.getArmazemDistribuicao().getNomeFantasia());
            int resposta = viewDadosEstoqueDistribuicao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                EstoqueDistribuicaoController estoqueController = new EstoqueDistribuicaoController();
                estoqueController.setEstoqueCadastro(estoque);
                estoqueController.excluir();
                viewDadosEstoqueDistribuicao.limparCampos();
            }
        }
         
     }
}
