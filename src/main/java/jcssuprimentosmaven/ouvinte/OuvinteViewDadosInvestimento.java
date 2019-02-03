/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.InvestimentoController;
import jcssuprimentosmaven.domain.Investimento;
import jcssuprimentosmaven.view.ViewDadosInvestimento;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosInvestimento {
    private ViewDadosInvestimento viewDadosInvestimento;
    
    public OuvinteViewDadosInvestimento(ViewDadosInvestimento view){
        this.viewDadosInvestimento = view;
        viewDadosInvestimento.bGravarAddActionListener(new OuvinteGravarInvestimento());
        viewDadosInvestimento.bEditarAddActionListener(new OuvinteEditarInvestimento());
        viewDadosInvestimento.bExcluirAddActionListener(new OuvinteExcluirInvestimento());
    }
    
    class OuvinteGravarInvestimento implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Investimento investimento = viewDadosInvestimento.preencherInvestimento();
            if(!viewDadosInvestimento.getErro()){
                InvestimentoController investimentoController = new InvestimentoController();
                investimentoController.setInvestimentoCadastro(investimento);
                investimentoController.salvar();
                viewDadosInvestimento.limparCampos();
            }else{
                viewDadosInvestimento.setError(false);
            }
        }
        
    }
    
    class OuvinteEditarInvestimento implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Investimento investimento = viewDadosInvestimento.preencherInvestimento();
            if(!viewDadosInvestimento.getErro()){
                InvestimentoController investimentoController = new InvestimentoController();
                investimentoController.setInvestimentoCadastro(investimento);
                investimentoController.editar();
                investimentoController.listar();
                
            }else{
                viewDadosInvestimento.setError(false);
            }
        }
        
    }
    
    class OuvinteExcluirInvestimento implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Investimento investimento = viewDadosInvestimento.getInvestimento();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Investimento abaixo: ");
            mensagem.append("\ncodigo: " + investimento.getId());
            mensagem.append("\nnome: " + investimento.getDescricao());
            int resposta = viewDadosInvestimento.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                InvestimentoController investimentoController = new InvestimentoController();
                investimentoController.setInvestimentoCadastro(investimento);
                investimentoController.excluir();
                viewDadosInvestimento.limparCampos();
            }
        }
        
    }
}
