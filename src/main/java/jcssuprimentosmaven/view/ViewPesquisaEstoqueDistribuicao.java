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
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosEstoqueDistribuicao;


import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaEstoqueDistribuicao extends javax.swing.JInternalFrame {

    private ViewDadosEstoqueDistribuicao viewDadosEstoqueDistribuicao;
    private List estoquesDistribuicoes;
    private Jogador jogador;

    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaEstoqueDistribuicao() {
        initComponents();
    }
    
    public ViewPesquisaEstoqueDistribuicao(Jogador usuario) {
        initComponents();
        this.jogador = usuario;
        if(this.jogador.getFuncao()==1){
            this.bNovo.setEnabled(false);
        }
    }
    
    private void abrirViewDadosEstoqueDistribuicao(EstoqueDistribuicao estoque, String acao) {
       // if (viewDadosJogador == null) {
            viewDadosEstoqueDistribuicao = new ViewDadosEstoqueDistribuicao(this.jogador);
            OuvinteViewDadosEstoqueDistribuicao ouvinte = new OuvinteViewDadosEstoqueDistribuicao(viewDadosEstoqueDistribuicao);
            viewDadosEstoqueDistribuicao.setAcao(acao);            
            this.getParent().add(viewDadosEstoqueDistribuicao);
            viewDadosEstoqueDistribuicao.setPosicao();
        //}
        if (acao.equals("novo")) {
            viewDadosEstoqueDistribuicao.bEditar.setEnabled(false);
            viewDadosEstoqueDistribuicao.bExcluir.setEnabled(false);
            viewDadosEstoqueDistribuicao.bGravar.setEnabled(true);
            viewDadosEstoqueDistribuicao.bNovo.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosEstoqueDistribuicao.bGravar.setEnabled(false);
            viewDadosEstoqueDistribuicao.bExcluir.setEnabled(false);
            viewDadosEstoqueDistribuicao.bEditar.setEnabled(true);
            viewDadosEstoqueDistribuicao.bNovo.setEnabled(false);
        }
        if (acao.equals("excluir")) {
            viewDadosEstoqueDistribuicao.bExcluir.setEnabled(true);
            viewDadosEstoqueDistribuicao.bEditar.setEnabled(false);
            viewDadosEstoqueDistribuicao.bGravar.setEnabled(false);
            viewDadosEstoqueDistribuicao.bNovo.setEnabled(false);
        }

        viewDadosEstoqueDistribuicao.setEstoqueDistribuicao(estoque);
        viewDadosEstoqueDistribuicao.exibirEstoqueDistribuicao(viewDadosEstoqueDistribuicao.getEstoqueDistribuicao());
        //viewDadosJuiz.preencherJuiz();
        viewDadosEstoqueDistribuicao.setVisible(true);
        try {
            viewDadosEstoqueDistribuicao.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a viewDadosEstoqueDistribuicao");
        }
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    

    public void listar(List estoques) {
        try {
            this.estoquesDistribuicoes = estoques;
            DefaultTableModel model = (DefaultTableModel) tEmpresas.getModel();
            this.removerLinhasDaTabela(model);

            Iterator resultado = estoquesDistribuicoes.iterator();

            while (resultado.hasNext()) {
                EstoqueDistribuicao estoqueTemp = (EstoqueDistribuicao) resultado.next();
                String produto = estoqueTemp.getProduto().getDescricao();
                String quantidade = estoqueTemp.getQuantidade().toEngineeringString();
                String armazem = estoqueTemp.getArmazemDistribuicao().getNomeFantasia();
                
                Object[] linha = {produto,quantidade,armazem};
                model.addRow(linha);
            }

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os estoques " + ex.getMessage());
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

    public EstoqueDistribuicao getEstoqueDistribuicao() {
        EstoqueDistribuicao estoqueTemp = null;
        try {

            int linhaSelecionada = tEmpresas.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            estoqueTemp = (EstoqueDistribuicao) this.estoquesDistribuicoes.get(linhaSelecionada);

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar o Jogador " + ex.getMessage());
        }
        return estoqueTemp;
    }
    
    public void bPesquisarAddActionListener(ActionListener ouvinte) {
        bPesquisar.addActionListener(ouvinte);
    }
    
    public void bExcluirAddActionListener(ActionListener ouvinte){
        bExcluir.addActionListener(ouvinte);
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

        spListaJuizes = new javax.swing.JScrollPane();
        tEmpresas = new javax.swing.JTable();
        pPesquisa = new javax.swing.JPanel();
        lLogin = new javax.swing.JLabel();
        jtxCriterio = new javax.swing.JTextField();
        bPesquisar = new javax.swing.JButton();
        bPesquisaTodos = new javax.swing.JButton();
        bNovo = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Pesquisa - Estoque Distribuição");

        tEmpresas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade", "Armazém"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tEmpresas.getTableHeader().setReorderingAllowed(false);
        spListaJuizes.setViewportView(tEmpresas);

        pPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Critério de Pesquisa"));

        lLogin.setText("Produto");

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

        bPesquisaTodos.setText("Listar Todos");

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });

        bExcluir.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(bPesquisaTodos))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(131, 131, 131)
                            .addComponent(pPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bNovo))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(bNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spListaJuizes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPesquisaTodos)
                    .addComponent(bExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        EstoqueDistribuicao estoque = new EstoqueDistribuicao();
        estoque.setQuantidade(BigDecimal.ZERO);
        this.abrirViewDadosEstoqueDistribuicao(estoque, "novo");
    }//GEN-LAST:event_bNovoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    private javax.swing.JButton bPesquisaTodos;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JTextField jtxCriterio;
    private javax.swing.JLabel lLogin;
    private javax.swing.JPanel pPesquisa;
    private javax.swing.JScrollPane spListaJuizes;
    private javax.swing.JTable tEmpresas;
    // End of variables declaration//GEN-END:variables
}
