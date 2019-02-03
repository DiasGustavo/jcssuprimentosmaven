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
import jcssuprimentosmaven.controller.SolicitacaoController;
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaSolicitacao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaSolicitacao {
    ViewPesquisaSolicitacao viewPesquisaSolicitacao;
    
    public OuvinteViewPesquisaSolicitacao(ViewPesquisaSolicitacao view){
        this.viewPesquisaSolicitacao = view;
        this.viewPesquisaSolicitacao.bPesquisarTodosAddActionListener(new OuvintePesquisarTodasSolicitacoes());
        this.viewPesquisaSolicitacao.bPesquisarAddActionListener(new OuvintePesquisarSolicitacao());
        this.viewPesquisaSolicitacao.bExcluirAddActionListener(new OuvinteExcluirSolicitacao());
    }
    
    class OuvintePesquisarTodasSolicitacoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                SolicitacaoController solicitacaoController = new SolicitacaoController();
                solicitacaoController.listar();
                List solicitacoes = solicitacaoController.getListaSolicitacoes();
                viewPesquisaSolicitacao.listar(solicitacoes);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao listar as solicitações " + ex.getMessage());
            }
        }
        
    }
    
    class OuvintePesquisarSolicitacao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                SolicitacaoController solicitacaoController = new SolicitacaoController();
                Long codigo = viewPesquisaSolicitacao.getIdPesquisar();
                List solicitacoes = null;
                solicitacaoController.setCodigo(codigo);
                solicitacaoController.carregarDados();
                solicitacoes.add(solicitacaoController.getSolicitacaoCadastro());
                viewPesquisaSolicitacao.listar(solicitacoes);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar a solicitação " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirSolicitacao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Solicitacao solicitacao = new Solicitacao();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Produção abaixo: ");
                mensagem.append("\ncodigo: " + solicitacao.getId());
                mensagem.append("\nQuantidade: " + solicitacao.getQuantidade());
                mensagem.append("\nMateria: " + solicitacao.getArmazemDistribuicao().getNomeFantasia());
                int resposta = viewPesquisaSolicitacao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    SolicitacaoController solicitacaoController = new SolicitacaoController();
                    solicitacaoController.setSolicitacaoCadastro(solicitacao);
                    solicitacaoController.excluir();
                    List solicitacoes = solicitacaoController.getListaSolicitacoes();
                    viewPesquisaSolicitacao.listar(solicitacoes);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao excluir a solicitação " + ex.getMessage());
            }
        }
        
    }
}
