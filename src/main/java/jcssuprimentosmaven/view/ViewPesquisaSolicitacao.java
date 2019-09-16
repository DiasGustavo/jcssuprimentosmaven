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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
<<<<<<< HEAD
import jcssuprimentosmaven.domain.Jogador;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosSolicitacao;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaSolicitacao extends javax.swing.JInternalFrame {

    private ViewDadosSolicitacao viewDadosSolicitacao;
    private List solicitacoes;
<<<<<<< HEAD
    private Jogador jogador;
=======

>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaSolicitacao() {
        initComponents();
    }
<<<<<<< HEAD
    
    public ViewPesquisaSolicitacao(Jogador jogador){
        this.jogador = jogador;
        initComponents();
        if(jogador.getFuncao() == 1){
            this.bAlterar.setEnabled(false);
            this.bExcluir.setEnabled(false);
            this.bEntregar.setEnabled(false);
        }
    }
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    private void abrirViewDadosSolicitacao(Solicitacao solicitacao, String acao) {
        //if (viewDadosProducao == null) {
<<<<<<< HEAD
            viewDadosSolicitacao = new ViewDadosSolicitacao(this.jogador);
=======
            viewDadosSolicitacao = new ViewDadosSolicitacao();
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
            OuvinteViewDadosSolicitacao ouvinte = new OuvinteViewDadosSolicitacao(viewDadosSolicitacao);
            this.getParent().add(viewDadosSolicitacao);
            viewDadosSolicitacao.setPosicao();
        //}
        
         if (acao.equals("novo")) {
            viewDadosSolicitacao.bEditar.setEnabled(false);
            viewDadosSolicitacao.bExcluir.setEnabled(false);
            viewDadosSolicitacao.bGravar.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosSolicitacao.bGravar.setEnabled(false);
            viewDadosSolicitacao.bExcluir.setEnabled(false);
            viewDadosSolicitacao.bEditar.setEnabled(true);
        }
        if (acao.equals("excluir")) {
            viewDadosSolicitacao.bExcluir.setEnabled(true);
            viewDadosSolicitacao.bEditar.setEnabled(false);
            viewDadosSolicitacao.bGravar.setEnabled(false);
        }

        viewDadosSolicitacao.setSolicitacao(solicitacao);
        viewDadosSolicitacao.exibirSolicitacao(viewDadosSolicitacao.getSolicitacao());
        //viewDadosJuiz.preencherJuiz();
        viewDadosSolicitacao.setVisible(true);
        try {
            viewDadosSolicitacao.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a viewDadosProducao");
        }
    }

    public void listar(List solicitacoes) {
        try {
            this.solicitacoes = solicitacoes;
            DefaultTableModel model = (DefaultTableModel) tJuizes.getModel();
            this.removerLinhasDaTabela(model);

            Iterator resultado = solicitacoes.iterator();

            while (resultado.hasNext()) {
                Solicitacao solicitacaoTemp = (Solicitacao) resultado.next();
                Long id = solicitacaoTemp.getId();
                String quantidade = solicitacaoTemp.getQuantidade().toEngineeringString();
<<<<<<< HEAD
                String meio = solicitacaoTemp.getMeioTransporte().getDescricao();
=======
                String meio = solicitacaoTemp.getMeioTransporte().getTempo();
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                String produto = solicitacaoTemp.getProduto().getDescricao();
                String armazem = solicitacaoTemp.getArmazemDistribuicao().getNomeFantasia();

                Object[] linha = {id, quantidade, meio ,produto, armazem};
                model.addRow(linha);
            }

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar as solicitação " + ex.getMessage());
        }
    }

    private void removerLinhasDaTabela(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            int ultimaLinha = model.getRowCount() - 1;
            model.removeRow(ultimaLinha);
        }
    }

    public Long getIdPesquisar() {
        Long codigo = null;
        codigo = Long.parseLong(this.jtxCriterio.getText());

        return codigo;
    }

    public Solicitacao getSolicitacao() {
        Solicitacao solicitacaoTemp = null;
        try {

            int linhaSelecionada = tJuizes.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            solicitacaoTemp = (Solicitacao) this.solicitacoes.get(linhaSelecionada);

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar o Produção " + ex.getMessage());
        }
        return solicitacaoTemp;
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
<<<<<<< HEAD
    
    public void bEntregarSolicitacaoListener (ActionListener ouvinte){
        bEntregar.addActionListener(ouvinte);
    }
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

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
        tJuizes = new javax.swing.JTable();
        bAlterar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        pPesquisa = new javax.swing.JPanel();
        lLogin = new javax.swing.JLabel();
        jtxCriterio = new javax.swing.JTextField();
        bPesquisar = new javax.swing.JButton();
        bPesquisaTodos = new javax.swing.JButton();
<<<<<<< HEAD
        bEntregar = new javax.swing.JButton();
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("Pesquisa - Solicitação");

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });

        tJuizes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Quantidade", "Meio Transporte", "Materia Prima", "Armazém Destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tJuizes.getTableHeader().setReorderingAllowed(false);
        spListaJuizes.setViewportView(tJuizes);

        bAlterar.setText("Alterar");
        bAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlterarActionPerformed(evt);
            }
        });

        bExcluir.setText("Excluir");

        pPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Critério de Pesquisa"));

        lLogin.setText("Id");

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

<<<<<<< HEAD
        bEntregar.setText("Entregar");

=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
                        .addComponent(bNovo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bEntregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
=======
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bNovo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                                .addComponent(bPesquisaTodos)
                                .addGap(32, 32, 32)
                                .addComponent(bAlterar)
                                .addGap(41, 41, 41)
<<<<<<< HEAD
                                .addComponent(bExcluir))
                            .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))))
=======
                                .addComponent(bExcluir))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)))
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
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
<<<<<<< HEAD
                    .addComponent(bPesquisaTodos)
                    .addComponent(bEntregar))
=======
                    .addComponent(bPesquisaTodos))
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:        
        try{
            Solicitacao solicitacao = new Solicitacao();
            solicitacao.setQuantidade(BigDecimal.ZERO);
            solicitacao.setValor(BigDecimal.ZERO);
            
            this.abrirViewDadosSolicitacao(solicitacao, "novo");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao abrir o cadastro \n" + ex.getMessage());
        }
        
    }//GEN-LAST:event_bNovoActionPerformed

    private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed
        // TODO add your handling code here:
        Solicitacao solicitacao = null;
        try {
            solicitacao = this.getSolicitacao();
            this.abrirViewDadosSolicitacao(solicitacao, "editar");

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Não foi possível carregar os dados da Solicitação " + ex.getMessage());
        }
    }//GEN-LAST:event_bAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterar;
<<<<<<< HEAD
    private javax.swing.JButton bEntregar;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    private javax.swing.JButton bPesquisaTodos;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JTextField jtxCriterio;
    private javax.swing.JLabel lLogin;
    private javax.swing.JPanel pPesquisa;
    private javax.swing.JScrollPane spListaJuizes;
    private javax.swing.JTable tJuizes;
    // End of variables declaration//GEN-END:variables
}
