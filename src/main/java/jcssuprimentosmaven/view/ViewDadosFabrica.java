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
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 04/08/2018
 * @version 1.0
 * obs.: adição do carregamento de empresas
 */
public class ViewDadosFabrica extends javax.swing.JInternalFrame {

    private List jogadores;
    private List empresas;
    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Fabrica fabrica;
    private boolean error = false;
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosFabrica() {
        initComponents();
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\fabrica_ico.png"));
        
        //carregar as empresas
        EmpresaController edao = new EmpresaController();
        edao.listar();
        this.setEmpresas(edao.getListaEmpresas());
        DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getEmpresas().toArray());
        this.getCbEmpresas().setModel(defaultComboBoxModelEmpresa);
    }

   
    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List getJogadores() {
        return jogadores;
    }

    public void setJogadores(List jogadores) {
        this.jogadores = jogadores;
    }

    public List getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List empresas) {
        this.empresas = empresas;
    }

    public JComboBox getCbEmpresas() {
        return cbEmpresas;
    }

    public void setCbEmpresas(JComboBox cbEmpresas) {
        this.cbEmpresas = cbEmpresas;
    }

   
    public Fabrica preencherFabrica() {
        
        fabrica.setNomeFantasia(this.jtxFantasia.getText());
        int tempo = Integer.parseInt(this.jtxTTransformacao.getText());
        fabrica.setTempoTransformacao(tempo);
        fabrica.setCustoTransformacao(new BigDecimal (this.jtxCTransformacao.getText()));
        fabrica.setFatorTransformacao(new BigDecimal (this.jtxFTransformacao.getText()));
        fabrica.setTaxaServicoCliente(new BigDecimal (this.jtxTaxaCliente.getText()));
        fabrica.setPesquisa(new BigDecimal (this.jtxPesquisa.getText()));
        //fabrica.setInvestimento((Investimento) this.cbInvestimento.getSelectedItem());
        fabrica.setDemanda(this.jtxDemanda.getText());
        fabrica.setEmpresa((Empresa)this.cbEmpresas.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Fabrica>> constraintViolation = validator.validate(fabrica);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Fabrica> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return fabrica;
    }

    public void exibirFabrica(Fabrica fabrica) {
        this.limparCampos();
        //if (juiz != null) {
            //this.cbInvestimento.setSelectedItem(fabrica.getInvestimento());
            this.jtxFantasia.setText(fabrica.getNomeFantasia());
            this.jtxTTransformacao.setText(Integer.toString(fabrica.getTempoTransformacao()));
            this.jtxCTransformacao.setText(fabrica.getCustoTransformacao().toEngineeringString());
            this.jtxFTransformacao.setText(fabrica.getFatorTransformacao().toEngineeringString());
            this.jtxTaxaCliente.setText(fabrica.getTaxaServicoCliente().toEngineeringString());
            this.jtxPesquisa.setText(fabrica.getPesquisa().toEngineeringString());
            this.jtxDemanda.setText(fabrica.getDemanda());
            this.cbEmpresas.setSelectedItem(fabrica.getEmpresa());
            
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

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) {
        this.fabrica = fabrica;
    }   

    public void limparCampos() {
        this.jtxDemanda.setText(null);
        //this.cbInvestimento.setSelectedIndex(0);
        this.jtxFantasia.setText(null);
        this.jtxCTransformacao.setText(null);
        this.jtxFTransformacao.setText(null);
        this.jtxPesquisa.setText(null);
        this.jtxTTransformacao.setText(null);
        this.jtxTaxaCliente.setText(null);
        
        
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
        lFantasia = new javax.swing.JLabel();
        jtxFantasia = new javax.swing.JTextField();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lTTransformacao = new javax.swing.JLabel();
        jtxTTransformacao = new javax.swing.JTextField();
        lCTransformacao = new javax.swing.JLabel();
        jtxCTransformacao = new javax.swing.JTextField();
        lFTransformacao = new javax.swing.JLabel();
        jtxFTransformacao = new javax.swing.JTextField();
        lTaxaCliente = new javax.swing.JLabel();
        jtxTaxaCliente = new javax.swing.JTextField();
        lPesquisa = new javax.swing.JLabel();
        jtxPesquisa = new javax.swing.JTextField();
        lDemanda = new javax.swing.JLabel();
        jtxDemanda = new javax.swing.JTextField();
        lEmpresa = new javax.swing.JLabel();
        cbEmpresas = new javax.swing.JComboBox();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Fábrica");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Fábrica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lFantasia.setText("Nome Fantasia");

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

        lTTransformacao.setText("Tempo de Transformação");

        lCTransformacao.setText("Custo de Transformação");

        lFTransformacao.setText("Fator de Transformação");

        lTaxaCliente.setText("Taxa de Serviço Cliente");

        lPesquisa.setText("Valor de Pesquisa");

        lDemanda.setText("Demanda (%)");

        lEmpresa.setText("Empresa");

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addComponent(bNovo)
                                .addGap(10, 10, 10)
                                .addComponent(bGravar)
                                .addGap(10, 10, 10)
                                .addComponent(bCancelar)
                                .addGap(10, 10, 10)
                                .addComponent(bEditar)
                                .addGap(10, 10, 10)
                                .addComponent(bExcluir))
                            .addComponent(lFantasia)))
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lTaxaCliente))
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lTTransformacao))
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lCTransformacao))
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lFTransformacao))
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lEmpresa)))
                        .addGap(34, 34, 34)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxFTransformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxTaxaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxCTransformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxTTransformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxFantasia, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                            .addComponent(cbEmpresas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lFantasia)
                    .addComponent(jtxFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxTTransformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lTTransformacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxCTransformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCTransformacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxFTransformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFTransformacao))
                .addGap(6, 6, 6)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTaxaCliente)
                    .addComponent(jtxTaxaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPesquisa)
                    .addComponent(jtxPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDemanda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEmpresa)
                    .addComponent(cbEmpresas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bNovo)
                    .addComponent(bGravar)
                    .addComponent(bCancelar)
                    .addComponent(bEditar)
                    .addComponent(bExcluir)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        fabrica = new Fabrica();
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
    private javax.swing.JComboBox cbEmpresas;
    private javax.swing.JTextField jtxCTransformacao;
    private javax.swing.JTextField jtxDemanda;
    private javax.swing.JTextField jtxFTransformacao;
    private javax.swing.JTextField jtxFantasia;
    private javax.swing.JTextField jtxPesquisa;
    private javax.swing.JTextField jtxTTransformacao;
    private javax.swing.JTextField jtxTaxaCliente;
    private javax.swing.JLabel lCTransformacao;
    private javax.swing.JLabel lDemanda;
    private javax.swing.JLabel lEmpresa;
    private javax.swing.JLabel lFTransformacao;
    private javax.swing.JLabel lFantasia;
    private javax.swing.JLabel lPesquisa;
    private javax.swing.JLabel lTTransformacao;
    private javax.swing.JLabel lTaxaCliente;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
