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
<<<<<<< HEAD
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueDistribuicaoController;
import jcssuprimentosmaven.controller.SolicitacaoController;
import jcssuprimentosmaven.dao.ArmazemDistribuicaoDAO;
import jcssuprimentosmaven.dao.EmpresaDAO;
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
=======
import jcssuprimentosmaven.controller.SolicitacaoController;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaSolicitacao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaSolicitacao {
<<<<<<< HEAD

    ViewPesquisaSolicitacao viewPesquisaSolicitacao;
    Jogador jogador;

    public OuvinteViewPesquisaSolicitacao(ViewPesquisaSolicitacao view, Jogador jogador) {
        this.viewPesquisaSolicitacao = view;
        this.jogador = jogador;
        this.viewPesquisaSolicitacao.bPesquisarTodosAddActionListener(new OuvintePesquisarTodasSolicitacoes(this.jogador));
        this.viewPesquisaSolicitacao.bPesquisarAddActionListener(new OuvintePesquisarSolicitacao());
        this.viewPesquisaSolicitacao.bExcluirAddActionListener(new OuvinteExcluirSolicitacao());
        this.viewPesquisaSolicitacao.bEntregarSolicitacaoListener(new OuvinteEntregarSolicitacao(this.jogador));
    }

    class OuvintePesquisarTodasSolicitacoes implements ActionListener {

        Jogador jogador;

        public OuvintePesquisarTodasSolicitacoes(Jogador jogador) {
            this.jogador = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                SolicitacaoController solicitacaoController = new SolicitacaoController();
                if (this.jogador.getFuncao() == 2) {
                    
                    solicitacaoController.listar();
                    List solicitacoes = solicitacaoController.getListaSolicitacoes();
                    viewPesquisaSolicitacao.listar(solicitacoes);
                }else{
                    EmpresaDAO edao = new EmpresaDAO();
                    FabricaDAO fdao = new FabricaDAO();
                    ArmazemDistribuicaoDAO add = new ArmazemDistribuicaoDAO();
                    Empresa empresa = (Empresa) edao.buscarPorJogador(this.jogador).get(0);
                    Fabrica fabrica = (Fabrica) fdao.buscarPorEmpresa(empresa).get(0);
                    ArmazemDistribuicao armazemDistribuicao = (ArmazemDistribuicao) add.buscarPorFabrica(fabrica).get(0);
                    viewPesquisaSolicitacao.listar(solicitacaoController.buscarPorArmazemStatus(armazemDistribuicao, "Pendente"));
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao listar as solicitações " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarSolicitacao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
=======
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
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                SolicitacaoController solicitacaoController = new SolicitacaoController();
                Long codigo = viewPesquisaSolicitacao.getIdPesquisar();
                List solicitacoes = null;
                solicitacaoController.setCodigo(codigo);
                solicitacaoController.carregarDados();
                solicitacoes.add(solicitacaoController.getSolicitacaoCadastro());
                viewPesquisaSolicitacao.listar(solicitacoes);
<<<<<<< HEAD
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar a solicitação " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirSolicitacao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Solicitacao solicitacao = viewPesquisaSolicitacao.getSolicitacao();
=======
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
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Produção abaixo: ");
                mensagem.append("\ncodigo: " + solicitacao.getId());
                mensagem.append("\nQuantidade: " + solicitacao.getQuantidade());
                mensagem.append("\nMateria: " + solicitacao.getArmazemDistribuicao().getNomeFantasia());
                int resposta = viewPesquisaSolicitacao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    SolicitacaoController solicitacaoController = new SolicitacaoController();
                    solicitacaoController.setSolicitacaoCadastro(solicitacao);
                    solicitacaoController.excluir();
<<<<<<< HEAD
                    solicitacaoController.listar();
                    List solicitacoes = solicitacaoController.getListaSolicitacoes();
                    viewPesquisaSolicitacao.listar(solicitacoes);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao excluir a solicitação " + ex.getMessage());
            }
        }

    }
    
    class OuvinteEntregarSolicitacao implements ActionListener{
        
        Jogador jogador;
        
        OuvinteEntregarSolicitacao(Jogador jogador){
            this.jogador = jogador;
        }
            
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Solicitacao solicitacao = viewPesquisaSolicitacao.getSolicitacao();
                SolicitacaoController solicitacaoController = new SolicitacaoController();
                EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();
                //transportar os produtos para o estoque de distribuição
                solicitacao.setStatus("Entregue");
                solicitacaoController.setSolicitacaoCadastro(solicitacao);
                solicitacaoController.editar();
                solicitacaoController.listar();
                viewPesquisaSolicitacao.listar(solicitacaoController.getListaSolicitacoes());
                
                EstoqueDistribuicao estoqueArmazemDistribuicao = (EstoqueDistribuicao)
                        estoqueDistribuicaoController.buscarPorArmazem(solicitacao.getArmazemDistribuicao()).get(0);
                
                estoqueArmazemDistribuicao.setQuantidade(estoqueArmazemDistribuicao.getQuantidade().add(solicitacao.getQuantidade()));
                estoqueDistribuicaoController.setEstoqueCadastro(estoqueArmazemDistribuicao);
                estoqueDistribuicaoController.editar();
                
                //Retirada do caixa da empresa
                EmpresaController empresaController = new EmpresaController();
                List empresas = empresaController.buscarPorJogador(jogador);
                Empresa empresa = (Empresa) empresas.get(0);
                empresa.setCapitalAtual(empresa.getCapitalAtual().subtract(solicitacao.getValor()));
                empresaController.setEmpresaCadastro(empresa);
                empresaController.editar();
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao entregar a solicatação " + ex.getMessage());
=======
                    List solicitacoes = solicitacaoController.getListaSolicitacoes();
                    viewPesquisaSolicitacao.listar(solicitacoes);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao excluir a solicitação " + ex.getMessage());
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
            }
        }
        
    }
}
