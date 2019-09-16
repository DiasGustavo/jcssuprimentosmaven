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
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosProduto;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaProduto extends javax.swing.JInternalFrame {
    
    private ViewDadosProduto viewDadosProduto;
    private List produtos;

    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaProduto() {
        initComponents();
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void abrirViewDadosProduto(Produto produto, String acao) {
        //if (viewDadosProducao == null) {
        viewDadosProduto = new ViewDadosProduto();
        OuvinteViewDadosProduto ouvinte = new OuvinteViewDadosProduto(viewDadosProduto);
        this.getParent().add(viewDadosProduto);
        viewDadosProduto.setPosicao();
        //}
        
        if (acao.equals("novo")) {
            viewDadosProduto.bEditar.setEnabled(false);
            viewDadosProduto.bExcluir.setEnabled(false);
            viewDadosProduto.bGravar.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosProduto.bGravar.setEnabled(false);
            viewDadosProduto.bExcluir.setEnabled(false);
            viewDadosProduto.bEditar.setEnabled(true);
        }
        if (acao.equals("excluir")) {
            viewDadosProduto.bExcluir.setEnabled(true);
            viewDadosProduto.bEditar.setEnabled(false);
            viewDadosProduto.bGravar.setEnabled(false);
        }
        
        viewDadosProduto.setProduto(produto);
        viewDadosProduto.exibirProduto(viewDadosProduto.getProduto());
        //viewDadosJuiz.preencherJuiz();
        viewDadosProduto.setVisible(true);
        try {
            viewDadosProduto.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a viewDadosProduto");
        }
    }
    
    public void listar(List produtos) {
        try {
            this.produtos = produtos;
            DefaultTableModel model = (DefaultTableModel) tJuizes.getModel();
            this.removerLinhasDaTabela(model);
            
            Iterator resultado = produtos.iterator();
            
            while (resultado.hasNext()) {
                Produto produtoTemp = (Produto) resultado.next();
                Long id = produtoTemp.getId();
                String quantidade = produtoTemp.getQuantidade().toEngineeringString();
                String descricao = produtoTemp.getDescricao();
                String fabrica = produtoTemp.getFabrica().getNomeFantasia();
                
                Object[] linha = {id, descricao, quantidade, fabrica};
                model.addRow(linha);
            }
            
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os produtos " + ex.getMessage());
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
    
    public Produto getProduto() {
        Produto produtoTemp = null;
        try {
            
            int linhaSelecionada = tJuizes.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            produtoTemp = (Produto) this.produtos.get(linhaSelecionada);
            
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar o Produção " + ex.getMessage());
        }
        return produtoTemp;
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
    /*public void bSolicitacaoAddActionListener(ActionListener ouvinte){
        bSolicitacao.addActionListener(ouvinte);
    }*/
    
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

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Pesquisa - Produto");

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
                "Id", "Descrição", "Quantidade", "Fábrica"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
=======
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bNovo, javax.swing.GroupLayout.Alignment.TRAILING)
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
<<<<<<< HEAD
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
=======
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
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
        Produto produto = new Produto();
        try {
            produto.setQuantidade(BigDecimal.ZERO);
            this.abrirViewDadosProduto(produto, "novo");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao abriar o cadastro \n" + ex.getMessage());
        }
    }//GEN-LAST:event_bNovoActionPerformed

    private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed
        // TODO add your handling code here:
        Produto produto = null;
        try {
            produto = this.getProduto();
            this.abrirViewDadosProduto(produto, "editar");
            
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Não foi possível carregar os dados da Produção " + ex.getMessage());
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
    private javax.swing.JTable tJuizes;
    // End of variables declaration//GEN-END:variables
}
