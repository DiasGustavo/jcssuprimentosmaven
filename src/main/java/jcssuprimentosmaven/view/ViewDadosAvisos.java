/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.JPanel;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jcssuprimentosmaven.controller.AvisoController;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosAvisos extends javax.swing.JInternalFrame {

    private List rodadas;
    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private ArmazemSuprimento armazem;
    private boolean error = false;
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosAvisos() {
        initComponents();
        AvisoController avisoController = new AvisoController();
        avisoController.setCodigo(avisoController.CodigoRecenteRodada());
        avisoController.carregarDados();
        exibirRodada(avisoController.getRodada());
    }

    public JPanel getpDadosAvisosJ() {
        return pDadosAvisosJ;
    }

    public void setpDadosAvisosJ(JPanel pDadosAvisosJ) {
        this.pDadosAvisosJ = pDadosAvisosJ;
    }

    public JPanel getpDadosAvisosP() {
        return pDadosAvisosP;
    }

    public void setpDadosAvisosP(JPanel pDadosAvisosP) {
        this.pDadosAvisosP = pDadosAvisosP;
    }

    public ArmazemSuprimento preencherArmazem() {

        armazem.setNomeFantasia(this.jtxRodada.getText());
        armazem.setTaxaDiaria(new BigDecimal(this.jtxCargaRodoviaria.getText()));
        armazem.setTaxaSeguro(new BigDecimal(this.jtxDemandaTotal.getText()));

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

    public void exibirRodada(Rodada rodada) {
        this.limparCampos();
        //if (juiz != null) {
        //aviso juiz
        if (rodada.getId() == 1) {
            this.jtxRodada.setText(rodada.getId().toString());
            this.jtxDemandaTotal.setText(rodada.getDemanda());
            String cargaRodoviaria = Integer.toString(rodada.getCargaRodoviaria());
            this.jtxCargaRodoviaria.setText(cargaRodoviaria);
            String cargaFerroviaria = Integer.toString(rodada.getCargaFerroviaria());
            this.jtxCargaFerroviaria.setText(cargaFerroviaria);
            //aviso participante
            this.jtxRodadaP.setText(rodada.getId().toString());
            this.jtxDemandaP.setText(rodada.getDemanda());
            AvisoController avisoController = new AvisoController();
            Long media = avisoController.mediaDemanda();
            this.jtxMediaDemanda.setText(media.toString());
            Long variacao = Long.parseLong("0") - media;
            this.jtxVariacaoRodada.setText(variacao.toString());
            //verifica se a rodada expirou
            Date data = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            Date data2 = calendar.getTime();

            if (data2.after(avisoController.ultimaRodada().getDataFim())) {
                //indica que a rodada terminou e o jogador não pode fazer nada
                this.jtxSituacao.setBackground(Color.RED);
            } else {
                //indica que a rodada continua ativa para os jogadores
                this.jtxSituacao.setBackground(Color.GREEN);
            }
        }else{
            this.jtxRodada.setText(rodada.getId().toString());
            this.jtxDemandaTotal.setText(rodada.getDemanda());
            String cargaRodoviaria = Integer.toString(rodada.getCargaRodoviaria());
            this.jtxCargaRodoviaria.setText(cargaRodoviaria);
            String cargaFerroviaria = Integer.toString(rodada.getCargaFerroviaria());
            this.jtxCargaFerroviaria.setText(cargaFerroviaria);
            //aviso participante
            this.jtxRodadaP.setText(rodada.getId().toString());
            this.jtxDemandaP.setText(rodada.getDemanda());
            AvisoController avisoController = new AvisoController();
            Long media = avisoController.mediaDemanda();
            this.jtxMediaDemanda.setText(media.toString());
            Long variacao = Long.parseLong(avisoController.penultimaRodada().getDemanda()) - media;
            this.jtxVariacaoRodada.setText(variacao.toString());
            //verifica se a rodada expirou
            Date data = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            Date data2 = calendar.getTime();

            if (data2.after(avisoController.ultimaRodada().getDataFim())) {
                //indica que a rodada terminou e o jogador não pode fazer nada
                this.jtxSituacao.setBackground(Color.RED);
            } else {
                //indica que a rodada continua ativa para os jogadores
                this.jtxSituacao.setBackground(Color.GREEN);
            }
        }
        //} 
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
        this.jtxCargaFerroviaria.setText(null);
        this.jtxDemandaP.setText(null);
        this.jtxMediaDemanda.setText(null);
        this.jtxRodadaP.setText(null);
        this.jtxSituacao.setText(null);
        this.jtxVariacaoRodada.setText(null);
        this.jtxRodada.setText(null);
        this.jtxCargaRodoviaria.setText(null);
        this.jtxDemandaTotal.setText(null);
    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        
        this.setLocation((d.width + d.width * 60 / 100), (d.height - this.getSize().height * 50 / 100) / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pDadosAvisosJ = new javax.swing.JPanel();
        lRodada = new javax.swing.JLabel();
        lCargaFerroviaria = new javax.swing.JLabel();
        jtxRodada = new javax.swing.JTextField();
        lDemandaTotal = new javax.swing.JLabel();
        jtxDemandaTotal = new javax.swing.JTextField();
        lCargaRodoviaria = new javax.swing.JLabel();
        jtxCargaRodoviaria = new javax.swing.JTextField();
        jtxCargaFerroviaria = new javax.swing.JTextField();
        pDadosAvisosP = new javax.swing.JPanel();
        jtxMediaDemanda = new javax.swing.JTextField();
        lDemanda = new javax.swing.JLabel();
        jtxVariacaoRodada = new javax.swing.JTextField();
        jtxRodadaP = new javax.swing.JTextField();
        lMediaDemanda = new javax.swing.JLabel();
        lRodada1 = new javax.swing.JLabel();
        jtxDemandaP = new javax.swing.JTextField();
        lVariacaoRodada = new javax.swing.JLabel();
        lSituacao = new javax.swing.JLabel();
        jtxSituacao = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Avisos");

        pDadosAvisosJ.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avisos - Juiz", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lRodada.setText("Rodada");

        lCargaFerroviaria.setText("Carga Completa Ferroviária");

        jtxRodada.setEnabled(false);

        lDemandaTotal.setText("Demanda Total");

        jtxDemandaTotal.setEnabled(false);

        lCargaRodoviaria.setText("Carga Completa Rodoviária");

        jtxCargaRodoviaria.setEnabled(false);

        jtxCargaFerroviaria.setEnabled(false);

        javax.swing.GroupLayout pDadosAvisosJLayout = new javax.swing.GroupLayout(pDadosAvisosJ);
        pDadosAvisosJ.setLayout(pDadosAvisosJLayout);
        pDadosAvisosJLayout.setHorizontalGroup(
            pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAvisosJLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lDemandaTotal)
                    .addComponent(lCargaRodoviaria)
                    .addComponent(lCargaFerroviaria)
                    .addComponent(lRodada))
                .addGap(18, 18, 18)
                .addGroup(pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtxCargaRodoviaria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jtxDemandaTotal, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxRodada, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxCargaFerroviaria))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pDadosAvisosJLayout.setVerticalGroup(
            pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAvisosJLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lRodada)
                    .addComponent(jtxRodada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDemandaTotal)
                    .addComponent(jtxDemandaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCargaRodoviaria)
                    .addComponent(jtxCargaRodoviaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCargaFerroviaria)
                    .addComponent(jtxCargaFerroviaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pDadosAvisosP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avisos - Participante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jtxMediaDemanda.setEnabled(false);

        lDemanda.setText("Demanda");

        jtxVariacaoRodada.setEnabled(false);

        jtxRodadaP.setEnabled(false);

        lMediaDemanda.setText("Média de Demanda");

        lRodada1.setText("Rodada");

        jtxDemandaP.setEnabled(false);

        lVariacaoRodada.setText("Variação da Demanda");

        lSituacao.setText("Situação da Rodada");

        jtxSituacao.setEnabled(false);

        javax.swing.GroupLayout pDadosAvisosPLayout = new javax.swing.GroupLayout(pDadosAvisosP);
        pDadosAvisosP.setLayout(pDadosAvisosPLayout);
        pDadosAvisosPLayout.setHorizontalGroup(
            pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAvisosPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lDemanda)
                    .addComponent(lMediaDemanda)
                    .addComponent(lVariacaoRodada)
                    .addComponent(lRodada1)
                    .addComponent(lSituacao))
                .addGap(51, 51, 51)
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxVariacaoRodada, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(jtxMediaDemanda)
                    .addComponent(jtxDemandaP)
                    .addComponent(jtxRodadaP)
                    .addComponent(jtxSituacao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pDadosAvisosPLayout.setVerticalGroup(
            pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAvisosPLayout.createSequentialGroup()
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lRodada1)
                    .addComponent(jtxRodadaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDemanda)
                    .addComponent(jtxDemandaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMediaDemanda)
                    .addComponent(jtxMediaDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVariacaoRodada)
                    .addComponent(jtxVariacaoRodada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosAvisosPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSituacao)
                    .addComponent(jtxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pDadosAvisosJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pDadosAvisosP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosAvisosJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pDadosAvisosP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jtxCargaFerroviaria;
    private javax.swing.JTextField jtxCargaRodoviaria;
    private javax.swing.JTextField jtxDemandaP;
    private javax.swing.JTextField jtxDemandaTotal;
    private javax.swing.JTextField jtxMediaDemanda;
    private javax.swing.JTextField jtxRodada;
    private javax.swing.JTextField jtxRodadaP;
    private javax.swing.JTextField jtxSituacao;
    private javax.swing.JTextField jtxVariacaoRodada;
    private javax.swing.JLabel lCargaFerroviaria;
    private javax.swing.JLabel lCargaRodoviaria;
    private javax.swing.JLabel lDemanda;
    private javax.swing.JLabel lDemandaTotal;
    private javax.swing.JLabel lMediaDemanda;
    private javax.swing.JLabel lRodada;
    private javax.swing.JLabel lRodada1;
    private javax.swing.JLabel lSituacao;
    private javax.swing.JLabel lVariacaoRodada;
    private javax.swing.JPanel pDadosAvisosJ;
    private javax.swing.JPanel pDadosAvisosP;
    // End of variables declaration//GEN-END:variables
}
