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
import jcssuprimentosmaven.dao.DistribuidorDAO;
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.dao.FornecedorDAO;
import jcssuprimentosmaven.dao.JogadorDAO;
import jcssuprimentosmaven.dao.RodadaDAO;
import jcssuprimentosmaven.dao.TransportadoraDAO;
import jcssuprimentosmaven.domain.Distribuidor;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 04/08/2018
 * @version 1.0
 * obs.: remoção de alguns carregamentos
 */
public class ViewDadosEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Empresa empresa;
    private boolean error = false;
    private List<Fornecedor> fornecedores;
    private List<Transportadora> transportadoras;
    private List<Jogador> participantes;
    private List<Fabrica> fabricas;
    


    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosEmpresa() {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\empresa_icon.png")); // NOI18N
        /*FornecedorDAO fdao = new FornecedorDAO();
        this.setFornecedores(fdao.listar());
        DefaultComboBoxModel defaultComboBoxModelFornecedor = new DefaultComboBoxModel(this.getFornecedores().toArray());
        this.getCbFornecedor().setModel(defaultComboBoxModelFornecedor);

        TransportadoraDAO tdao = new TransportadoraDAO();
        this.setTransportadoras(tdao.listar());
        DefaultComboBoxModel defaultComboBoxModelTransportadora = new DefaultComboBoxModel(this.getTransportadoras().toArray());
        this.getCbTranportadora().setModel(defaultComboBoxModelTransportadora);*/

        JogadorDAO jdao = new JogadorDAO();
        this.setParticipantes(jdao.listar());
        DefaultComboBoxModel defaultComboBoxModelParticipante = new DefaultComboBoxModel(this.getParticipantes().toArray());
        this.getCbParticipante().setModel(defaultComboBoxModelParticipante);

        /*RodadaDAO rdao = new RodadaDAO();
        this.setRodadas(rdao.listar());
        DefaultComboBoxModel defaultComboBoxModelRodada = new DefaultComboBoxModel(this.getRodadas().toArray());
        this.getCbRodada().setModel(defaultComboBoxModelRodada);*/

        /*FabricaDAO fbdao = new FabricaDAO();
        this.setFabricas(fbdao.listar());
        DefaultComboBoxModel defaultComboBoxModelFabrica = new DefaultComboBoxModel(this.getFabricas().toArray());
        this.getCbFabrica().setModel(defaultComboBoxModelFabrica);
        
        DistribuidorDAO distdao = new DistribuidorDAO();
        this.setDistribuidores(distdao.listar());
        DefaultComboBoxModel defaultComboBoxModelDistribuidor = new DefaultComboBoxModel(this.getDistribuidores().toArray());
        this.getCbDistribuidor().setModel(defaultComboBoxModelDistribuidor);*/
    }

    public List<Fabrica> getFabricas() {
        return fabricas;
    }

    public void setFabricas(List<Fabrica> fabricas) {
        this.fabricas = fabricas;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public List<Jogador> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Jogador> participantes) {
        this.participantes = participantes;
    }

    /*public List<Rodada> getRodadas() {
        return rodadas;
    }

    public void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }*/

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    /*public JComboBox getCbFornecedor() {
        return cbFornecedor;
    }

    public void setCbFornecedor(JComboBox cbFornecedor) {
        this.cbFornecedor = cbFornecedor;
    }*/

    public JComboBox getCbParticipante() {
        return cbParticipante;
    }

    public void setCbParticipante(JComboBox cbParticipante) {
        this.cbParticipante = cbParticipante;
    }

    /*public JComboBox getCbRodada() {
        return cbRodada;
    }

    public void setCbRodada(JComboBox cbRodada) {
        this.cbRodada = cbRodada;
    }*/

   /*public JComboBox getCbTranportadora() {
        return cbTranportadora;
    }

    public void setCbTranportadora(JComboBox cbTranportadora) {
        this.cbTranportadora = cbTranportadora;
    }

    public JComboBox getCbFabrica() {
        return cbFabrica;
    }

    public void setCbFabrica(JComboBox cbFabrica) {
        this.cbFabrica = cbFabrica;
    }

    public JComboBox getCbDistribuidor() {
        return cbDistribuidor;
    }

    public void setCbDistribuidor(JComboBox cbDistribuidor) {
        this.cbDistribuidor = cbDistribuidor;
    }

    public List<Distribuidor> getDistribuidores() {
        return distribuidores;
    }

    public void setDistribuidores(List<Distribuidor> distribuidores) {
        this.distribuidores = distribuidores;
    }*/
    
    
    
    

    public Empresa preencherEmpresa() {

        empresa.setNomeFantasia(this.jtxNomeFantasia.getText());
        empresa.setJogador((Jogador) this.cbParticipante.getSelectedItem());
        /*empresa.setFornecedor((Fornecedor) this.cbFornecedor.getSelectedItem());
        empresa.setTransportadora((Transportadora) this.cbTranportadora.getSelectedItem());*/
        empresa.setLogomarca(null);
        //empresa.setRodada((Rodada) this.cbRodada.getSelectedItem());
        empresa.setCapitalAtual(new BigDecimal(this.jtxCapitalAtual.getText()));
        empresa.setCapitalInicial(new BigDecimal(this.jtxCapitalInicial.getText()));
        empresa.setMargeLucro(new BigDecimal(this.jtxMargemLucro.getText()));
        /*empresa.setFabrica((Fabrica)this.cbFabrica.getSelectedItem());
        empresa.setDistribuidor((Distribuidor) this.cbDistribuidor.getSelectedItem());*/

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Empresa>> constraintViolation = validator.validate(empresa);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Empresa> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return empresa;
    }

    public void exibirEmpresa(Empresa empresa) {
        this.limparCampos();
        if (empresa != null) {
            this.jtxNomeFantasia.setText(empresa.getNomeFantasia());
            this.cbParticipante.setSelectedItem(empresa.getJogador());
            /*this.cbFornecedor.setSelectedItem(empresa.getFornecedor());
            this.cbTranportadora.setSelectedItem(empresa.getTransportadora());*/
            //this.cbRodada.setSelectedItem(empresa.getRodada());
            //this.cbDistribuidor.setSelectedItem(empresa.getDistribuidor());
            this.jtxCapitalAtual.setText(empresa.getCapitalAtual().toEngineeringString());
            this.jtxCapitalInicial.setText(empresa.getCapitalInicial().toEngineeringString());            
            this.jtxMargemLucro.setText(empresa.getMargeLucro().toEngineeringString());
        }
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void limparCampos() {
        this.jtxNomeFantasia.setText(null);
        //this.cbFornecedor.setSelectedIndex(0);
        this.cbParticipante.setSelectedIndex(0);
        //this.cbRodada.setSelectedIndex(0);
        //this.cbTranportadora.setSelectedIndex(0);
        //this.cbFabrica.setSelectedIndex(0);
        //this.cbDistribuidor.setSelectedIndex(0);
        this.jtxCapitalAtual.setText(null);
        this.jtxCapitalInicial.setText(null);
        this.jtxMargemLucro.setText(null);
        
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

        pDadosEmpresa = new javax.swing.JPanel();
        lParticipante = new javax.swing.JLabel();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lNomeFantasia = new javax.swing.JLabel();
        jtxNomeFantasia = new javax.swing.JTextField();
        cbParticipante = new javax.swing.JComboBox();
        lCapitalInicial = new javax.swing.JLabel();
        jtxCapitalInicial = new javax.swing.JTextField();
        lCapitalAtual = new javax.swing.JLabel();
        jtxCapitalAtual = new javax.swing.JTextField();
        lMlucro = new javax.swing.JLabel();
        jtxMargemLucro = new javax.swing.JTextField();
        lLogo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Empresa");

        pDadosEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lParticipante.setText("Equipe");

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

        lNomeFantasia.setText("Nome Fantasia");

        cbParticipante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lCapitalInicial.setText("Capital Inicial");

        lCapitalAtual.setText("Capital Atual");

        lMlucro.setText("Margem de Lucro");

        javax.swing.GroupLayout pDadosEmpresaLayout = new javax.swing.GroupLayout(pDadosEmpresa);
        pDadosEmpresa.setLayout(pDadosEmpresaLayout);
        pDadosEmpresaLayout.setHorizontalGroup(
            pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosEmpresaLayout.createSequentialGroup()
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
                    .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lParticipante)
                            .addComponent(lNomeFantasia)
                            .addComponent(lCapitalInicial)
                            .addComponent(lCapitalAtual)
                            .addComponent(lMlucro))
                        .addGap(22, 22, 22)
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtxNomeFantasia, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(cbParticipante, 0, 265, Short.MAX_VALUE))
                            .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtxCapitalAtual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jtxCapitalInicial, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jtxMargemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pDadosEmpresaLayout.setVerticalGroup(
            pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomeFantasia)
                    .addComponent(jtxNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lParticipante)
                    .addComponent(cbParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCapitalInicial)
                    .addComponent(jtxCapitalInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCapitalAtual)
                    .addComponent(jtxCapitalAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMlucro)
                    .addComponent(jtxMargemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(pDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        empresa = new Empresa();
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
    private javax.swing.JComboBox cbParticipante;
    private javax.swing.JTextField jtxCapitalAtual;
    private javax.swing.JTextField jtxCapitalInicial;
    private javax.swing.JTextField jtxMargemLucro;
    private javax.swing.JTextField jtxNomeFantasia;
    private javax.swing.JLabel lCapitalAtual;
    private javax.swing.JLabel lCapitalInicial;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lMlucro;
    private javax.swing.JLabel lNomeFantasia;
    private javax.swing.JLabel lParticipante;
    private javax.swing.JPanel pDadosEmpresa;
    // End of variables declaration//GEN-END:variables
}
