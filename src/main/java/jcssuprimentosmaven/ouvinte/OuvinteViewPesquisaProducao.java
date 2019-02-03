/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.ProducaoController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Producao;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaProducao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaProducao {

    ViewPesquisaProducao viewPesquisaProducao;
    Jogador jogador;

    public OuvinteViewPesquisaProducao(ViewPesquisaProducao view, Jogador usuario) {
        this.viewPesquisaProducao = view;
        this.jogador = usuario;
        viewPesquisaProducao.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosPesquisa(this.jogador));
        viewPesquisaProducao.bPesquisarAddActionListener(new OuvintePesquisarProducao());
        viewPesquisaProducao.bExcluirAddActionListener(new OuvinteExcluirProducao());
    }

    class OuvintePesquisarTodosPesquisa implements ActionListener {

        Jogador jogadorTeste;
        
        public OuvintePesquisarTodosPesquisa(Jogador usuario){
            this.jogadorTeste = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogadorTeste.getFuncao() == 2) {
                    ProducaoController producaoController = new ProducaoController();
                    producaoController.listar();
                    List producoes = producaoController.getListaProducoes();
                    viewPesquisaProducao.listar(producoes);
                } else {
                    ProducaoController producaoController = new ProducaoController();
                    FabricaController fabricaController = new FabricaController();
                    EmpresaController empresaController = new EmpresaController();
                    List empresas = empresaController.buscarPorJogador(this.jogadorTeste);
                    Empresa empresa = (Empresa)empresas.get(0);
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    Fabrica fabrica = (Fabrica) fabricas.get(0);
                    List producoes = producaoController.buscarPorFabrica(fabrica);
                    viewPesquisaProducao.listar(producoes);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe prouções para listar " + ex.getMessage());
            }
        }
    }

    class OuvintePesquisarProducao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ProducaoController producaoController = new ProducaoController();
                String nome = viewPesquisaProducao.getNomePesquisar();
                List producoes = null;
                //producoes = producaoController.buscarPorFabrica(nome);
                viewPesquisaProducao.listar(producoes);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar os produções " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirProducao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Producao producao = viewPesquisaProducao.getProducao();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Produção abaixo: ");
                mensagem.append("\ncodigo: " + producao.getId());
                mensagem.append("\nArmazém: " + producao.getArmazemFabrica().getFabrica().getNomeFantasia()); 
                BigDecimal produtos = producao.getQuantidadeMateriaA().multiply(new BigDecimal("0.4")).add(producao.getQuantidadeMateriaB().multiply(new BigDecimal("0.6")));
                mensagem.append("\nProdutos: " + produtos.toEngineeringString());
                int resposta = viewPesquisaProducao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ProducaoController producaoController = new ProducaoController();
                    producaoController.setProducaoCadastro(producao);
                    producaoController.excluir();
                    producaoController.listar();
                    List producoes = producaoController.getListaProducoes();
                    viewPesquisaProducao.listar(producoes);
                }
            } catch (RuntimeException ex) {

            }
        }

    }
}
