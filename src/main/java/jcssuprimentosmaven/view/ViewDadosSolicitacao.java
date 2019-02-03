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
import jcssuprimentosmaven.dao.ArmazemDistribuicaoDAO;
import jcssuprimentosmaven.dao.ArmazemFabricaDAO;
import jcssuprimentosmaven.dao.MeioTransporteDAO;
import jcssuprimentosmaven.dao.ProdutoDAO;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosSolicitacao extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Solicitacao solicitacao;
    private boolean error = false;
    private List produtos;
    private List armazens;
    private List transportes;
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosSolicitacao() {
        initComponents();

        //carregar os produtos
        ProdutoDAO pdao = new ProdutoDAO();
        this.setProdutos(pdao.listar());
        DefaultComboBoxModel defaultComboBoxModelFabrica = new DefaultComboBoxModel(this.getProdutos().toArray());
        this.getCbProdutos().setModel(defaultComboBoxModelFabrica);
        //carrregar os armazens
        ArmazemDistribuicaoDAO adao = new ArmazemDistribuicaoDAO();
        this.setArmazens(adao.listar());
        DefaultComboBoxModel defaultComboBoxModelArmazem = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelArmazem);
        //Meio de Transporte
        MeioTransporteDAO mtdao = new MeioTransporteDAO();
        this.setTransportes(mtdao.listar());
        DefaultComboBoxModel defaultComboBoxModelEstoque = new DefaultComboBoxModel(this.getTransportes().toArray());
        this.getCbMeioTransporte().setModel(defaultComboBoxModelEstoque);
        
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\caixa110_110.png"));
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

    public List getTransportes() {
        return transportes;
    }

    public void setTransportes(List transportes) {
        this.transportes = transportes;
    }

    public JComboBox getCbMeioTransporte() {
        return cbMeioTransporte;
    }

    public void setCbMeioTransporte(JComboBox cbMeioTransporte) {
        this.cbMeioTransporte = cbMeioTransporte;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public List getProdutos() {
        return produtos;
    }

    public void setProdutos(List produtos) {
        this.produtos = produtos;
    }

    public JComboBox getCbProdutos() {
        return cbProdutos;
    }

    public void setCbProdutos(JComboBox cbProdutos) {
        this.cbProdutos = cbProdutos;
    }
    
    public Solicitacao preencherSolicitacao() {

        solicitacao.setQuantidade(new BigDecimal(this.jtxQuantidade.getText()));
        solicitacao.setDescricao(this.jtxDescricao.getText());
        solicitacao.setProduto((Produto) this.cbProdutos.getSelectedItem());
        solicitacao.setArmazemDistribuicao((ArmazemDistribuicao) this.cbArmazem.getSelectedItem());
        solicitacao.setMeioTransporte((MeioTransporte)this.cbMeioTransporte.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Solicitacao>> constraintViolation = validator.validate(solicitacao);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Solicitacao> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return solicitacao;
    }

    public void exibirSolicitacao(Solicitacao solicitacao) {
        this.limparCampos();
        //if (juiz != null) {
        this.jtxQuantidade.setText(solicitacao.getQuantidade().toEngineeringString());
        this.jtxDescricao.setText(solicitacao.getDescricao());
        this.cbArmazem.setSelectedItem(solicitacao.getArmazemDistribuicao());
        this.cbProdutos.setSelectedItem(solicitacao.getProduto());
        this.cbMeioTransporte.setSelectedItem(solicitacao.getMeioTransporte());
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
        this.jtxQuantidade.setText(null);
        this.jtxDescricao.setText(null);
        this.cbArmazem.setSelectedIndex(0);
        this.cbProdutos.setSelectedIndex(0);
        this.cbMeioTransporte.setSelectedIndex(0);
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
        lProduto = new javax.swing.JLabel();
        cbProdutos = new javax.swing.JComboBox();
        lArmazem = new javax.swing.JLabel();
        cbArmazem = new javax.swing.JComboBox();
        lDescricao = new javax.swing.JLabel();
        jtxDescricao = new javax.swing.JTextField();
        lQuantidade = new javax.swing.JLabel();
        jtxQuantidade = new javax.swing.JTextField();
        lMeioTransporte = new javax.swing.JLabel();
        cbMeioTransporte = new javax.swing.JComboBox();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Solicitação");

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

        lProduto.setText("Produto");

        cbProdutos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lArmazem.setText("Armazém");
        lArmazem.setToolTipText("Armazém de destino");

        cbArmazem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lDescricao.setText("Descrição");

        lQuantidade.setText("Quantidade");

        lMeioTransporte.setText("Meio de Transporte");

        cbMeioTransporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lProduto)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addComponent(bNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bExcluir)))
                        .addContainerGap(320, Short.MAX_VALUE))
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDescricao)
                            .addComponent(lQuantidade)
                            .addComponent(lArmazem)
                            .addComponent(lMeioTransporte))
                        .addGap(21, 21, 21)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbMeioTransporte, 0, 522, Short.MAX_VALUE)
                                .addComponent(cbProdutos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbArmazem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProduto)
                    .addComponent(cbProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbArmazem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lArmazem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMeioTransporte)
                    .addComponent(cbMeioTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuantidade)
                    .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricao)
                    .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
        solicitacao = new Solicitacao();
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
    private javax.swing.JComboBox cbMeioTransporte;
    private javax.swing.JComboBox cbProdutos;
    private javax.swing.JTextField jtxDescricao;
    private javax.swing.JTextField jtxQuantidade;
    private javax.swing.JLabel lArmazem;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lMeioTransporte;
    private javax.swing.JLabel lProduto;
    private javax.swing.JLabel lQuantidade;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
