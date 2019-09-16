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
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaEmpresa;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaEmpresa {

    ViewPesquisaEmpresa viewPesquisaEmpresa;
    Jogador jogador;

    public OuvinteViewPesquisaEmpresa(ViewPesquisaEmpresa view, Jogador usuario) {
        this.viewPesquisaEmpresa = view;
        this.jogador = usuario;
        viewPesquisaEmpresa.bPesquisarTodosAddActionListener(new OuvintePesquisarTodasEmpresas(this.jogador));
        viewPesquisaEmpresa.bPesquisarAddActionListener(new OuvintePesquisarEmpresa());
        viewPesquisaEmpresa.bExcluirAddActionListener(new OuvinteExcluirEmpresa());
    }

    class OuvintePesquisarTodasEmpresas implements ActionListener {
        Jogador jogadorTest;
        public OuvintePesquisarTodasEmpresas(Jogador jogador){
            this.jogadorTest=jogador;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (jogador.getFuncao() == 2) {
                    EmpresaController empresaController = new EmpresaController();
                    empresaController.listar();
                    List empresas = empresaController.getListaEmpresas();
                    viewPesquisaEmpresa.listar(empresas);
                }else{
                    EmpresaController empresaController = new EmpresaController();                    
                    List empresas = empresaController.buscarPorJogador(this.jogadorTest);
                    viewPesquisaEmpresa.listar(empresas);
                }
                
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Empresas para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarEmpresa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                EmpresaController empresaController = new EmpresaController();
                String nome = viewPesquisaEmpresa.getNomePesquisar();
                List empresas;
                empresas = empresaController.buscarPorNome(nome);
                viewPesquisaEmpresa.listar(empresas);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar as empresas " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirEmpresa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Empresa empresa = viewPesquisaEmpresa.getEmpresa();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Empresa abaixo: ");
                mensagem.append("\ncodigo: " + empresa.getId());
                mensagem.append("\nnome: " + empresa.getNomeFantasia());
                int resposta = viewPesquisaEmpresa.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    EmpresaController empresaController = new EmpresaController();
                    empresaController.setEmpresaCadastro(empresa);
                    empresaController.excluir();
                    empresaController.listar();
                    List empresas = empresaController.getListaEmpresas();
                    viewPesquisaEmpresa.listar(empresas);
                }
            } catch (RuntimeException ex) {

            }
        }

    }

}
