/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosMeioTransporte;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaTransporte;

import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaMeioTransporte extends javax.swing.JInternalFrame {

    private ViewDadosMeioTransporte viewDadosMeioTransporte;
    private ViewPesquisaTransporte viewPesquisaTransporte;
    private List meios;
    private String destino;
    private Jogador jogador;
    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaMeioTransporte() {
        initComponents();
    }
    
    public ViewPesquisaMeioTransporte(Jogador jogador) {
        initComponents();
        this.jogador = jogador;
        if(jogador.getFuncao()==1){
            this.bAlterar.setEnabled(false);
            this.bExcluir.setEnabled(false);
            this.bNovo.setEnabled(false);
        }
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }   

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void abrirViewDadosMeioTransporte(MeioTransporte meioTransporte, String acao) {
        //if (viewDadosMateriaPrima == null) {
            viewDadosMeioTransporte = new ViewDadosMeioTransporte();
            OuvinteViewDadosMeioTransporte ouvinte = new OuvinteViewDadosMeioTransporte(viewDadosMeioTransporte);
            viewDadosMeioTransporte.setAcao(acao);            
            this.getParent().add(viewDadosMeioTransporte);
            viewDadosMeioTransporte.setPosicao();
        //}
        if (acao.equals("novo")) {
            viewDadosMeioTransporte.bEditar.setEnabled(false);
            viewDadosMeioTransporte.bExcluir.setEnabled(false);
            viewDadosMeioTransporte.bGravar.setEnabled(true);
            viewDadosMeioTransporte.bNovo.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosMeioTransporte.bGravar.setEnabled(false);
            viewDadosMeioTransporte.bExcluir.setEnabled(false);
            viewDadosMeioTransporte.bEditar.setEnabled(true);
            viewDadosMeioTransporte.bNovo.setEnabled(false);
        }
        if (acao.equals("excluir")) {
            viewDadosMeioTransporte.bExcluir.setEnabled(true);
            viewDadosMeioTransporte.bEditar.setEnabled(false);
            viewDadosMeioTransporte.bGravar.setEnabled(false);
            viewDadosMeioTransporte.bNovo.setEnabled(false);
        }

        viewDadosMeioTransporte.setMeioTransporte(meioTransporte);
        viewDadosMeioTransporte.exibirMeioTransporte(viewDadosMeioTransporte.getMeioTransporte());
        //viewDadosJuiz.preencherJuiz();
        viewDadosMeioTransporte.setVisible(true);
        try {
            viewDadosMeioTransporte.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a viewDadosMeioTransporte");
        }
    }

    public void listar(List meiosTransporte) {
        try {
            this.meios = meiosTransporte;
            DefaultTableModel model = (DefaultTableModel) tMeios.getModel();
            this.removerLinhasDaTabela(model);

            Iterator resultado = meiosTransporte.iterator();

            while (resultado.hasNext()) {
                MeioTransporte meioTemp = (MeioTransporte) resultado.next();
                String descricao = meioTemp.getDescricao();
                BigDecimal taxaSeguro = meioTemp.getTaxa_seguro();
                String tempo = meioTemp.getTempo();
                BigDecimal taxaTransporte = meioTemp.getTaxa_transporte();
                String destino = meioTemp.getDestino();                               

                Object[] linha = {descricao, taxaSeguro, taxaTransporte,tempo,destino};
                model.addRow(linha);
            }

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os Meios de Transporte " + ex.getMessage());
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

    public MeioTransporte getMeioTransporte() {
        MeioTransporte meioTemp = null;
        try {

            int linhaSelecionada = tMeios.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            meioTemp = (MeioTransporte) this.meios.get(linhaSelecionada);

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar a Meio de Transporte " + ex.getMessage());
        }
        return meioTemp;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
    
    public void bPesquisarDestinoAddActionListener(ActionListener ouvinte){
        bPesquisaDestino.addActionListener(ouvinte);
    }

    public int pedirConfirmacao(String mensagem, String titulo, int tipo) {
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, tipo);
        return resposta;
    }

    public JButton getbPesquisaTodos() {
        return bPesquisaTodos;
    }

    public void setbPesquisaTodos(JButton bPesquisaTodos) {
        this.bPesquisaTodos = bPesquisaTodos;
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
        tMeios = new javax.swing.JTable();
        bAlterar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        pPesquisa = new javax.swing.JPanel();
        lLogin = new javax.swing.JLabel();
        jtxCriterio = new javax.swing.JTextField();
        bPesquisar = new javax.swing.JButton();
        bPesquisaTodos = new javax.swing.JButton();
        bPesquisaDestino = new javax.swing.JButton();
        bTransportar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Pesquisa - Meio de Transporte");

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });

        tMeios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Taxa de Seguro", "Taxa de Transporte", "Tempo de Entrega", "Destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tMeios.getTableHeader().setReorderingAllowed(false);
        spListaJuizes.setViewportView(tMeios);

        bAlterar.setText("Alterar");
        bAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlterarActionPerformed(evt);
            }
        });

        bExcluir.setText("Excluir");

        pPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Critério de Pesquisa"));

        lLogin.setText("Descrição");

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
                .addContainerGap(47, Short.MAX_VALUE))
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

        bPesquisaDestino.setText("Pesquisar Destino");

        bTransportar.setText("Transportar");
        bTransportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTransportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bNovo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bPesquisaTodos)
                                .addGap(18, 18, 18)
                                .addComponent(bPesquisaDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bTransportar)
                                .addGap(18, 18, 18)
                                .addComponent(bAlterar)
                                .addGap(41, 41, 41)
                                .addComponent(bExcluir))
                            .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(bPesquisaTodos)
                    .addComponent(bPesquisaDestino)
                    .addComponent(bTransportar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        MeioTransporte meio = new MeioTransporte();
        meio.setTaxa_seguro(BigDecimal.ZERO);
        meio.setTaxa_transporte(BigDecimal.ZERO);
        this.abrirViewDadosMeioTransporte(meio, "novo");
    }//GEN-LAST:event_bNovoActionPerformed

    private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed
        // TODO add your handling code here:
        MeioTransporte meio = null;
        try {
            meio = this.getMeioTransporte();
            this.abrirViewDadosMeioTransporte(meio, "editar");

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Não foi possível carregar os dados do Meio de Transporte " + ex.getMessage());
        }
    }//GEN-LAST:event_bAlterarActionPerformed

    private void bTransportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTransportarActionPerformed
        // TODO add your handling code here:
        viewPesquisaTransporte = new ViewPesquisaTransporte(this.jogador);
        OuvinteViewPesquisaTransporte ouvinte = new OuvinteViewPesquisaTransporte(viewPesquisaTransporte,this.jogador);
        this.getParent().add(viewPesquisaTransporte);
        viewPesquisaTransporte.setPosicao();
        viewPesquisaTransporte.setVisible(true);
    }//GEN-LAST:event_bTransportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterar;
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    public javax.swing.JButton bPesquisaDestino;
    public javax.swing.JButton bPesquisaTodos;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JButton bTransportar;
    private javax.swing.JTextField jtxCriterio;
    private javax.swing.JLabel lLogin;
    private javax.swing.JPanel pPesquisa;
    private javax.swing.JScrollPane spListaJuizes;
    private javax.swing.JTable tMeios;
    // End of variables declaration//GEN-END:variables
}
