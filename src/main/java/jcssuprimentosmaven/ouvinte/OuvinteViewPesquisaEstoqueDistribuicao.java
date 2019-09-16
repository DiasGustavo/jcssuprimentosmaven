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
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueDistribuicaoController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.ProdutoController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaEstoqueDistribuicao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaEstoqueDistribuicao {

    ViewPesquisaEstoqueDistribuicao viewPesquisaEstoqueDistribuicao;
    Jogador jogador;

    public OuvinteViewPesquisaEstoqueDistribuicao(ViewPesquisaEstoqueDistribuicao view, Jogador usuario) {
        this.viewPesquisaEstoqueDistribuicao = view;
        this.jogador = usuario;
        viewPesquisaEstoqueDistribuicao.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosEstoques(this.jogador));
        viewPesquisaEstoqueDistribuicao.bPesquisarAddActionListener(new OuvintePesquisarEstoque());
        viewPesquisaEstoqueDistribuicao.bExcluirAddActionListener(new OuvinteExcluirEstoque());
    }

    class OuvintePesquisarTodosEstoques implements ActionListener {

        Jogador jogadorTeste;

        public OuvintePesquisarTodosEstoques(Jogador usuario) {
            this.jogadorTeste = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogadorTeste.getFuncao() == 2) {
                    EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();
                    estoqueDistribuicaoController.listar();
                    List estoques = estoqueDistribuicaoController.getListaEstoques();
                    viewPesquisaEstoqueDistribuicao.listar(estoques);
                } else {
                    EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();

                    EmpresaController empresaController = new EmpresaController();
                    List empresas = empresaController.buscarPorJogador(this.jogadorTeste);
                    Empresa empresa = (Empresa) empresas.get(0);

                    FabricaController fabricaController = new FabricaController();
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    Fabrica fabrica = (Fabrica) fabricas.get(0);

                    ProdutoController produtoController = new ProdutoController();
                    List produtos = produtoController.buscarPorFabrica(fabrica);
                    Produto produto = (Produto) produtos.get(0);

                    List estoques = estoqueDistribuicaoController.buscarPorProduto(produto);

                    viewPesquisaEstoqueDistribuicao.listar(estoques);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Estoques para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarEstoque implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();
                String nome = viewPesquisaEstoqueDistribuicao.getNomePesquisar();
                List estoques;
                //estoques = estoqueDistribuicaoController.buscarPorProduto(nome);
                //viewPesquisaEstoqueDistribuicao.listar(estoques);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar as estoques " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirEstoque implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueDistribuicao estoqueDistribuicao = viewPesquisaEstoqueDistribuicao.getEstoqueDistribuicao();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Fábrica abaixo: ");
            mensagem.append("\ncodigo: " + estoqueDistribuicao.getId());
            mensagem.append("\nnome: " + estoqueDistribuicao.getDescricao());
            int resposta = viewPesquisaEstoqueDistribuicao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                EstoqueDistribuicaoController distribuicaoController = new EstoqueDistribuicaoController();
                distribuicaoController.setEstoqueCadastro(estoqueDistribuicao);
                distribuicaoController.excluir();
                distribuicaoController.listar();
                List estoques = distribuicaoController.getListaEstoques();
                viewPesquisaEstoqueDistribuicao.listar(estoques);
            }
        }

    }

}
