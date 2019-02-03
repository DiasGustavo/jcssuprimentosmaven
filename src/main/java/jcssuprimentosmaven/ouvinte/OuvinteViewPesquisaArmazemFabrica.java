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
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaArmazemFabrica;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaArmazemFabrica {

    ViewPesquisaArmazemFabrica viewPesquisaArmazem;
    Jogador jogador;

    public OuvinteViewPesquisaArmazemFabrica(ViewPesquisaArmazemFabrica view, Jogador usuario) {
        this.viewPesquisaArmazem = view;
        this.jogador = usuario;
        viewPesquisaArmazem.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosArmazens(this.jogador));
        viewPesquisaArmazem.bPesquisarAddActionListener(new OuvintePesquisarArmazem());
        viewPesquisaArmazem.bExcluirAddActionListener(new OuvinteExcluirArmazem());
    }

    class OuvintePesquisarTodosArmazens implements ActionListener {

        Jogador jogadorTeste;

        public OuvintePesquisarTodosArmazens(Jogador jogador) {
            this.jogadorTeste = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogadorTeste.getFuncao() == 2) {
                    ArmazemFabricaController armazemController = new ArmazemFabricaController();
                    armazemController.listar();
                    List armazens = armazemController.getListaArmazens();
                    viewPesquisaArmazem.listar(armazens);
                } else {
                    /*ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
                    FabricaController fabricaController = new FabricaController();
                    EmpresaController empresaController = new EmpresaController();
                    List  = empresaController.buscarPorJogador(jogadorTeste);
                    Empresa empresa = (Empresa)empresas.get(0);
                    List fabricas = fabricaController.buscarPorJogador(empresa);
                    Fabrica fabrica = (Fabrica)fabricas.get(0);
                    List armazens = armazemFabricaController.buscarPorFabrica(fabrica);
                    viewPesquisaArmazem.listar(armazens);*/
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
                ArmazemFabricaController armazemController = new ArmazemFabricaController();
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
                ArmazemFabrica armazem = viewPesquisaArmazem.getArmazem();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Armazem abaixo: ");
                mensagem.append("\ncodigo: " + armazem.getId());
                mensagem.append("\nFábrica" + armazem.getFabrica().getNomeFantasia());
                int resposta = viewPesquisaArmazem.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ArmazemFabricaController armazemController = new ArmazemFabricaController();
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
