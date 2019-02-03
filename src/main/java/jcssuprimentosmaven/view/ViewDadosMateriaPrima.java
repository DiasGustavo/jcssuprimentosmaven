/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import jcssuprimentosmaven.constantes.StatusEnum;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jcssuprimentosmaven.dao.FornecedorDAO;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 25/07/2018
 * @version 1.0
 * obs.: Remoção do atributo taxa diária
 */
public class ViewDadosMateriaPrima extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private MateriaPrima materiaPrima;
    private boolean error = false;
    private List<Fornecedor> listaFornecedores;
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosMateriaPrima() {
        initComponents();    
        for(StatusEnum status: StatusEnum.values()){
            cbStatus.addItem(status.getStatus());
        }
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\materiaPrima_ico.png"));
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }
    
    public MateriaPrima preencherMateriaPrima() {
        //materiaPrima = new MateriaPrima();

        materiaPrima.setDescricao(this.txDescricao.getText());
        materiaPrima.setQuantidade(this.txQuantidade.getText());
        BigDecimal preco = new BigDecimal(this.txPreco.getText());
        materiaPrima.setPreco(preco);
        materiaPrima.setTempoEntrega(this.txTempo.getText());
        materiaPrima.setStatus((String)this.cbStatus.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MateriaPrima>> constraintViolation = validator.validate(materiaPrima);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<MateriaPrima> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return materiaPrima;
    }

    public void exibirMateriaPrima(MateriaPrima materiaPrima) {
        this.limparCampos();
        if (materiaPrima != null) {
            this.txDescricao.setText(materiaPrima.getDescricao());
            this.txPreco.setText(materiaPrima.getPreco().toEngineeringString());           
            this.txQuantidade.setText(materiaPrima.getQuantidade());
            this.txTempo.setText(materiaPrima.getTempoEntrega());
            this.cbStatus.setSelectedItem(materiaPrima.getStatus());
        }
    }

    public void bGravarJuizAddActionListener(ActionListener ouvinte) {
        bGravar.addActionListener(ouvinte);
    }

    public void bEditarJuizAddActionListener(ActionListener ouvinte) {
        bEditar.addActionListener(ouvinte);
    }

    public void bExcluirJuizAddActionListener(ActionListener ouvinte) {
        bExcluir.addActionListener(ouvinte);
    }

    public boolean getErro() {
        return this.error;
    }

    public void setError(boolean valor) {
        this.error = valor;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public void limparCampos() {
        
        this.txPreco.setText(null);
        this.txQuantidade.setText(null);
        this.txDescricao.setText(null);
        this.txTempo.setText(null);
        this.cbStatus.setSelectedIndex(0);
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

        pDadosMateriaPrima = new javax.swing.JPanel();
        lPreco = new javax.swing.JLabel();
        lQuantidade = new javax.swing.JLabel();
        txPreco = new javax.swing.JTextField();
        txQuantidade = new javax.swing.JTextField();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lDescricao = new javax.swing.JLabel();
        txDescricao = new javax.swing.JTextField();
        lTempo = new javax.swing.JLabel();
        txTempo = new javax.swing.JTextField();
        lStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Materia Prima");

        pDadosMateriaPrima.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Materia Prima", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lPreco.setText("Preço");

        lQuantidade.setText("Quantidade");

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

        lDescricao.setText("Descrição");

        lTempo.setText("Tempo Entrega");

        lStatus.setText("Status");

        javax.swing.GroupLayout pDadosMateriaPrimaLayout = new javax.swing.GroupLayout(pDadosMateriaPrima);
        pDadosMateriaPrima.setLayout(pDadosMateriaPrimaLayout);
        pDadosMateriaPrimaLayout.setHorizontalGroup(
            pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(bNovo)
                .addGap(12, 12, 12)
                .addComponent(bGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(bEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bExcluir)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                        .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPreco)
                            .addComponent(lDescricao))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                        .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lTempo)
                            .addComponent(lQuantidade)
                            .addComponent(lStatus))
                        .addGap(31, 31, 31)
                        .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pDadosMateriaPrimaLayout.setVerticalGroup(
            pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricao)
                    .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPreco)
                    .addComponent(txPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lQuantidade))
                    .addComponent(txQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lTempo))
                    .addComponent(txTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lStatus))
                .addGap(29, 29, 29)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bNovo)
                    .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bCancelar)
                        .addComponent(bGravar)
                        .addComponent(bEditar)
                        .addComponent(bExcluir))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pDadosMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pDadosMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        materiaPrima = new MateriaPrima();
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
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lPreco;
    private javax.swing.JLabel lQuantidade;
    private javax.swing.JLabel lStatus;
    private javax.swing.JLabel lTempo;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pDadosMateriaPrima;
    private javax.swing.JTextField txDescricao;
    private javax.swing.JTextField txPreco;
    private javax.swing.JTextField txQuantidade;
    private javax.swing.JTextField txTempo;
    // End of variables declaration//GEN-END:variables
}
