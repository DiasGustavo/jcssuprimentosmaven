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
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.dao.MateriaPrimaDAO;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 25/07/2018
 * @version 1.0
 * obs.: Alteração para importar materia prima
 */
public class ViewDadosArmazemSuprimento extends javax.swing.JInternalFrame {

    private List fabricas;
    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private ArmazemSuprimento armazem;
    private boolean error = false;
    /*  para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosArmazemSuprimento() {
        initComponents();
        FabricaDAO fdao = new FabricaDAO();
        this.setFabricas(fdao.listar());
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(this.getFabricas().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModel);
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\armazem_ico.png"));
    }
    
    public ViewDadosArmazemSuprimento(int localArmazem) {
        initComponents();
        FabricaDAO fdao = new FabricaDAO();
        this.setFabricas(fdao.listar());
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(this.getFabricas().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModel);
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\armazem_ico.png"));        
    }

   
    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }  

    public JComboBox getCbMateriaPrima() {
        return cbFabrica;
    }

    public void setCbMateriaPrima(JComboBox cbMateriaPrima) {
        this.cbFabrica = cbMateriaPrima;
    }

    public List getFabricas() {
        return fabricas;
    }

    public void setFabricas(List fabricas) {
        this.fabricas = fabricas;
    }
   
    

    public ArmazemSuprimento preencherArmazem() {
        
        armazem.setNomeFantasia(this.jtxNomeFantasia.getText());
        armazem.setTaxaDiaria(new BigDecimal(this.jtxTaxaDiaria.getText()));
        armazem.setTaxaSeguro(new BigDecimal (this.jtxTaxaSeguro.getText()));
        armazem.setFabrica((Fabrica)this.cbFabrica.getSelectedItem());
                
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ArmazemSuprimento>> constraintViolation = validator.validate(armazem);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<ArmazemSuprimento> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return armazem;
    }

    public void exibirArmazem(ArmazemSuprimento armazem) {
        this.limparCampos();
        //if (juiz != null) {
            this.cbFabrica.setSelectedItem(armazem.getFabrica());
            this.jtxNomeFantasia.setText(armazem.getNomeFantasia());
            this.jtxTaxaDiaria.setText(armazem.getTaxaDiaria().toEngineeringString());
            this.jtxTaxaSeguro.setText(armazem.getTaxaSeguro().toEngineeringString());
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

    public ArmazemSuprimento getArmazem() {
        return armazem;
    }

    public void setArmazem(ArmazemSuprimento armazem) {
        this.armazem = armazem;
    }

      

    public void limparCampos() {
        this.cbFabrica.setSelectedIndex(0);        
        this.jtxNomeFantasia.setText(null);
        this.jtxTaxaDiaria.setText(null);
        this.jtxTaxaSeguro.setText(null);
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
        lNomeFantasia = new javax.swing.JLabel();
        jtxNomeFantasia = new javax.swing.JTextField();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        ltaxaSeguro = new javax.swing.JLabel();
        jtxTaxaSeguro = new javax.swing.JTextField();
        lTaxaDiaria = new javax.swing.JLabel();
        jtxTaxaDiaria = new javax.swing.JTextField();
        cbFabrica = new javax.swing.JComboBox();
        lFabrica = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Armazém Suprimento");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Armazém", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lNomeFantasia.setText("Nome Fantasia");

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

        ltaxaSeguro.setText("Taxa de Seguro");

        lTaxaDiaria.setText("Taxa Diária");

        lFabrica.setText("Fábrica");

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
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
                        .addContainerGap()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ltaxaSeguro)
                            .addComponent(lTaxaDiaria)
                            .addComponent(lNomeFantasia)
                            .addComponent(lFabrica))
                        .addGap(38, 38, 38)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxNomeFantasia)
                            .addComponent(cbFabrica, 0, 401, Short.MAX_VALUE)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtxTaxaDiaria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jtxTaxaSeguro, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 304, Short.MAX_VALUE)))))
                .addGap(10, 10, 10))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomeFantasia)
                    .addComponent(jtxNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ltaxaSeguro)
                    .addComponent(jtxTaxaSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTaxaDiaria)
                    .addComponent(jtxTaxaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lFabrica))
                .addGap(18, 18, 18)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNovo)
                    .addComponent(bCancelar)
                    .addComponent(bExcluir)
                    .addComponent(bGravar)
                    .addComponent(bEditar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        armazem = new ArmazemSuprimento();
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
    private javax.swing.JComboBox cbFabrica;
    private javax.swing.JTextField jtxNomeFantasia;
    private javax.swing.JTextField jtxTaxaDiaria;
    private javax.swing.JTextField jtxTaxaSeguro;
    private javax.swing.JLabel lFabrica;
    private javax.swing.JLabel lNomeFantasia;
    private javax.swing.JLabel lTaxaDiaria;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel ltaxaSeguro;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
