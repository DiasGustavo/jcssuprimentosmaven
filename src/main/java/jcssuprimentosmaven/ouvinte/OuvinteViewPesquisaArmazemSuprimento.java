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
import jcssuprimentosmaven.controller.ArmazemSuprimentoController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaArmazemSuprimento;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaArmazemSuprimento {

    ViewPesquisaArmazemSuprimento viewPesquisaArmazem;
    Jogador jogador;

    public OuvinteViewPesquisaArmazemSuprimento(ViewPesquisaArmazemSuprimento view, Jogador jogador) {
        this.viewPesquisaArmazem = view;
        this.jogador = jogador;
        viewPesquisaArmazem.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosArmazens(this.jogador));
        viewPesquisaArmazem.bPesquisarAddActionListener(new OuvintePesquisarArmazem());
        viewPesquisaArmazem.bExcluirAddActionListener(new OuvinteExcluirArmazem());
    }

    class OuvintePesquisarTodosArmazens implements ActionListener {

        Jogador jogadorTest;

        public OuvintePesquisarTodosArmazens(Jogador jogador) {
            this.jogadorTest = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ArmazemSuprimentoController armazemController = new ArmazemSuprimentoController();
            try {
                //se for administrador lista todos os armazens de suprimento
                if (this.jogadorTest.getFuncao() == 2) {
                    armazemController.listar();
                    List armazens = armazemController.getListaArmazens();
                    viewPesquisaArmazem.listar(armazens);
                } else {
                    //lista o armazem do usuario
                    EmpresaController empresaController = new EmpresaController();
                    FabricaController fabricaController = new FabricaController();
                    Empresa empresa = (Empresa) empresaController.buscarPorJogador(this.jogadorTest).get(0);
                    Fabrica fabrica = (Fabrica) fabricaController.buscarPorEmpresa(empresa).get(0);
                    List armazensSuprimentos = armazemController.buscarPorFabrica(fabrica);
                    viewPesquisaArmazem.listar(armazensSuprimentos);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe armazens para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarArmazem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArmazemSuprimentoController armazemController = new ArmazemSuprimentoController();
                String nome = viewPesquisaArmazem.getNomePesquisar();
                List armazens;
                armazens = armazemController.buscarPorNome(nome);
                viewPesquisaArmazem.listar(armazens);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar os armazens " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirArmazem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArmazemSuprimento armazem = viewPesquisaArmazem.getArmazem();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Armazem abaixo: ");
                mensagem.append("\ncodigo: " + armazem.getId());
                mensagem.append("\nnome: " + armazem.getNomeFantasia());
                int resposta = viewPesquisaArmazem.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ArmazemSuprimentoController armazemController = new ArmazemSuprimentoController();
                    armazemController.setArmazemCadastro(armazem);
                    armazemController.excluir();
                    armazemController.listar();
                    List armazens = armazemController.getListaArmazens();
                    viewPesquisaArmazem.listar(armazens);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao excluir o armazem " + ex.getMessage());
            }
        }

    }
}
