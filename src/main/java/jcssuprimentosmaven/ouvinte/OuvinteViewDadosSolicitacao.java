/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.SolicitacaoController;
import jcssuprimentosmaven.dao.ProdutoDAO;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaSolicitacao.OuvinteExcluirSolicitacao;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosSolicitacao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosSolicitacao {
    ViewDadosSolicitacao viewDadosSolicitacao;
    
    public OuvinteViewDadosSolicitacao (ViewDadosSolicitacao view){
        this.viewDadosSolicitacao = view;
        this.viewDadosSolicitacao.bGravarProducaoAddActionListener(new OuvinteGravarSolicitacao());
        this.viewDadosSolicitacao.bEditarProducaoAddActionListener(new OuvinteEditarSolicitacao());
        this.viewDadosSolicitacao.bExcluirProducaoAddActionListener(new OuvinteExcluirSolicitacao());
    }
    
    class OuvinteGravarSolicitacao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Solicitacao solicitacao = viewDadosSolicitacao.preencherSolicitacao();
            try{
                if(!viewDadosSolicitacao.getErro()){
                    SolicitacaoController solicitacaoController = new SolicitacaoController();
                    solicitacaoController.setSolicitacaoCadastro(solicitacao);
                    solicitacaoController.salvar();
                    //subtraindo a quantidade de produtos depois de feita a solicitação.
                    ProdutoDAO pdao = new ProdutoDAO();
                    Produto produto = pdao.buscarPorCodigo(solicitacao.getProduto().getId());
                    produto.setQuantidade(produto.getQuantidade().subtract(solicitacao.getQuantidade()));
                    pdao.editar(produto);
                    viewDadosSolicitacao.limparCampos();
                }else{
                    viewDadosSolicitacao.setError(false);
                }
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao gravar a solicitação " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteEditarSolicitacao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Solicitacao solicitacao = viewDadosSolicitacao.preencherSolicitacao();
            try{
               if(!viewDadosSolicitacao.getErro()){
                   SolicitacaoController solicitacaoController = new SolicitacaoController();
                   solicitacaoController.setSolicitacaoCadastro(solicitacao);
                   solicitacaoController.editar();
                   solicitacaoController.listar();
               }else{
                   viewDadosSolicitacao.setError(false);
               }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao editar a solicitação " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirSolicitacao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Solicitacao solicitacao = viewDadosSolicitacao.getSolicitacao();
            try{
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Producao abaixo: ");
                mensagem.append("\ncodigo: " + solicitacao.getId());
                mensagem.append("\nQuantidade: " + solicitacao.getQuantidade());
                mensagem.append("\nArmazém: " + solicitacao.getArmazemDistribuicao().getNomeFantasia());
                int resposta = viewDadosSolicitacao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    SolicitacaoController solicitacaoController = new SolicitacaoController();
                    solicitacaoController.setSolicitacaoCadastro(solicitacao);
                    solicitacaoController.excluir();
                    solicitacaoController.listar();
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao excluir a solicitação " + ex.getMessage());
            }
        }
        
    }
}
