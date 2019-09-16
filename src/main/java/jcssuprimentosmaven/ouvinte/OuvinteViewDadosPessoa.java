/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.PessoaController;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Pessoa;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosPessoa;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosPessoa {
    ViewDadosPessoa viewDadosPessoa;
    Jogador jogador;
    
    public OuvinteViewDadosPessoa(ViewDadosPessoa view){
        this.viewDadosPessoa = view;
        //this.jogador = jogador;
        
        viewDadosPessoa.bGravarAddActionListener(new OuvinteGravarPessoa());
        viewDadosPessoa.bEditarAddActionListener(new OuvinteEditarPessoa());
        viewDadosPessoa.bExcluirAddActionListener(new OuvinteExcluirPessoa());
    }
    
    class OuvinteGravarPessoa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Pessoa pessoa = viewDadosPessoa.preencherPessoa();
            PessoaController pcontroller = new PessoaController();
            pcontroller.setPessoaCadastro(pessoa);
            pcontroller.salvar();
        }
        
    }
    
    class OuvinteEditarPessoa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Pessoa pessoa = viewDadosPessoa.preencherPessoa();
            PessoaController pcontroller = new PessoaController();
            pcontroller.setPessoaCadastro(pessoa);
            pcontroller.editar();
        }
        
    }
    
    class OuvinteExcluirPessoa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           Pessoa pessoa = viewDadosPessoa.getPessoa();
            try{
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Pessoa abaixo: ");
                mensagem.append("\ncodigo: " + pessoa.getId());
                mensagem.append("\nNome: " + pessoa.getNome());
                mensagem.append("\nE-mail: " + pessoa.getEmail());
                int resposta = viewDadosPessoa.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                   PessoaController pcontroller = new PessoaController();
                   pcontroller.setPessoaCadastro(pessoa);
                   pcontroller.excluir();                   
                }
            
            }catch(RuntimeException ex){
                   ViewUtil.addMsgErro("Ocorreu erro ao excluir o pedido " + ex.getMessage()); 
            }
        
        }
    }
}
