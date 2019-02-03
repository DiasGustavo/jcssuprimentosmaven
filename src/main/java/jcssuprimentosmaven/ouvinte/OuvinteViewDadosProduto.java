/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.ProdutoController;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosProduto;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosProduto {
    ViewDadosProduto viewDadosProduto;
    
    public OuvinteViewDadosProduto(ViewDadosProduto view){
        this.viewDadosProduto = view;
        this.viewDadosProduto.bGravarProducaoAddActionListener(new OuvinteGravarProduto());
        this.viewDadosProduto.bEditarProducaoAddActionListener(new OuvinteEditarProduto());
        this.viewDadosProduto.bExcluirProducaoAddActionListener(new OuvinteExcluirProduto());
    }
    
    class OuvinteGravarProduto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Produto produto = viewDadosProduto.preencherProduto();
            try{
                if(!viewDadosProduto.getErro()){
                    ProdutoController produtoController = new ProdutoController();
                    produtoController.setProdutoCadastro(produto);
                    produtoController.salvar();
                    viewDadosProduto.limparCampos();
                }else{
                    viewDadosProduto.setError(false);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao gravar o produto " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteEditarProduto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Produto produto = viewDadosProduto.preencherProduto();
            try{
                if(!viewDadosProduto.getErro()){
                    ProdutoController produtoController = new ProdutoController();
                    produtoController.setProdutoCadastro(produto);
                    produtoController.editar();
                    produtoController.listar();
                }else{
                    viewDadosProduto.setError(false);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao editar o produto " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirProduto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Produto produto = viewDadosProduto.getProduto();
            try{
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Producao abaixo: ");
                mensagem.append("\ncodigo: " + produto.getId());
                mensagem.append("\nQuantidade: " + produto.getQuantidade());
                mensagem.append("\nArmazém: " + produto.getFabrica().getNomeFantasia());
                int resposta = viewDadosProduto.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ProdutoController produtoController = new ProdutoController();
                    produtoController.setProdutoCadastro(produto);
                    produtoController.excluir();
                    produtoController.listar();
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao excluir o produto " + ex.getMessage());
            }
        }
        
    }
}
