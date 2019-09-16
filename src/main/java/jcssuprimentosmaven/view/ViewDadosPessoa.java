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
import jcssuprimentosmaven.dao.JogadorDAO;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Pessoa;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo
 * Criação: 10/06/2018 
 * Última Alteração 04/08/2018
 * @version 1.0
 * obs.: remoção de alguns carregamentos
 */
public class ViewDadosPessoa extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Pessoa pessoa;
    private boolean error = false;
    private List<Fornecedor> fornecedores;
    private List<Transportadora> transportadoras;
    private List<Jogador> participantes;
    private List<Fabrica> fabricas;
    


    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosPessoa() {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\icon_login.png")); // NOI18N
        JogadorDAO jdao = new JogadorDAO();
        this.setParticipantes(jdao.listar());
        DefaultComboBoxModel defaultComboBoxModelParticipante = new DefaultComboBoxModel(this.getParticipantes().toArray());
        this.getCbParticipante().setModel(defaultComboBoxModelParticipante);
        
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

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public JComboBox getCbParticipante() {
        return cbEquipe;
    }

    public void setCbParticipante(JComboBox cbParticipante) {
        this.cbEquipe = cbParticipante;
    }
   

    public Pessoa preencherPessoa() {
             
        pessoa.setNome(this.jtxNome.getText());
        pessoa.setMatricula(this.jtxMatricula.getText());
        pessoa.setEmail(this.jtxEmail.getText());
        pessoa.setJogador((Jogador)this.cbEquipe.getSelectedItem());
        int status = 0;
        if(this.jtxStatus.getSelectedItem() == "Ativo"){
            status = 1;
        }
        pessoa.setStatus(status);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Pessoa>> constraintViolation = validator.validate(pessoa);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Pessoa> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return pessoa;
    }

    public void exibirPessoa(Pessoa pessoa) {
        this.limparCampos();
        if (pessoa != null) {
            this.jtxNome.setText(pessoa.getNome());
            this.cbEquipe.setSelectedItem(pessoa.getJogador());
            this.jtxEmail.setText(pessoa.getEmail());
            this.jtxMatricula.setText(pessoa.getMatricula());            
            this.jtxStatus.setSelectedItem(pessoa.getStatus());
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    

    public void limparCampos() {
        this.jtxNome.setText(null);
        this.cbEquipe.setSelectedIndex(0);
        this.jtxEmail.setText(null);
        this.jtxMatricula.setText(null);
        this.jtxStatus.setSelectedIndex(0);
        
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
        lEquipe = new javax.swing.JLabel();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lNome = new javax.swing.JLabel();
        jtxNome = new javax.swing.JTextField();
        cbEquipe = new javax.swing.JComboBox();
        lMatricula = new javax.swing.JLabel();
        jtxMatricula = new javax.swing.JTextField();
        lEmail = new javax.swing.JLabel();
        jtxEmail = new javax.swing.JTextField();
        lStatus = new javax.swing.JLabel();
        jtxStatus = new javax.swing.JComboBox<>();
        lLogo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Aluno");

        pDadosEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Aluno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lEquipe.setText("Equipe");

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

        lNome.setText("Nome");

        cbEquipe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lMatricula.setText("Matrícula");

        lEmail.setText("E-mail");

        lStatus.setText("Stratus");

        jtxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Suspenso", " " }));

        javax.swing.GroupLayout pDadosEmpresaLayout = new javax.swing.GroupLayout(pDadosEmpresa);
        pDadosEmpresa.setLayout(pDadosEmpresaLayout);
        pDadosEmpresaLayout.setHorizontalGroup(
            pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNome)
                            .addComponent(lMatricula)
                            .addComponent(lEmail))
                        .addGap(46, 46, 46)
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxNome, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxEmail)))
                    .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosEmpresaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lEquipe)
                                    .addComponent(lStatus))
                                .addGap(55, 55, 55)
                                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbEquipe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtxStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(bNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bExcluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pDadosEmpresaLayout.setVerticalGroup(
            pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNome)
                    .addComponent(jtxNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMatricula)
                    .addComponent(jtxMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEmail)
                    .addComponent(jtxEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEquipe)
                    .addComponent(cbEquipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStatus)
                    .addComponent(jtxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
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
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        pessoa = new Pessoa();
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
    private javax.swing.JComboBox cbEquipe;
    private javax.swing.JTextField jtxEmail;
    private javax.swing.JTextField jtxMatricula;
    private javax.swing.JTextField jtxNome;
    private javax.swing.JComboBox<String> jtxStatus;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lEquipe;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lMatricula;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel lStatus;
    private javax.swing.JPanel pDadosEmpresa;
    // End of variables declaration//GEN-END:variables
}
