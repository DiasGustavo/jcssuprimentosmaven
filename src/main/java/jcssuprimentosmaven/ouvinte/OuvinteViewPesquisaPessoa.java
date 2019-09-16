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
import jcssuprimentosmaven.controller.PessoaController;
import jcssuprimentosmaven.domain.Pessoa;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaPessoa;


/**
 * Data de criação: 01/09/2019
 * Data da última modificação: 01/09/2019
 * @author Gustavo
 * @version 1.0
 * Modificação: criação inicial da classe
 */
public class OuvinteViewPesquisaPessoa {
    ViewPesquisaPessoa viewPesquisaPessoa;
    
    public OuvinteViewPesquisaPessoa(ViewPesquisaPessoa view){
        this.viewPesquisaPessoa = view;
        this.viewPesquisaPessoa.bPesquisarTodosAddActionListener(new OuvintePesquisarTodosProdutos());
        this.viewPesquisaPessoa.bPesquisarAddActionListener(new OuvintePesquisaPessoa());
        this.viewPesquisaPessoa.bExcluirAddActionListener(new OuvinteExcluirProduto());
        
    }
    
    class OuvintePesquisarTodosProdutos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                PessoaController pessoaController = new PessoaController();
                pessoaController.listar();
                List pessoas = pessoaController.getListaPessoas();
                viewPesquisaPessoa.listar(pessoas);
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao listar as pessoas " + ex.getMessage());
            }
        }
        
    }
    
    class OuvintePesquisaPessoa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                PessoaController pessoaController = new PessoaController();
                String matricula = viewPesquisaPessoa.getMatricula();
                List pessoas;
                pessoaController.setMatricula(matricula);
                pessoaController.buscarPorMatricula();
                pessoas = pessoaController.getListaPessoas();
                viewPesquisaPessoa.listar(pessoas);
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Erro ao pesquisar a pessoa " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirProduto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Pessoa pessoa = viewPesquisaPessoa.getPessoa();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da pessoa abaixo: ");
                mensagem.append("\ncodigo: " + pessoa.getId());
                mensagem.append("\nNome: " + pessoa.getNome());
                mensagem.append("\nMatrícula: " + pessoa.getMatricula());
                int resposta = viewPesquisaPessoa.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    PessoaController pessoaController = new PessoaController();
                    pessoaController.setPessoaCadastro(pessoa);
                    pessoaController.excluir();
                    pessoaController.listar();
                    List pessoas = pessoaController.getListaPessoas();
                    viewPesquisaPessoa.listar(pessoas);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao excluir a pessoa " + ex.getMessage());
            }
        }
        
    }    
}
