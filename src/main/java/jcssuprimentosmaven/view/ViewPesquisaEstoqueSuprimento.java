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
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.ouvinte.OuvinteViewDadosEstoqueSuprimento;


import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewPesquisaEstoqueSuprimento extends javax.swing.JInternalFrame {

    private ViewDadosEstoqueSuprimento viewDadosEstoqueSuprimento;
    private List estoquesSuprimentos;
    private Jogador jogador;
    /**
     * Creates new form ViewPesquisaJuiz
     */
    public ViewPesquisaEstoqueSuprimento() {
        initComponents();
    }
    
    public ViewPesquisaEstoqueSuprimento(Jogador usuario) {
        initComponents();
        this.jogador = usuario;
        if(this.jogador.getFuncao() == 1){
            this.bNovo.setEnabled(false);
        }
    }
    
    private void abrirViewDadosEstoqueSuprimento(EstoqueSuprimento estoque, String acao) {
       // if (viewDadosJogador == null) {
            viewDadosEstoqueSuprimento = new ViewDadosEstoqueSuprimento();
            OuvinteViewDadosEstoqueSuprimento ouvinte = new OuvinteViewDadosEstoqueSuprimento(viewDadosEstoqueSuprimento);
            viewDadosEstoqueSuprimento.setAcao(acao);            
            this.getParent().add(viewDadosEstoqueSuprimento);
            viewDadosEstoqueSuprimento.setPosicao();
        //}
        if (acao.equals("novo")) {
            viewDadosEstoqueSuprimento.bEditar.setEnabled(false);
            viewDadosEstoqueSuprimento.bExcluir.setEnabled(false);
            viewDadosEstoqueSuprimento.bGravar.setEnabled(true);
            viewDadosEstoqueSuprimento.bNovo.setEnabled(true);
        }
        if (acao.equals("editar")) {
            viewDadosEstoqueSuprimento.bGravar.setEnabled(false);
            viewDadosEstoqueSuprimento.bExcluir.setEnabled(false);
            viewDadosEstoqueSuprimento.bEditar.setEnabled(true);
            viewDadosEstoqueSuprimento.bNovo.setEnabled(false);
        }
        if (acao.equals("excluir")) {
            viewDadosEstoqueSuprimento.bExcluir.setEnabled(true);
            viewDadosEstoqueSuprimento.bEditar.setEnabled(false);
            viewDadosEstoqueSuprimento.bGravar.setEnabled(false);
            viewDadosEstoqueSuprimento.bNovo.setEnabled(false);
        }

        viewDadosEstoqueSuprimento.setEstoqueSuprimento(estoque);
        viewDadosEstoqueSuprimento.exibirEstoqueSuprimento(viewDadosEstoqueSuprimento.getEstoqueSuprimento());
        //viewDadosJuiz.preencherJuiz();
        viewDadosEstoqueSuprimento.setVisible(true);
        try {
            viewDadosEstoqueSuprimento.setSelected(true);
        } catch (PropertyVetoException ex) {
            ViewUtil.addMsgErro("Não foi possível abrir a viewDadosEstoqueSuprimento");
        }
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    

    public void listar(List estoques) {
        try {
            this.estoquesSuprimentos = estoques;
            DefaultTableModel model = (DefaultTableModel) tEmpresas.getModel();
            this.removerLinhasDaTabela(model);

            Iterator resultado = estoquesSuprimentos.iterator();

            while (resultado.hasNext()) {
                EstoqueSuprimento estoqueTemp = (EstoqueSuprimento) resultado.next();
                String materia = estoqueTemp.getMateriaPrima().getDescricao();
                String quantidade = estoqueTemp.getQuantidade().toEngineeringString();
                String local = estoqueTemp.getDescricao();
                String armazem = estoqueTemp.getArmazemSuprimento().getNomeFantasia();
                
                Object[] linha = {materia,quantidade,local,armazem};
                model.addRow(linha);
            }

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os transportes " + ex.getMessage());
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

    public EstoqueSuprimento getEstoqueSuprimento() {
        EstoqueSuprimento estoqueTemp = null;
        try {

            int linhaSelecionada = tEmpresas.getSelectedRow();
            if (linhaSelecionada < 0) {
                ViewUtil.addMsgErro("Nenhuma linha foi Selecionada");
            }
            estoqueTemp = (EstoqueSuprimento) this.estoquesSuprimentos.get(linhaSelecionada);

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Erro ao carregar o Jogador " + ex.getMessage());
        }
        return estoqueTemp;
    }
    
    public void bPesquisarAddActionListener(ActionListener ouvinte) {
        bPesquisar.addActionListener(ouvinte);
    }

    public void bPesquisarTodosAddActionListener(ActionListener ouvinte) {
        bPesquisaTodos.addActionListener(ouvinte);
    }
    
    public void bExcluirAddActionListener(ActionListener ouvinte){
        bExcluir.addActionListener(ouvinte);
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
        setTitle("Pesquisa - Estoque Suprimento");

        tEmpresas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Materia Prima", "Quantidade", "Descrição", "Armazem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tEmpresas.getTableHeader().setReorderingAllowed(false);
        spListaJuizes.setViewportView(tEmpresas);

        pPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Critério de Pesquisa"));

        lLogin.setText("Empresa");

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
                        .addGap(31, 31, 31)
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
        EstoqueSuprimento estoque = new EstoqueSuprimento();
        estoque.setQuantidade(BigDecimal.ZERO);
        this.abrirViewDadosEstoqueSuprimento(estoque, "novo");
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
