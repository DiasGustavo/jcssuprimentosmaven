/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jcssuprimentosmaven.controller.ArmazemDistribuicaoController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.ProdutoController;
import jcssuprimentosmaven.dao.ArmazemDistribuicaoDAO;
import jcssuprimentosmaven.dao.ProdutoDAO;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosEstoqueDistribuicao extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private EstoqueDistribuicao estoqueDistribuicao;
    private boolean error = false;
    private List armazens;
    private List produtos;
    private Jogador jogador;

    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosEstoqueDistribuicao() {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\produto110_110.png")); // NOI18N
        ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
        this.setArmazens(adao.listar());
        DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelEmpresa);

        ProdutoDAO pdao = new ProdutoDAO();
        this.setProdutos(pdao.listar());
        DefaultComboBoxModel defaultComboBoxModelProduto = new DefaultComboBoxModel(this.getProdutos().toArray());
        this.getCbProduto().setModel(defaultComboBoxModelProduto);
    }

    public ViewDadosEstoqueDistribuicao(Jogador jogador) {
        initComponents();

        this.jogador = jogador;
        if (this.jogador.getFuncao() == 1) {
            EmpresaController empresaController = new EmpresaController();
            List empresas = empresaController.buscarPorJogador(this.jogador);
            Empresa empresa = (Empresa) empresas.get(0);

            FabricaController fabricaController = new FabricaController();
            List fabricas = fabricaController.buscarPorEmpresa(empresa);
            Fabrica fabrica = (Fabrica) fabricas.get(0);

            ArmazemDistribuicaoController armazemDistribuicaoController = new ArmazemDistribuicaoController();
            List armazens = armazemDistribuicaoController.buscarPorFabrica(fabrica);
            this.setArmazens(armazens);
            DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getArmazens().toArray());
            this.getCbArmazem().setModel(defaultComboBoxModelEmpresa);

        } else {
            ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
            this.setArmazens(adao.listar());
            DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getArmazens().toArray());
            this.getCbArmazem().setModel(defaultComboBoxModelEmpresa);

            ProdutoDAO pdao = new ProdutoDAO();
            this.setProdutos(pdao.listar());
            DefaultComboBoxModel defaultComboBoxModelProduto = new DefaultComboBoxModel(this.getProdutos().toArray());
            this.getCbProduto().setModel(defaultComboBoxModelProduto);
        }

        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\produto110_110.png")); // NOI18N
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

    public List getProdutos() {
        return produtos;
    }

    public void setProdutos(List produtos) {
        this.produtos = produtos;
    }

    public JComboBox getCbArmazem() {
        return cbArmazem;
    }

    public void setCbArmazem(JComboBox cbArmazem) {
        this.cbArmazem = cbArmazem;
    }

    public JComboBox getCbProduto() {
        return cbProduto;
    }

    public void setCbProduto(JComboBox cbProduto) {
        this.cbProduto = cbProduto;
    }

    public EstoqueDistribuicao getEstoqueDistribuicao() {
        return estoqueDistribuicao;
    }

    public void setEstoqueDistribuicao(EstoqueDistribuicao estoqueDistribuicao) {
        this.estoqueDistribuicao = estoqueDistribuicao;
    }

    public EstoqueDistribuicao preencherEstoqueDistribuicao() {

        estoqueDistribuicao.setQuantidade(new BigDecimal(this.jtxQuantidade.getText()));
        estoqueDistribuicao.setDescricao(this.jtxDescricao.getText());
        estoqueDistribuicao.setArmazemDistribuicao((ArmazemDistribuicao) this.cbArmazem.getSelectedItem());
        estoqueDistribuicao.setProduto((Produto) this.cbProduto.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EstoqueDistribuicao>> constraintViolation = validator.validate(estoqueDistribuicao);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<EstoqueDistribuicao> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return estoqueDistribuicao;
    }

    public void exibirEstoqueDistribuicao(EstoqueDistribuicao estoque) {
        this.limparCampos();
        //if (juiz != null) {
        this.jtxQuantidade.setText(estoque.getQuantidade().toEngineeringString());
        this.jtxDescricao.setText(estoque.getDescricao());
        this.cbArmazem.setSelectedItem(estoque.getArmazemDistribuicao());
        this.cbProduto.setSelectedItem(estoque.getProduto());
        //} 
    }

    public void bGravarAddActionListener(ActionListener ouvinte) {
        bGravar.addActionListener(ouvinte);
    }

    public void bEditarAddActionListener(ActionListener ouvinte) {
        bEditar.addActionListener(ouvinte);
    }

    public void bExcluirAddActionListener(ActionListener ouvinte) {
        bExcluir.addActionListener(ouvinte);
    }

    public boolean getErro() {
        return this.error;
    }

    public void setError(boolean valor) {
        this.error = valor;
    }

    public void limparCampos() {
        this.jtxQuantidade.setText(null);
        this.jtxDescricao.setText(null);
        this.cbProduto.setSelectedIndex(0);
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
        lQuantidade = new javax.swing.JLabel();
        jtxQuantidade = new javax.swing.JTextField();
        lProduto = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox();
        lDescricao = new javax.swing.JLabel();
        jtxDescricao = new javax.swing.JTextField();
        lLogo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Estoque Distribuição");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Estoque de Distribuição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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
        lArmazem.setToolTipText("Armazém de Distribuição");

        cbArmazem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "Distribuidor" }));

        lQuantidade.setText("Quantidade");

        lProduto.setText("Produto");

        cbProduto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lDescricao.setText("Descrição");

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
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lQuantidade)
                            .addComponent(lDescricao)
                            .addComponent(lProduto)
                            .addComponent(lArmazem))
                        .addGap(29, 29, 29)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbArmazem, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lArmazem)
                    .addComponent(cbArmazem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProduto)
                    .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuantidade)
                    .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricao)
                    .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGap(34, 34, 34)
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        estoqueDistribuicao = new EstoqueDistribuicao();
        estoqueDistribuicao.setQuantidade(BigDecimal.ZERO);
    }//GEN-LAST:event_bNovoActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton bEditar;
    protected javax.swing.JButton bExcluir;
    protected javax.swing.JButton bGravar;
    protected javax.swing.JButton bNovo;
    private javax.swing.JComboBox cbArmazem;
    private javax.swing.JComboBox cbProduto;
    private javax.swing.JTextField jtxDescricao;
    private javax.swing.JTextField jtxQuantidade;
    private javax.swing.JLabel lArmazem;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lProduto;
    private javax.swing.JLabel lQuantidade;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
