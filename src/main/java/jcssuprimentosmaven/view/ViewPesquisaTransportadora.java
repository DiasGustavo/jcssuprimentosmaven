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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosTransportadora;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaTransportadora extends javax.swing.JInternalFrame {

    private ViewDadosTransportadora viewDadosTransportadora;
    private List transportadoras;

    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaTransportadora() {
        initComponents();
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void abrirViewDadosTransportadora(Transportadora transportadora, String acao) {
        //if (viewDadosTransportadora == null) {
            viewDadosTransportadora = new ViewDadosTransportadora();
            OuvinteViewDadosTransportadora ouvinte = new OuvinteViewDadosTransportadora(viewDadosTransportadora);
            this.getParent().add(viewDadosTransportadora);
            viewDadosTransportadora.setPosicao();
        //}
        
         if (acao.equals("novo")) {
            viewDadosTransportadora.bEditar.setEnabled(false);
            viewDadosTransportadora.bExcluir.setEnabled(false);
            viewDadosTransportadora.bGravar.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosTransportadora.bGravar.setEnabled(false);
            viewDadosTransportadora.bExcluir.setEnabled(false);
            viewDadosTransportadora.bEditar.setEnabled(true);
        }
        if (acao.equals("excluir")) {
            viewDadosTransportadora.bExcluir.setEnabled(true);
            viewDadosTransportadora.bEditar.setEnabled(false);
            viewDadosTransportadora.bGravar.setEnabled(false);
        }

        viewDadosTransportadora.setTransportadora(transportadora);
        viewDadosTransportadora.exibirTransportadora(viewDadosTransportadora.getTransportadora());
        //viewDadosJuiz.preencherJuiz();
        viewDadosTransportadora.setVisible(true);
        try {
            viewDadosTransportadora.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a ViewDadosTransportadora");
        }
    }

    public void listar(List transportadoras) {
        try {
            this.transportadoras = transportadoras;
            DefaultTableModel model = (DefaultTableModel) tTransportadora.getModel();
            this.removerLinhasDaTabela(model);

            Iterator resultado = transportadoras.iterator();

            while (resultado.hasNext()) {
                Transportadora transportadoraTemp = (Transportadora) resultado.next();
                Long id = transportadoraTemp.getId();
                String nome = transportadoraTemp.getnomeFantasia();
                String empresa = transportadoraTemp.getEmpresa().getNomeFantasia();
                

                Object[] linha = {id, nome, empresa};
                model.addRow(linha);
            }

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os transportadora " + ex.getMessage());
        }
    }

    private void removerLinhasDaTabela(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            int ultimaLinha = model.getRowCount() - 1;
            model.removeRow(ultimaLinha);
        }
    }

    public String getNomePesquisar() {
        String nome = null;
        nome = this.jtxCriterio.getText();

        return nome;
    }

    public Transportadora getTransportadora() {
        Transportadora transportadoraTemp = null;
        try {

            int linhaSelecionada = tTransportadora.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            transportadoraTemp = (Transportadora) this.transportadoras.get(linhaSelecionada);

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar o Transportadora " + ex.getMessage());
        }
        return transportadoraTemp;
    }

    public void bExcluirAddActionListener(ActionListener ouvinte) {
        bExcluir.addActionListener(ouvinte);
    }

    public void bPesquisarAddActionListener(ActionListener ouvinte) {
        bPesquisar.addActionListener(ouvinte);
    }

    public void bPesquisarTodosAddActionListener(ActionListener ouvinte) {
        bPesquisaTodos.addActionListener(ouvinte);
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
        tTransportadora = new javax.swing.JTable();
        bAlterar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        pPesquisa = new javax.swing.JPanel();
        lLogin = new javax.swing.JLabel();
        jtxCriterio = new javax.swing.JTextField();
        bPesquisar = new javax.swing.JButton();
        bPesquisaTodos = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Pesquisa - Fornecedor");

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });

        tTransportadora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome Fantasia", "Empresa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tTransportadora.getTableHeader().setReorderingAllowed(false);
        spListaJuizes.setViewportView(tTransportadora);

        bAlterar.setText("Alterar");
        bAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlterarActionPerformed(evt);
            }
        });

        bExcluir.setText("Excluir");

        pPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Critério de Pesquisa"));

        lLogin.setText("Nome Fantasia");

        bPesquisar.setText("Pesquisar");

        javax.swing.GroupLayout pPesquisaLayout = new javax.swing.GroupLayout(pPesquisa);
        pPesquisa.setLayout(pPesquisaLayout);
        pPesquisaLayout.setHorizontalGroup(
            pPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bPesquisar)
                    .addGroup(pPesquisaLayout.createSequentialGroup()
                        .addComponent(lLogin)
                        .addGap(18, 18, 18)
                        .addComponent(jtxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pPesquisaLayout.setVerticalGroup(
            pPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPesquisaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lLogin)
                    .addComponent(jtxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bPesquisar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bPesquisaTodos.setText("Pesquisar Todos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bNovo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bPesquisaTodos)
                                .addGap(32, 32, 32)
                                .addComponent(bAlterar)
                                .addGap(41, 41, 41)
                                .addComponent(bExcluir))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(pPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(bPesquisaTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        Transportadora transportadora = new Transportadora();
        this.abrirViewDadosTransportadora(transportadora, "novo");
    }//GEN-LAST:event_bNovoActionPerformed

    private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed
        // TODO add your handling code here:
        Transportadora transportadora = null;
        try {
            transportadora = this.getTransportadora();
            this.abrirViewDadosTransportadora(transportadora, "editar");

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Não foi possível carregar os dados do Fornecedor " + ex.getMessage());
        }
    }//GEN-LAST:event_bAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterar;
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    private javax.swing.JButton bPesquisaTodos;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JTextField jtxCriterio;
    private javax.swing.JLabel lLogin;
    private javax.swing.JPanel pPesquisa;
    private javax.swing.JScrollPane spListaJuizes;
    private javax.swing.JTable tTransportadora;
    // End of variables declaration//GEN-END:variables
}
