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
import jcssuprimentosmaven.controller.ProdutoController;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaProduto;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaProduto {
    ViewPesquisaProduto viewPesquisaProduto;
    
    public OuvinteViewPesquisaProduto(ViewPesquisaProduto view){
        this.viewPesquisaProduto = view;
        this.viewPesquisaProduto.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosProdutos());
        this.viewPesquisaProduto.bPesquisarAddActionListener(new OuvintePesquisaProduto());
        this.viewPesquisaProduto.bExcluirAddActionListener(new OuvinteExcluirProduto());
    }
    
    class OuvintePesquisarTodosProdutos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ProdutoController produtoController = new ProdutoController();
                produtoController.listar();
                List produtos = produtoController.getListaProdutos();
                viewPesquisaProduto.listar(produtos);
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao listar os produtos " + ex.getMessage());
            }
        }
        
    }
    
    class OuvintePesquisaProduto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ProdutoController produtoController = new ProdutoController();
                Long codigo = viewPesquisaProduto.getIdPesquisar();
                List produtos = null;
                produtoController.setCodigo(codigo);
                produtoController.carregarDados();
                produtos.add(produtoController.getProdutoCadastro());
                viewPesquisaProduto.listar(produtos);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar o produto " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirProduto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Produto produto = viewPesquisaProduto.getProduto();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Produção abaixo: ");
                mensagem.append("\ncodigo: " + produto.getId());
                mensagem.append("\nQuantidade: " + produto.getQuantidade());
                mensagem.append("\nMateria: " + produto.getFabrica().getNomeFantasia());
                int resposta = viewPesquisaProduto.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ProdutoController produtoController = new ProdutoController();
                    produtoController.setProdutoCadastro(produto);
                    produtoController.excluir();
                    List produtos = produtoController.getListaProdutos();
                    viewPesquisaProduto.listar(produtos);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao excluir o produto " + ex.getMessage());
            }
        }
        
    }
    
}
