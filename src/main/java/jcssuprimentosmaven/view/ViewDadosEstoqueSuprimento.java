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
import jcssuprimentosmaven.dao.ArmazemSuprimentoDAO;
import jcssuprimentosmaven.dao.MateriaPrimaDAO;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosEstoqueSuprimento extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private EstoqueSuprimento estoqueSuprimento;
    private boolean error = false;
    private List materias;
    private List armazens;
    
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */

    public ViewDadosEstoqueSuprimento() {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\suprimento110_110.png")); // NOI18N      
        MateriaPrimaDAO mpdao = new MateriaPrimaDAO();
        this.setMaterias(mpdao.listar());
        DefaultComboBoxModel defaultComboBoxModelMateria = new DefaultComboBoxModel(this.getMaterias().toArray());
        this.getCbMateria().setModel(defaultComboBoxModelMateria);
        
        ArmazemSuprimentoDAO asdao = new ArmazemSuprimentoDAO();
        this.setArmazens(asdao.listar());
        DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelEmpresa);
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List getMaterias() {
        return materias;
    }

    public void setMaterias(List materias) {
        this.materias = materias;
    }

    public EstoqueSuprimento getEstoqueSuprimento() {
        return estoqueSuprimento;
    }

    public void setEstoqueSuprimento(EstoqueSuprimento estoqueSuprimento) {
        this.estoqueSuprimento = estoqueSuprimento;
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

    public JComboBox getCbMateria() {
        return cbMateria;
    }

    public void setCbMateria(JComboBox cbMateria) {
        this.cbMateria = cbMateria;
    }
    
    
    public EstoqueSuprimento preencherEstoqueSuprimento() {

        estoqueSuprimento.setQuantidade(new BigDecimal(this.jtxQuantidade.getText()));
        estoqueSuprimento.setDescricao(this.jtxDescricao.getText());
        estoqueSuprimento.setMateriaPrima((MateriaPrima)this.cbMateria.getSelectedItem());
        estoqueSuprimento.setArmazemSuprimento((ArmazemSuprimento)this.cbArmazem.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EstoqueSuprimento>> constraintViolation = validator.validate(estoqueSuprimento);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<EstoqueSuprimento> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return estoqueSuprimento;
    }

    public void exibirEstoqueSuprimento(EstoqueSuprimento estoque) {
        this.limparCampos();
        //if (juiz != null) {
        this.jtxQuantidade.setText(estoque.getQuantidade().toEngineeringString());
        this.jtxDescricao.setText(estoque.getDescricao());
        this.cbMateria.setSelectedItem(estoque.getMateriaPrima());
        this.cbArmazem.setSelectedItem(estoque.getArmazemSuprimento());
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

    public void limparCampos() {
        this.jtxQuantidade.setText(null);
        this.cbArmazem.setSelectedIndex(0);
        this.cbMateria.setSelectedIndex(0);
        this.cbArmazem.setSelectedIndex(0);
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
        lMateria = new javax.swing.JLabel();
        cbMateria = new javax.swing.JComboBox();
        lQuantidade = new javax.swing.JLabel();
        jtxQuantidade = new javax.swing.JTextField();
        lEmpresa = new javax.swing.JLabel();
        cbArmazem = new javax.swing.JComboBox();
        lDescricao = new javax.swing.JLabel();
        jtxDescricao = new javax.swing.JTextField();
        lLogo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Estoque Suprimento");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Estoque de Suprimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        lMateria.setText("Matéria Prima");

        cbMateria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lQuantidade.setText("Quantidade");

        lEmpresa.setText("Armazém Suprimento");

        cbArmazem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lDescricao.setText("Descrição");

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addComponent(bNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bExcluir)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lQuantidade)
                            .addComponent(lMateria)
                            .addComponent(lEmpresa)
                            .addComponent(lDescricao))
                        .addGap(36, 36, 36)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxDescricao)
                            .addComponent(cbMateria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbArmazem, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuantidade)
                    .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricao)
                    .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMateria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEmpresa)
                    .addComponent(cbArmazem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
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
                .addGap(34, 34, 34)
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        estoqueSuprimento = new EstoqueSuprimento();
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
    private javax.swing.JComboBox cbMateria;
    private javax.swing.JTextField jtxDescricao;
    private javax.swing.JTextField jtxQuantidade;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lEmpresa;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lMateria;
    private javax.swing.JLabel lQuantidade;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
