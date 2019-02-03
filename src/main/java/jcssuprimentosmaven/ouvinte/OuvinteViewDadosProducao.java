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
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.controller.ProducaoController;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Producao;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosProducao;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosProducao {
    
    ViewDadosProducao viewDadosProducao;
    
    public OuvinteViewDadosProducao(ViewDadosProducao view) {
        this.viewDadosProducao = view;
        viewDadosProducao.bGravarProducaoAddActionListener(new OuvinteGravarProducao());
        viewDadosProducao.bEditarProducaoAddActionListener(new OuvinteEditarProducao());
        viewDadosProducao.bExcluirProducaoAddActionListener(new OuvinteExcluirProducao());
    }
    
    class OuvinteGravarProducao implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Producao producao = viewDadosProducao.preencherProducao();
            if (!viewDadosProducao.getErro()) {
                ProducaoController producaoController = new ProducaoController();
                ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
                producaoController.setProducaoCadastro(producao);
                List materias = viewDadosProducao.getMatA();
                ArmazemFabrica armazem = (ArmazemFabrica) materias.get(0);
                BigDecimal qtdA = new BigDecimal(viewDadosProducao.getJtxQtdA().getText());
                BigDecimal qtdB = new BigDecimal(viewDadosProducao.getJtxQtdB().getText());
                BigDecimal qtdArmazem = armazem.getQuantidade();
                
                if (qtdArmazem.compareTo(qtdA) ==  1 || qtdArmazem.compareTo(qtdA) == 0) {
                    armazem.setQuantidade(armazem.getQuantidade().subtract(qtdA));
                    armazemFabricaController.setArmazemCadastro(armazem);                    
                    armazemFabricaController.editar();
                    
                    armazem = (ArmazemFabrica) materias.get(1);
                    qtdArmazem = armazem.getQuantidade();
                    if (qtdArmazem.compareTo(qtdB) == 1 || qtdArmazem.compareTo(qtdB) == 0) {
                        armazem.setQuantidade(armazem.getQuantidade().subtract(qtdB));
                        armazemFabricaController.setArmazemCadastro(armazem);
                        armazemFabricaController.editar();
                        //gravar a produção
                        producaoController.salvar();
                        viewDadosProducao.limparCampos();
                    }
                } else {
                    ViewUtil.addMsgErro("Valores de quantidades maiores do que o estoque da fábrica.");
                }
                
            } else {
                viewDadosProducao.setError(false);
            }
        }
        
    }
    
    class OuvinteEditarProducao implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Producao producao = viewDadosProducao.preencherProducao();
            if (!viewDadosProducao.getErro()) {
                ProducaoController producaoController = new ProducaoController();
                producaoController.setProducaoCadastro(producao);
                producaoController.editar();
                producaoController.listar();
            } else {
                viewDadosProducao.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirProducao implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Producao producao = viewDadosProducao.getProducao();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Producao abaixo: ");
            mensagem.append("\ncodigo: " + producao.getId());
            mensagem.append("\nArmazém: " + producao.getArmazemFabrica().getFabrica().getNomeFantasia());
            BigDecimal produtos = producao.getQuantidadeMateriaA().multiply(new BigDecimal("0.4")).add(producao.getQuantidadeMateriaB().multiply(new BigDecimal("0.6")));
            mensagem.append("\nProdutos: " + produtos.toEngineeringString());
            int resposta = viewDadosProducao.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                ProducaoController producaoController = new ProducaoController();
                producaoController.setProducaoCadastro(producao);
                producaoController.excluir();
                viewDadosProducao.limparCampos();
            }
        }
        
    }
}
