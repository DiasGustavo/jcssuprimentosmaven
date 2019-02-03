/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.ProducaoController;
import jcssuprimentosmaven.dao.ArmazemFabricaDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Producao;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosProducao extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Producao producao;
    private boolean error = false;
    private List matA;
    private List armazens;
    private List matB;
    private Jogador jogador;

    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosProducao() {
        initComponents();

        //carrregar os armazens
        ArmazemFabricaDAO adao = new ArmazemFabricaDAO();
        this.setArmazens(adao.listar());
        DefaultComboBoxModel defaultComboBoxModelArmazem = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelArmazem);
        //Estoque
        /*EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
        this.setEstoques(esdao.listar());
        DefaultComboBoxModel defaultComboBoxModelEstoque = new DefaultComboBoxModel(this.getEstoques().toArray());
        this.getCbEstoque().setModel(defaultComboBoxModelEstoque);*/

        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\producao_simbolo.png"));
    }

    public ViewDadosProducao(Jogador jogador) {
        initComponents();
        this.jogador = jogador;
        //carrregar os armazens
        ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
        FabricaController fabricaController = new FabricaController();
        EmpresaController empresaController = new EmpresaController();
        List empresas = empresaController.buscarPorJogador(jogador);
        Empresa empresa = (Empresa) empresas.get(0);
        List fabricas = fabricaController.buscarPorEmpresa(empresa);
        Fabrica fabrica = (Fabrica) fabricas.get(0);        
        this.setArmazens(armazemFabricaController.buscarPorFabrica(fabrica));
        
        DefaultComboBoxModel defaultComboBoxModelArmazem = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelArmazem);
        //Estoque
        /*EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
        this.setEstoques(esdao.listar());
        DefaultComboBoxModel defaultComboBoxModelEstoque = new DefaultComboBoxModel(this.getEstoques().toArray());
        this.getCbEstoque().setModel(defaultComboBoxModelEstoque);*/

        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\producao_simbolo.png"));
    }

    public JTextField getJtxQtdA() {
        return jtxQtdA;
    }

    public void setJtxQtdA(JTextField jtxQtdA) {
        this.jtxQtdA = jtxQtdA;
    }

    public JTextField getJtxQtdB() {
        return jtxQtdB;
    }

    public void setJtxQtdB(JTextField jtxQtdB) {
        this.jtxQtdB = jtxQtdB;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List getArmazens() {
        return armazens;
    }

    public void setArmazens(List armazens) {
        this.armazens = armazens;
    }

    public JComboBox getCbArmazem() {
        return cbArmazem;
    }

    public void setCbArmazem(JComboBox cbArmazem) {
        this.cbArmazem = cbArmazem;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    public List getMatA() {
        return matA;
    }

    public void setMatA(List matA) {
        this.matA = matA;
    }

    public List getMatB() {
        return matB;
    }

    public void setMatB(List matB) {
        this.matB = matB;
    }

    public JComboBox getCbMatA() {
        return cbMatA;
    }

    public void setCbMatA(JComboBox cbMatA) {
        this.cbMatA = cbMatA;
    }

    public JComboBox getCbMatB() {
        return cbMatB;
    }

    public void setCbMatB(JComboBox cbMatB) {
        this.cbMatB = cbMatB;
    }

    public JComboBox getCbEstoque() {
        return cbMatA;
    }

    public void setCbEstoque(JComboBox cbEstoque) {
        this.cbMatA = cbEstoque;
    }

    public Producao preencherProducao() {

        producao.setQuantidadeMateriaA(new BigDecimal(this.jtxQtdA.getText()));
        producao.setQuantidadeMateriaB(new BigDecimal(this.jtxQtdB.getText()));
        producao.setArmazemFabrica((ArmazemFabrica) this.cbArmazem.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Producao>> constraintViolation = validator.validate(producao);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Producao> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return producao;
    }

    public void exibirProducao(Producao producao) {
        this.limparCampos();
        //if (juiz != null) {
        this.jtxQtdA.setText(producao.getQuantidadeMateriaA().toEngineeringString());
        this.jtxQtdB.setText(producao.getQuantidadeMateriaB().toEngineeringString());
        BigDecimal produtos = producao.getQuantidadeMateriaA().multiply(new BigDecimal("0.4")).add(producao.getQuantidadeMateriaB().multiply(new BigDecimal("0.6")));
        this.jtxProdutos.setText(produtos.toEngineeringString());
        this.cbArmazem.setSelectedItem(producao.getArmazemFabrica());

        //} 
    }

    public void bGravarProducaoAddActionListener(ActionListener ouvinte) {
        bGravar.addActionListener(ouvinte);
    }

    public void bEditarProducaoAddActionListener(ActionListener ouvinte) {
        bEditar.addActionListener(ouvinte);
    }

    public void bExcluirProducaoAddActionListener(ActionListener ouvinte) {
        bExcluir.addActionListener(ouvinte);
    }

    public boolean getErro() {
        return this.error;
    }

    public void setError(boolean valor) {
        this.error = valor;
    }

    public void limparCampos() {
        this.jtxQtdA.setText(null);
        this.jtxQtdB.setText(null);
        this.jtxProdutos.setText(null);
        this.cbArmazem.setSelectedIndex(0);

    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
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

        pDadosJuiz = new javax.swing.JPanel();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lArmazem = new javax.swing.JLabel();
        cbArmazem = new javax.swing.JComboBox();
        lProdutos = new javax.swing.JLabel();
        jtxProdutos = new javax.swing.JTextField();
        lQtdA = new javax.swing.JLabel();
        jtxQtdA = new javax.swing.JTextField();
        lMatA = new javax.swing.JLabel();
        cbMatA = new javax.swing.JComboBox();
        lMatB = new javax.swing.JLabel();
        cbMatB = new javax.swing.JComboBox();
        lQtdB = new javax.swing.JLabel();
        jtxQtdB = new javax.swing.JTextField();
        lProducao = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Producao");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bExcluir.setText("Excluir");

        bGravar.setText("Gravar");

        bEditar.setText("Editar");

        lArmazem.setText("Armazém");
        lArmazem.setToolTipText("Armazém de Produção");

        cbArmazem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        cbArmazem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbArmazemFocusLost(evt);
            }
        });

        lProdutos.setText("Produtos");

        jtxProdutos.setEditable(false);

        lQtdA.setText("Qtd Couro");
        lQtdA.setToolTipText("Quantidade de Couro");

        lMatA.setText("Mat. A");
        lMatA.setToolTipText("Couro");

        cbMatA.setMaximumRowCount(1);
        cbMatA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lMatB.setText("Mat. B");
        lMatB.setToolTipText("Plástico");

        cbMatB.setMaximumRowCount(1);
        cbMatB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lQtdB.setText("Qtd Plástico");
        lQtdB.setToolTipText("Quantidade de Plástico");

        jtxQtdB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxQtdBKeyPressed(evt);
            }
        });

        lProducao.setText("Produção");

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addComponent(bNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bExcluir))
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lProdutos)
                            .addComponent(lArmazem)
                            .addComponent(lMatA)
                            .addComponent(lMatB)
                            .addComponent(lProducao))
                        .addGap(25, 25, 25)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbArmazem, javax.swing.GroupLayout.Alignment.LEADING, 0, 284, Short.MAX_VALUE)
                                .addComponent(cbMatA, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbMatB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosJuizLayout.createSequentialGroup()
                                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtxProdutos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosJuizLayout.createSequentialGroup()
                                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtxQtdA, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lQtdA))
                                        .addGap(18, 18, 18)
                                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtxQtdB, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lQtdB))))
                                .addGap(52, 52, 52)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbArmazem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lArmazem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMatA)
                    .addComponent(cbMatA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMatB)
                    .addComponent(cbMatB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQtdA)
                    .addComponent(lQtdB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxQtdA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxQtdB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lProducao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProdutos)
                    .addComponent(jtxProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNovo)
                    .addComponent(bCancelar)
                    .addComponent(bExcluir)
                    .addComponent(bGravar)
                    .addComponent(bEditar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        producao = new Producao();
    }//GEN-LAST:event_bNovoActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void cbArmazemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbArmazemFocusLost
        ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
        ArmazemFabrica armazem = (ArmazemFabrica) cbArmazem.getSelectedItem();
        List materiaA = armazemFabricaController.buscarPorFabrica(armazem.getFabrica());
        //List materiaB = armazemFabricaController.buscarPorFabrica(armazem.getFabrica());
        this.setMatA(materiaA);

        DefaultComboBoxModel defaultComboBoxModelArmazemA = new DefaultComboBoxModel(this.getMatA().toArray());
        this.getCbMatA().setModel(defaultComboBoxModelArmazemA);
        this.cbMatA.setSelectedIndex(0);

        this.setMatB(materiaA);

        DefaultComboBoxModel defaultComboBoxModelArmazemB = new DefaultComboBoxModel(this.getMatB().toArray());
        this.getCbMatB().setModel(defaultComboBoxModelArmazemB);
        this.cbMatB.setSelectedIndex(1);

    }//GEN-LAST:event_cbArmazemFocusLost

    private void jtxQtdBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxQtdBKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BigDecimal qtda = new BigDecimal(this.jtxQtdA.getText());
            BigDecimal qtdb = new BigDecimal(this.jtxQtdB.getText());
            BigDecimal produtos = qtda.multiply(new BigDecimal("0.4")).add(qtdb.multiply(new BigDecimal("0.6")));
            this.jtxProdutos.setText(produtos.toEngineeringString());
        }
    }//GEN-LAST:event_jtxQtdBKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton bEditar;
    protected javax.swing.JButton bExcluir;
    protected javax.swing.JButton bGravar;
    protected javax.swing.JButton bNovo;
    private javax.swing.JComboBox cbArmazem;
    private javax.swing.JComboBox cbMatA;
    private javax.swing.JComboBox cbMatB;
    private javax.swing.JTextField jtxProdutos;
    private javax.swing.JTextField jtxQtdA;
    private javax.swing.JTextField jtxQtdB;
    private javax.swing.JLabel lArmazem;
    private javax.swing.JLabel lMatA;
    private javax.swing.JLabel lMatB;
    private javax.swing.JLabel lProducao;
    private javax.swing.JLabel lProdutos;
    private javax.swing.JLabel lQtdA;
    private javax.swing.JLabel lQtdB;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
