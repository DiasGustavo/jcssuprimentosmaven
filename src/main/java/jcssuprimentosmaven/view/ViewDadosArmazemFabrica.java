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
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 25/07/2018
 * @version 1.0
 * obs.: criação do armazém da fábrica
 */
public class ViewDadosArmazemFabrica extends javax.swing.JInternalFrame {

    private List materias;
    private List fabricas;
    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private ArmazemFabrica armazem;
    private boolean error = false;
    /*  para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosArmazemFabrica() {
        initComponents();
        MateriaPrimaDAO mpdao = new MateriaPrimaDAO();
        this.setMaterias(mpdao.listar());
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(this.getMaterias().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModel);
        
        
        FabricaDAO fdao = new FabricaDAO();
        this.setFabricas(fdao.listar());
        DefaultComboBoxModel defaultComboBoxModelFabrica = new DefaultComboBoxModel(this.getFabricas().toArray());
        this.getCbFabrica().setModel(defaultComboBoxModelFabrica);
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\armazem_ico.png"));
    }
    
      
    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }  

    public JComboBox getCbMateriaPrima() {
        return cbMateriaPrima;
    }

    public void setCbMateriaPrima(JComboBox cbMateriaPrima) {
        this.cbMateriaPrima = cbMateriaPrima;
    }

    public List getMaterias() {
        return materias;
    }

    public void setMaterias(List materias) {
        this.materias = materias;
    }

    public List getFabricas() {
        return fabricas;
    }

    public void setFabricas(List fabricas) {
        this.fabricas = fabricas;
    }

    public JComboBox getCbFabrica() {
        return cbFabrica;
    }

    public void setCbFabrica(JComboBox cbFabrica) {
        this.cbFabrica = cbFabrica;
    }
    

    public ArmazemFabrica preencherArmazem() {
        
        armazem.setQuantidade(new BigDecimal (this.jtxQtd.getText()));
        armazem.setMateriaPrima((MateriaPrima)this.cbMateriaPrima.getSelectedItem());
        armazem.setFabrica((Fabrica) this.cbFabrica.getSelectedItem());
                
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ArmazemFabrica>> constraintViolation = validator.validate(armazem);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<ArmazemFabrica> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return armazem;
    }

    public void exibirArmazem(ArmazemFabrica armazem) {
        this.limparCampos();
        //if (juiz != null) {
            this.cbMateriaPrima.setSelectedItem(armazem.getMateriaPrima());
            this.cbFabrica.setSelectedItem(armazem.getFabrica());
            this.jtxQtd.setText(armazem.getQuantidade().toEngineeringString());
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

    public ArmazemFabrica getArmazem() {
        return armazem;
    }

    public void setArmazem(ArmazemFabrica armazem) {
        this.armazem = armazem;
    }

      

    public void limparCampos() {
        this.cbMateriaPrima.setSelectedIndex(0); 
        this.cbFabrica.setSelectedIndex(0);
        this.jtxQtd.setText(null);
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
        lQtd = new javax.swing.JLabel();
        jtxQtd = new javax.swing.JTextField();
        cbMateriaPrima = new javax.swing.JComboBox();
        lMateriaPrima = new javax.swing.JLabel();
        lFabrica = new javax.swing.JLabel();
        cbFabrica = new javax.swing.JComboBox();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Armazém Fábrica");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Armazém", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        lQtd.setText("Quantidade");

        lMateriaPrima.setText("Materia Prima");
        lMateriaPrima.setToolTipText("No estoque do armazém de suprimento");

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
                            .addComponent(lQtd)
                            .addComponent(lMateriaPrima))
                        .addGap(44, 44, 44)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMateriaPrima, 0, 401, Short.MAX_VALUE)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addComponent(jtxQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbFabrica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lFabrica)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lFabrica)
                    .addComponent(cbFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQtd)
                    .addComponent(jtxQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMateriaPrima))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
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
        armazem = new ArmazemFabrica();
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
    private javax.swing.JComboBox cbMateriaPrima;
    private javax.swing.JTextField jtxQtd;
    private javax.swing.JLabel lFabrica;
    private javax.swing.JLabel lMateriaPrima;
    private javax.swing.JLabel lQtd;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
