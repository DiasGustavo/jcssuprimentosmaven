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
import jcssuprimentosmaven.controller.EstoqueSuprimentoController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaEstoqueSuprimento;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaEstoqueSuprimento {

    ViewPesquisaEstoqueSuprimento viewPesquisaEstoqueSuprimento;
    Jogador jogador;

    public OuvinteViewPesquisaEstoqueSuprimento(ViewPesquisaEstoqueSuprimento view, Jogador usuario) {
        this.viewPesquisaEstoqueSuprimento = view;
        this.jogador = usuario;
        viewPesquisaEstoqueSuprimento.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosEstoques(this.jogador));
        viewPesquisaEstoqueSuprimento.bPesquisarAddActionListener(new OuvintePesquisarEstoque());
        viewPesquisaEstoqueSuprimento.bExcluirAddActionListener(new OuvinteExcluirEstoque());
    }

    class OuvintePesquisarTodosEstoques implements ActionListener {

        Jogador jogadorTeste;
        
        public OuvintePesquisarTodosEstoques(Jogador usuario){
            this.jogadorTeste = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                  //se for administrador lista todos os armazens de suprimento
                if (this.jogadorTeste.getFuncao() == 2) {
                    EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                    estoqueSuprimentoController.listar();
                    List estoques = estoqueSuprimentoController.getListaEstoques();
                    viewPesquisaEstoqueSuprimento.listar(estoques);
                } else {
                    //lista o armazem do usuario
                    /*EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                    EmpresaController empresaController = new EmpresaController();
                    List empresas = empresaController.buscarPorJogador(this.jogadorTeste);
                    Empresa empresa = (Empresa)empresas.get(0);
                    List estoques = estoqueSuprimentoController.getListaEstoques();
                    viewPesquisaEstoqueSuprimento.listar(estoques);*/
                    
                    EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                    EmpresaController empresaController = new EmpresaController();
                    List empresas = empresaController.buscarPorJogador(this.jogadorTeste);
                    Empresa empresa = (Empresa)empresas.get(0);
                    
                    FabricaController fabricaController = new FabricaController();
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    Fabrica fabrica = (Fabrica) fabricas.get(0);
                    
                    ArmazemSuprimentoController armazemSuprimentoController = new ArmazemSuprimentoController();
                    List armazensSuprimentos = armazemSuprimentoController.buscarPorFabrica(fabrica);
                    ArmazemSuprimento armazemSuprimento = (ArmazemSuprimento) armazensSuprimentos.get(0);
                    
                    List estoqueSuprimentos = estoqueSuprimentoController.buscarPorArmazem(armazemSuprimento);
                    viewPesquisaEstoqueSuprimento.listar(estoqueSuprimentos);
                            
                            
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
                EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                String nome = viewPesquisaEstoqueSuprimento.getNomePesquisar();
                List estoques;
                //estoques = estoqueSuprimentoController.buscarPorNomeEmpresa(nome);
                //viewPesquisaEstoqueSuprimento.listar(estoques);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar as estoques " + ex.getMessage());
            }
        }

    }
    
    class OuvinteExcluirEstoque implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EstoqueSuprimento estoqueSuprimento = viewPesquisaEstoqueSuprimento.getEstoqueSuprimento();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Fábrica abaixo: ");
            mensagem.append("\ncodigo: " + estoqueSuprimento.getId());
            mensagem.append("\nnome: " + estoqueSuprimento.getDescricao());
            int resposta = viewPesquisaEstoqueSuprimento.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                estoqueSuprimentoController.setEstoqueCadastro(estoqueSuprimento);
                estoqueSuprimentoController.excluir();
                estoqueSuprimentoController.listar();
                List estoques = estoqueSuprimentoController.getListaEstoques();
                viewPesquisaEstoqueSuprimento.listar(estoques);
            }
        }
        
    }

}
