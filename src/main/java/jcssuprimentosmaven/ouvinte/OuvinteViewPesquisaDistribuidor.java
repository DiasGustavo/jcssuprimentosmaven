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
import jcssuprimentosmaven.controller.ArmazemDistribuicaoController;
import jcssuprimentosmaven.controller.DistribuidorController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueDistribuicaoController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Distribuidor;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaDistribuidor;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaDistribuidor {

    ViewPesquisaDistribuidor viewPesquisaDistribuidor;
    Jogador jogador;

    public OuvinteViewPesquisaDistribuidor(ViewPesquisaDistribuidor view, Jogador usuario) {
        this.jogador = usuario;
        this.viewPesquisaDistribuidor = view;
        viewPesquisaDistribuidor.bPesquisarTodosAddActionListener(new OuvintePesquisarTodasDistribuidores(this.jogador));
        viewPesquisaDistribuidor.bPesquisarAddActionListener(new OuvintePesquisarDistribuidores());
        viewPesquisaDistribuidor.bExcluirAddActionListener(new OuvinteExcluirDistribuidores());
    }

    class OuvintePesquisarTodasDistribuidores implements ActionListener {

        Jogador jogadorTeste;

        public OuvintePesquisarTodasDistribuidores(Jogador usuario) {
            this.jogadorTeste = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogadorTeste.getFuncao() == 2) {
                    DistribuidorController distribuidorController = new DistribuidorController();
                    distribuidorController.listar();
                    List distribuidores = distribuidorController.getListaDistribuidores();
                    viewPesquisaDistribuidor.listar(distribuidores);
                } else {
                    DistribuidorController distribuidorController = new DistribuidorController();
                    EmpresaController empresaController = new EmpresaController();
                    List empresas = empresaController.buscarPorJogador(this.jogadorTeste);
                    Empresa empresa = (Empresa)empresas.get(0);
                    
                    FabricaController fabricaController = new FabricaController();
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    Fabrica fabrica = (Fabrica) fabricas.get(0);
                    
                    ArmazemDistribuicaoController armazemDistribuicaController = new ArmazemDistribuicaoController();
                    List armazens = armazemDistribuicaController.buscarPorFabrica(fabrica);
                    ArmazemDistribuicao armazem = (ArmazemDistribuicao) armazens.get(0);
                    
                    EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();
                    List estoques = estoqueDistribuicaoController.buscarPorArmazem(armazem);
                    EstoqueDistribuicao estoque = (EstoqueDistribuicao) estoques.get(0);
                    
                    List distribuidores = distribuidorController.buscarPorEstoque(estoque);
                    viewPesquisaDistribuidor.listar(distribuidores);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Distribuidor para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarDistribuidores implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DistribuidorController distribuidorController = new DistribuidorController();
                String nome = viewPesquisaDistribuidor.getNomePesquisar();
                List distribuidores;
                distribuidores = distribuidorController.buscarPorNome(nome);
                viewPesquisaDistribuidor.listar(distribuidores);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar as empresas " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirDistribuidores implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Distribuidor distribuidor = viewPesquisaDistribuidor.getDistribuidor();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Distribuidor abaixo: ");
                mensagem.append("\ncodigo: " + distribuidor.getId());
                mensagem.append("\nnome: " + distribuidor.getNomeFantasia());
                int resposta = viewPesquisaDistribuidor.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    DistribuidorController distribuidorController = new DistribuidorController();
                    distribuidorController.setDistribuidorCadastro(distribuidor);
                    distribuidorController.excluir();
                    distribuidorController.listar();
                    List distribuidores = distribuidorController.getListaDistribuidores();
                    viewPesquisaDistribuidor.listar(distribuidores);
                }
            } catch (RuntimeException ex) {

            }
        }

    }

}
