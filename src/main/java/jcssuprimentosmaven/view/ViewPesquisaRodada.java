/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosRodada;

import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaRodada extends javax.swing.JInternalFrame {

    private ViewDadosRodada viewDadosRodada;
    private List rodadas;

    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaRodada() {
        initComponents();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void abrirViewDadosRodada(Rodada rodada, String acao) {
        //if (viewDadosRodada == null) {
            viewDadosRodada = new ViewDadosRodada();
            OuvinteViewDadosRodada ouvinte = new OuvinteViewDadosRodada(viewDadosRodada);
            viewDadosRodada.setAcao(acao);            
            this.getParent().add(viewDadosRodada);
            viewDadosRodada.setPosicao();
       // }
        if (acao.equals("novo")) {
            viewDadosRodada.bEditar.setEnabled(false);
            viewDadosRodada.bExcluir.setEnabled(false);
            viewDadosRodada.bGravar.setEnabled(true);
            viewDadosRodada.bNovo.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosRodada.bGravar.setEnabled(false);
            viewDadosRodada.bExcluir.setEnabled(false);
            viewDadosRodada.bEditar.setEnabled(true);
            viewDadosRodada.bNovo.setEnabled(false);
        }
        if (acao.equals("excluir")) {
            viewDadosRodada.bExcluir.setEnabled(true);
            viewDadosRodada.bEditar.setEnabled(false);
            viewDadosRodada.bGravar.setEnabled(false);
            viewDadosRodada.bNovo.setEnabled(false);
        }

        viewDadosRodada.setRodada(rodada);
        viewDadosRodada.exibirRodada(viewDadosRodada.getRodada());
        //viewDadosJuiz.preencherJuiz();
        viewDadosRodada.setVisible(true);
        try {
            viewDadosRodada.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a ViewDadosJogador");
        }
    }

    public void listar(List rodadas) {
        try {
            this.rodadas = rodadas;
            DefaultTableModel model = (DefaultTableModel) tRodadas.getModel();
            this.removerLinhasDaTabela(model);

            Iterator resultado = rodadas.iterator();

            while (resultado.hasNext()) {
                Rodada rodadaTemp = (Rodada) resultado.next();
                Long id = rodadaTemp.getId();
                String demanda = rodadaTemp.getDemanda();
                Date dataInicio = rodadaTemp.getDataInicio();
                Date dataFim = rodadaTemp.getDataFim();     
                

                Object[] linha = {id,demanda, dataInicio,dataFim};
                model.addRow(linha);
            }

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar as rodadas " + ex.getMessage());
        }
    }

    private void removerLinhasDaTabela(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            int ultimaLinha = model.getRowCount() - 1;
            model.removeRow(ultimaLinha);
        }
    }

    
    public Rodada getRodada() {
        Rodada rodadaTemp = null;
        try {

            int linhaSelecionada = tRodadas.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            rodadaTemp = (Rodada) this.rodadas.get(linhaSelecionada);

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar o Rodada " + ex.getMessage());
        }
        return rodadaTemp;
    }

    public void bExcluirAddActionListener(ActionListener ouvinte) {
        bExcluir.addActionListener(ouvinte);
    }

    public void bPesquisarTodosAddActionListener(ActionListener ouvinte) {
        bPesquisaTodos.addActionListener(ouvinte);
    }
    
    public void bFinalizarAddActionListener (ActionListener ouvinte){
        bFinalizar.addActionListener(ouvinte);
    }

    public int pedirConfirmacao(String mensagem, String titulo, int tipo) {
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, tipo);
        return resposta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bNovo = new javax.swing.JButton();
        spListaJuizes = new javax.swing.JScrollPane();
        tRodadas = new javax.swing.JTable();
        bAlterar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bPesquisaTodos = new javax.swing.JButton();
        bFinalizar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Pesquisa - Rodada");

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });

        tRodadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Demanda", "Data Início", "Data Fim"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tRodadas.getTableHeader().setReorderingAllowed(false);
        spListaJuizes.setViewportView(tRodadas);

        bAlterar.setText("Alterar");
        bAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlterarActionPerformed(evt);
            }
        });

        bExcluir.setText("Excluir");

        bPesquisaTodos.setText("Pesquisar Todos");

        bFinalizar.setText("Finalizar Rodada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(583, Short.MAX_VALUE)
                        .addComponent(bNovo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bFinalizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bPesquisaTodos)
                                .addGap(32, 32, 32)
                                .addComponent(bAlterar)
                                .addGap(41, 41, 41)
                                .addComponent(bExcluir))
                            .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAlterar)
                    .addComponent(bExcluir)
                    .addComponent(bPesquisaTodos)
                    .addComponent(bFinalizar))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        Rodada rodada = new Rodada();
        this.abrirViewDadosRodada(rodada, "novo");
    }//GEN-LAST:event_bNovoActionPerformed

    private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed
        // TODO add your handling code here:
        Rodada rodada = null;
        try {
            rodada = this.getRodada();
            this.abrirViewDadosRodada(rodada, "editar");

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Não foi possível carregar os dados da Rodada " + ex.getMessage());
        }
    }//GEN-LAST:event_bAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterar;
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bFinalizar;
    private javax.swing.JButton bNovo;
    private javax.swing.JButton bPesquisaTodos;
    private javax.swing.JScrollPane spListaJuizes;
    private javax.swing.JTable tRodadas;
    // End of variables declaration//GEN-END:variables
}
