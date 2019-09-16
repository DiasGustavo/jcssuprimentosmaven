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
import jcssuprimentosmaven.constantes.StatusEnum;
import jcssuprimentosmaven.dao.TransportadoraDAO;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 25/07/2018
 * @version 1.0
 * obs.: Adicionando o atributo destino
 */
public class ViewDadosMeioTransporte extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private MeioTransporte meioTransporte;
    private boolean error = false;
    private List<Transportadora> listaTransportadora;
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosMeioTransporte() {
        initComponents();
        for(StatusEnum status: StatusEnum.values()){
            cbStatus.addItem(status.getStatus());
        }
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\transporte_icon.png"));
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public MeioTransporte getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(MeioTransporte meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    public List<Transportadora> getListaTransportadora() {
        return listaTransportadora;
    }

    public void setListaTransportadora(List<Transportadora> listaTransportadora) {
        this.listaTransportadora = listaTransportadora;
    }

       

    public MeioTransporte preencherMeioTransporte() {
        //materiaPrima = new MateriaPrima();

        meioTransporte.setDescricao(this.txDescricao.getText());
        meioTransporte.setTempo(this.txEntrega.getText());
        BigDecimal taxaTransporte = new BigDecimal(this.txTransporte.getText());
        meioTransporte.setTaxa_transporte(taxaTransporte);
        BigDecimal taxaSeguro = new BigDecimal(this.txEntrega.getText());
        meioTransporte.setTaxa_seguro(taxaSeguro);
        //meioTransporte.setTransportadora((Transportadora) this.cbTransportadora.getSelectedItem());
        meioTransporte.setStatus((String)this.cbStatus.getSelectedItem());
        meioTransporte.setDestino((String) this.cbDestino.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MeioTransporte>> constraintViolation = validator.validate(meioTransporte);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<MeioTransporte> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return meioTransporte;
    }

    public void exibirMeioTransporte(MeioTransporte meioTransporte) {
        this.limparCampos();
        if (meioTransporte != null) {
            this.txDescricao.setText(meioTransporte.getDescricao());
            this.txSeguro.setText(meioTransporte.getTaxa_seguro().toEngineeringString());           
            this.txTransporte.setText(meioTransporte.getTaxa_transporte().toEngineeringString());
            this.txEntrega.setText(meioTransporte.getTempo());
            //this.cbTransportadora.setSelectedItem(meioTransporte.getTransportadora());
            this.cbDestino.setSelectedItem(meioTransporte.getDestino());
        }
    }

    public void bGravarMeioTransporteAddActionListener(ActionListener ouvinte) {
        bGravar.addActionListener(ouvinte);
    }

    public void bEditarMeioTransporteAddActionListener(ActionListener ouvinte) {
        bEditar.addActionListener(ouvinte);
    }

    public void bExcluirMeioTransporteAddActionListener(ActionListener ouvinte) {
        bExcluir.addActionListener(ouvinte);
    }

    public boolean getErro() {
        return this.error;
    }

    public void setError(boolean valor) {
        this.error = valor;
    }
        
    public void limparCampos() {
        this.txEntrega.setText(null);
        this.txSeguro.setText(null);
        this.txTransporte.setText(null);
        this.txDescricao.setText(null);
        //this.cbTransportadora.setSelectedIndex(0);
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
        ltaxaSeguro = new javax.swing.JLabel();
        ltaxaTransporte = new javax.swing.JLabel();
        ltempo = new javax.swing.JLabel();
        txSeguro = new javax.swing.JTextField();
        txTransporte = new javax.swing.JTextField();
        txEntrega = new javax.swing.JTextField();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lDescricao = new javax.swing.JLabel();
        txDescricao = new javax.swing.JTextField();
        lStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        lDestino = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox();
        logo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Meio de Transporte");

        pDadosMateriaPrima.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Meio de Transporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        ltaxaSeguro.setText("Taxa de Seguro");

        ltaxaTransporte.setText("Taxa de Transporte");

        ltempo.setText("Tempo de Entrega");

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

        lStatus.setText("Status");

        lDestino.setText("Destino");

        cbDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Armazém Suprimento", "Fábrica", "Armazém Distribuição", "Distribuidor(Empresa)" }));

        javax.swing.GroupLayout pDadosMateriaPrimaLayout = new javax.swing.GroupLayout(pDadosMateriaPrima);
        pDadosMateriaPrima.setLayout(pDadosMateriaPrimaLayout);
        pDadosMateriaPrimaLayout.setHorizontalGroup(
            pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                        .addComponent(bNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bExcluir)
                        .addContainerGap())
                    .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                        .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ltaxaTransporte)
                            .addComponent(ltempo)
                            .addComponent(ltaxaSeguro)
                            .addComponent(lDescricao)
                            .addComponent(lStatus)
                            .addComponent(lDestino))
                        .addGap(49, 49, 49)
                        .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                                .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(242, 242, 242))
                            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txSeguro, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addComponent(txTransporte)
                                        .addComponent(txEntrega)))
                                .addContainerGap(63, Short.MAX_VALUE))))))
        );
        pDadosMateriaPrimaLayout.setVerticalGroup(
            pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosMateriaPrimaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricao)
                    .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ltaxaSeguro)
                    .addComponent(txSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ltaxaTransporte)
                    .addComponent(txTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ltempo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDestino)
                    .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStatus)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNovo)
                    .addComponent(bCancelar)
                    .addComponent(bExcluir)
                    .addComponent(bGravar)
                    .addComponent(bEditar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(pDadosMateriaPrima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosMateriaPrima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        meioTransporte = new MeioTransporte();
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
    private javax.swing.JComboBox cbDestino;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lDestino;
    private javax.swing.JLabel lStatus;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel ltaxaSeguro;
    private javax.swing.JLabel ltaxaTransporte;
    private javax.swing.JLabel ltempo;
    private javax.swing.JPanel pDadosMateriaPrima;
    private javax.swing.JTextField txDescricao;
    private javax.swing.JTextField txEntrega;
    private javax.swing.JTextField txSeguro;
    private javax.swing.JTextField txTransporte;
    // End of variables declaration//GEN-END:variables
}
