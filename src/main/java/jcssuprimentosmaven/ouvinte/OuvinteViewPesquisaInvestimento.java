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
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.InvestimentoController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaInvestimento;
import jcssuprimentosmaven.domain.Investimento;
import jcssuprimentosmaven.domain.Jogador;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaInvestimento {

    ViewPesquisaInvestimento viewPesquisaInvestimento;
    Jogador jogador;

    public OuvinteViewPesquisaInvestimento(ViewPesquisaInvestimento view, Jogador usuario) {
        this.viewPesquisaInvestimento = view;
        this.jogador = usuario;
        viewPesquisaInvestimento.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosInvestimentos(this.jogador));
        viewPesquisaInvestimento.bPesquisarAddActionListener(new OuvintePesquisarInvestimento());
        viewPesquisaInvestimento.bExcluirAddActionListener(new OuvinteExcluirInvestimento());
    }

    class OuvintePesquisarTodosInvestimentos implements ActionListener {

        Jogador jogadorTeste;
        public OuvintePesquisarTodosInvestimentos(Jogador usuario){
            this.jogadorTeste = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogadorTeste.getFuncao() == 2) {
                    InvestimentoController investimentoController = new InvestimentoController();
                    investimentoController.listar();
                    List investimentos = investimentoController.getListaInvestimentos();
                    viewPesquisaInvestimento.listar(investimentos);
                } else {
                    InvestimentoController investimentoController = new InvestimentoController();
                    FabricaController fabricaController = new FabricaController();
                    /*List fabricas = fabricaController.buscarPorJogador(this.jogadorTeste);
                    Fabrica fabrica = (Fabrica) fabricas.get(0);
                    List investimentos = investimentoController.buscarPorFabrica(fabrica);
                    viewPesquisaInvestimento.listar(investimentos);*/
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Investimentos para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarInvestimento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                InvestimentoController investimentoController = new InvestimentoController();
                String nome = viewPesquisaInvestimento.getNomePesquisar();
                List investimentos;
                investimentos = investimentoController.buscarPorNome(nome);
                viewPesquisaInvestimento.listar(investimentos);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar o investimento " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirInvestimento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Investimento investimento = viewPesquisaInvestimento.getInvestimento();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Investimento abaixo: ");
                mensagem.append("\ncodigo: " + investimento.getId());
                mensagem.append("\nnome: " + investimento.getDescricao());
                int resposta = viewPesquisaInvestimento.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    InvestimentoController investimentoController = new InvestimentoController();
                    investimentoController.setInvestimentoCadastro(investimento);
                    investimentoController.excluir();
                    investimentoController.listar();
                    List investimentos = investimentoController.getListaInvestimentos();
                    viewPesquisaInvestimento.listar(investimentos);
                }
            } catch (RuntimeException ex) {

            }
        }

    }
}
