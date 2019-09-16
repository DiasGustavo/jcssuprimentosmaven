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
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaFabrica;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaFabrica {

    ViewPesquisaFabrica viewPesquisaFabrica;
    Jogador jogador;

    public OuvinteViewPesquisaFabrica(ViewPesquisaFabrica view, Jogador usuario) {
        this.viewPesquisaFabrica = view;
        this.jogador = usuario;
        viewPesquisaFabrica.bPesquisarTodosAddActionListener(new OuvintePesquisarTodasFabricas(this.jogador));
        viewPesquisaFabrica.bPesquisarAddActionListener(new OuvintePesquisarFabrica());
        viewPesquisaFabrica.bExcluirAddActionListener(new OuvinteExcluirFabrica());
    }

    class OuvintePesquisarTodasFabricas implements ActionListener {

        Jogador usuarioTest;

        public OuvintePesquisarTodasFabricas(Jogador jogador) {
            this.usuarioTest = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.usuarioTest.getFuncao() == 2) {
                    FabricaController fabricaController = new FabricaController();
                    fabricaController.listar();
                    List fabricas = fabricaController.getListaArmazens();
                    viewPesquisaFabrica.listar(fabricas);
                } else {
                    FabricaController fabricaController = new FabricaController();
                    EmpresaController empresaController = new EmpresaController();
                    List empresas = empresaController.buscarPorJogador(usuarioTest);
                    Empresa empresa = (Empresa) empresas.get(0);
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    viewPesquisaFabrica.listar(fabricas);
                }

            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Fabricas para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarFabrica implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FabricaController fabricaController = new FabricaController();
                String nome = viewPesquisaFabrica.getNomePesquisar();
                List fabricas;
                fabricas = fabricaController.buscarPorNome(nome);
                viewPesquisaFabrica.listar(fabricas);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar as fábricas " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirFabrica implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Fabrica fabrica = viewPesquisaFabrica.getFabrica();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Fábrica abaixo: ");
            mensagem.append("\ncodigo: " + fabrica.getId());
            mensagem.append("\nnome: " + fabrica.getNomeFantasia());
            int resposta = viewPesquisaFabrica.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                FabricaController fabricaController = new FabricaController();
                fabricaController.setArmazemCadastro(fabrica);
                fabricaController.excluir();
                fabricaController.listar();
                List fabricas = fabricaController.getListaArmazens();
                viewPesquisaFabrica.listar(fabricas);
            }
        }

    }
}
