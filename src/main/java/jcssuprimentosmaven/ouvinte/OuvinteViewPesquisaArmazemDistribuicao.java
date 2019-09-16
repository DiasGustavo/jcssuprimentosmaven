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
<<<<<<< HEAD
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
=======
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaArmazemDistribuicao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaArmazemDistribuicao {

    ViewPesquisaArmazemDistribuicao viewPesquisaArmazem;
<<<<<<< HEAD
    Jogador jogador;

    public OuvinteViewPesquisaArmazemDistribuicao(ViewPesquisaArmazemDistribuicao view, Jogador jogador) {
        this.viewPesquisaArmazem = view;
        this.jogador = jogador;
        viewPesquisaArmazem.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosArmazens(this.jogador));
=======

    public OuvinteViewPesquisaArmazemDistribuicao(ViewPesquisaArmazemDistribuicao view) {
        this.viewPesquisaArmazem = view;
        viewPesquisaArmazem.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosArmazens());
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        viewPesquisaArmazem.bPesquisarAddActionListener(new OuvintePesquisarArmazem());
        viewPesquisaArmazem.bExcluirAddActionListener(new OuvinteExcluirArmazem());
    }

    class OuvintePesquisarTodosArmazens implements ActionListener {

<<<<<<< HEAD
        Jogador jogador;

        OuvintePesquisarTodosArmazens(Jogador jogador) {
            this.jogador = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogador.getFuncao() == 2) {
                    ArmazemDistribuicaoController armazemController = new ArmazemDistribuicaoController();
                    armazemController.listar();
                    List armazens = armazemController.getListaArmazens();
                    viewPesquisaArmazem.listar(armazens);
                } else {
                    ArmazemDistribuicaoController armazemDistribuicaoController = new ArmazemDistribuicaoController();
                    FabricaController fabricaController = new FabricaController();
                    EmpresaController empresaController = new EmpresaController();
                    List empresas  = empresaController.buscarPorJogador(jogador);
                    Empresa empresa = (Empresa)empresas.get(0);
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    Fabrica fabrica = (Fabrica)fabricas.get(0);
                    List listaArmazens = armazemDistribuicaoController.buscarPorFabrica(fabrica);
                    viewPesquisaArmazem.listar(listaArmazens);
                }
=======
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArmazemDistribuicaoController armazemController = new ArmazemDistribuicaoController();
                armazemController.listar();
                List armazens = armazemController.getListaArmazens();
                viewPesquisaArmazem.listar(armazens);
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe armazens para listar " + ex.getMessage());
            }
        }

    }
<<<<<<< HEAD

    class OuvintePesquisarArmazem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
=======
    
    class OuvintePesquisarArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                ArmazemDistribuicaoController armazemController = new ArmazemDistribuicaoController();
                String nome = viewPesquisaArmazem.getNomePesquisar();
                List armazens;
                armazens = armazemController.buscarPorNome(nome);
                viewPesquisaArmazem.listar(armazens);
<<<<<<< HEAD
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar os armazens " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirArmazem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArmazemDistribuicao armazem = viewPesquisaArmazem.getArmazem();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Armazem abaixo: ");
=======
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar os armazens " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirArmazem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                ArmazemDistribuicao armazem = viewPesquisaArmazem.getArmazem();
                 StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Armazem abaixo: ");
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                mensagem.append("\ncodigo: " + armazem.getId());
                mensagem.append("\nnome: " + armazem.getNomeFantasia());
                int resposta = viewPesquisaArmazem.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    ArmazemDistribuicaoController armazemController = new ArmazemDistribuicaoController();
                    armazemController.setArmazemCadastro(armazem);
                    armazemController.excluir();
                    armazemController.listar();
                    List armazens = armazemController.getListaArmazens();
                    viewPesquisaArmazem.listar(armazens);
                }
<<<<<<< HEAD
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao excluir o armazem " + ex.getMessage());
            }
        }

=======
            }catch(RuntimeException ex){
               ViewUtil.addMsgErro("Erro ao excluir o armazem " + ex.getMessage()); 
            }
        }
        
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    }
}
