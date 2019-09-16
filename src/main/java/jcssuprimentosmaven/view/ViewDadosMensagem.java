/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jcssuprimentosmaven.dao.JogadorDAO;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Mensagem;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosMensagem extends javax.swing.JInternalFrame {

    private List destinatarios;
    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Mensagem mensagem;
    private boolean error = false;
    private Jogador jogador;
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosMensagem() {
        initComponents();
        JogadorDAO jdao = new JogadorDAO();
        this.setDestinatarios(jdao.listar());
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(this.getDestinatarios().toArray());
        this.getCbDestinatario().setModel(defaultComboBoxModel);
        this.jtxRemetente.setText(this.jogador.getLogin());
    }

    public ViewDadosMensagem(Jogador jogador) {
        initComponents();
        JogadorDAO jdao = new JogadorDAO();
        this.setDestinatarios(jdao.listar());
        destinatarios.add("Todos");
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(this.getDestinatarios().toArray());
        this.getCbDestinatario().setModel(defaultComboBoxModel);
        this.jtxRemetente.setText(jogador.getLogin());
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List destinatarios) {
        this.destinatarios = destinatarios;
    }

    public JComboBox getCbDestinatario() {
        return cbDestinatario;
    }

    public void setCbDestinatario(JComboBox cbDestinatario) {
        this.cbDestinatario = cbDestinatario;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem preencherMensagem() {
        //if (mensagem != null) {
        mensagem = new Mensagem();
        mensagem.setAssunto(this.jtxAssunto.getText());
        mensagem.setRemetente(this.jtxRemetente.getText());
        Jogador jogadorTemp = (Jogador) this.cbDestinatario.getSelectedItem();
        mensagem.setDestinatario(jogadorTemp.getLogin());
        mensagem.setTexto(this.jtxTexto.getText());
        //} else {
        //  mensagem = new Mensagem();
        //}

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Mensagem>> constraintViolation = validator.validate(mensagem);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Mensagem> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return mensagem;
    }

    public void exibirMensagem(Mensagem mensagem) {
        this.limparCampos();
        //if (mensagem != null) {
            this.cbDestinatario.setSelectedItem(mensagem.getDestinatario());
            //this.jtxRemetente.setText(mensagem.getRemetente());
            this.jtxTexto.setText(mensagem.getTexto());
            this.jtxAssunto.setText(mensagem.getAssunto());

        //}
    }

    public void bEnviarAddActionListener(ActionListener ouvinte) {
        bEnviar.addActionListener(ouvinte);
    }

    public boolean getErro() {
        return this.error;
    }

    public void setError(boolean valor) {
        this.error = valor;
    }

    public void limparCampos() {
        this.cbDestinatario.setSelectedIndex(0);
        this.jtxAssunto.setText(null);
        this.jtxTexto.setText(null);
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
        lDestinario = new javax.swing.JLabel();
        cbDestinatario = new javax.swing.JComboBox();
        bLimpar = new javax.swing.JButton();
        bFechar = new javax.swing.JButton();
        bEnviar = new javax.swing.JButton();
        lTexto = new javax.swing.JLabel();
        lRemetente = new javax.swing.JLabel();
        jtxRemetente = new javax.swing.JTextField();
        lAssunto = new javax.swing.JLabel();
        jtxAssunto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxTexto = new javax.swing.JTextArea();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Mensagem");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mensagem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lDestinario.setText("Destinatário");

        cbDestinatario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "Todos" }));

        bLimpar.setText("Limpar");
        bLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimparActionPerformed(evt);
            }
        });

        bFechar.setText("Fechar");
        bFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFecharActionPerformed(evt);
            }
        });

        bEnviar.setText("Enviar");

        lTexto.setText("Texto");

        lRemetente.setText("Remetente");

        jtxRemetente.setEnabled(false);

        lAssunto.setText("Assunto");

        jtxTexto.setColumns(20);
        jtxTexto.setRows(5);
        jScrollPane1.setViewportView(jtxTexto);

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(bEnviar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addComponent(bLimpar)
                .addGap(18, 18, 18)
                .addComponent(bFechar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lTexto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosJuizLayout.createSequentialGroup()
                    .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lDestinario)
                        .addComponent(lRemetente)
                        .addComponent(lAssunto))
                    .addGap(60, 60, 60)
                    .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtxRemetente)
                        .addComponent(cbDestinatario, 0, 386, Short.MAX_VALUE)
                        .addComponent(jtxAssunto))))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lRemetente)
                    .addComponent(jtxRemetente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDestinario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lAssunto)
                    .addComponent(jtxAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lTexto)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLimpar)
                    .addComponent(bFechar)
                    .addComponent(bEnviar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimparActionPerformed
        // TODO add your handling code here:
        limparCampos();
        mensagem = new Mensagem();
    }//GEN-LAST:event_bLimparActionPerformed

    private void bFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFecharActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bFecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bEnviar;
    protected javax.swing.JButton bFechar;
    protected javax.swing.JButton bLimpar;
    private javax.swing.JComboBox cbDestinatario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtxAssunto;
    private javax.swing.JTextField jtxRemetente;
    private javax.swing.JTextArea jtxTexto;
    private javax.swing.JLabel lAssunto;
    private javax.swing.JLabel lDestinario;
    private javax.swing.JLabel lRemetente;
    private javax.swing.JLabel lTexto;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
