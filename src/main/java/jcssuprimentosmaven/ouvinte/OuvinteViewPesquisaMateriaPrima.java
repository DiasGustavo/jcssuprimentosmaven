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
import jcssuprimentosmaven.controller.FornecedorController;
import jcssuprimentosmaven.controller.MateriaPrimaController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaMateriaPrima;
import jcssuprimentosmaven.domain.MateriaPrima;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaMateriaPrima {

    ViewPesquisaMateriaPrima viewPesquisaMateriaPrima;
    Jogador usuario;

    public OuvinteViewPesquisaMateriaPrima(ViewPesquisaMateriaPrima view, Jogador jogador) {
        this.viewPesquisaMateriaPrima = view;
        this.usuario = jogador;
        viewPesquisaMateriaPrima.bPesquisarTodosAddActionListener(new OuvintePesquisarTodos(this.usuario));
        viewPesquisaMateriaPrima.bPesquisarAddActionListener(new OuvintePesquisarMateriaPrima());
        viewPesquisaMateriaPrima.bExcluirAddActionListener(new OuvinteExcluirMateriaPrima());
    }

    class OuvintePesquisarTodos implements ActionListener {

        Jogador jogadorTest;
        
        public OuvintePesquisarTodos(Jogador usuario){
            this.jogadorTest = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //administrador
                if (jogadorTest.getFuncao() == 2) {
                    MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                    materiaPrimaController.listar();
                    List materias = materiaPrimaController.getListaMateriaPrimas();
                    viewPesquisaMateriaPrima.listar(materias);
                }else{
                    MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                    List materias = materiaPrimaController.buscarPorStatus("Ativo");
                    viewPesquisaMateriaPrima.listar(materias);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Materia Prima para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarMateriaPrima implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                String nome = viewPesquisaMateriaPrima.getNomePesquisar();
                List materias;
                materias = materiaPrimaController.buscarPorNome(nome);
                viewPesquisaMateriaPrima.listar(materias);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar a Materia Prima " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirMateriaPrima implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MateriaPrima materia = viewPesquisaMateriaPrima.getMateriaPrima();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Materia Prima abaixo: ");
                mensagem.append("\ncodigo: " + materia.getId());
                mensagem.append("\nnome: " + materia.getDescricao());
                int resposta = viewPesquisaMateriaPrima.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                    materiaPrimaController.setMateriaPrimaCadastro(materia);
                    materiaPrimaController.excluir();
                    materiaPrimaController.listar();
                    List materias = materiaPrimaController.getListaMateriaPrimas();
                    viewPesquisaMateriaPrima.listar(materias);
                }

            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao exlcuir a Materia Prima");
            }
        }

    }
}
