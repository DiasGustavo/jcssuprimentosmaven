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
import jcssuprimentosmaven.controller.ArmazemDistribuicaoController;
import jcssuprimentosmaven.controller.DistribuidorController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueDistribuicaoController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.dao.EstoqueDistribuicaoDAO;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Distribuidor;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosDistribuidor extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Distribuidor distribuidor;
    private boolean error = false;
    private List<Fornecedor> fornecedores;
    private List<Transportadora> transportadoras;
    private List<Jogador> participantes;
    private List<Rodada> rodadas;
    private List<Fabrica> fabricas;
    private List estoques;
    private Jogador jogador;


    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosDistribuidor() {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\empresa_icon.png")); // NOI18N

        //carregar as empresas
        EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
        this.setEstoques(eddao.listar());
        DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getEstoques().toArray());
        this.getCbEstoque().setModel(defaultComboBoxModelEmpresa);
    }

    public ViewDadosDistribuidor(Jogador jogador) {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\empresa_icon.png")); // NOI18N

        this.jogador = jogador;

        //carregar as empresas
        if (jogador.getFuncao() == 1) {
            EmpresaController empresaController = new EmpresaController();
            List empresas = empresaController.buscarPorJogador(this.jogador);
            Empresa empresa = (Empresa) empresas.get(0);

            FabricaController fabricaController = new FabricaController();
            List fabricas = fabricaController.buscarPorEmpresa(empresa);
            Fabrica fabrica = (Fabrica) fabricas.get(0);

            ArmazemDistribuicaoController armazemDistribuicaController = new ArmazemDistribuicaoController();
            List armazens = armazemDistribuicaController.buscarPorFabrica(fabrica);
            ArmazemDistribuicao armazem = (ArmazemDistribuicao) armazens.get(0);

            EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();
            List estoques = estoqueDistribuicaoController.buscarPorArmazem(armazem);

            this.setEstoques(estoques);
            DefaultComboBoxModel defaultComboBoxModelEstoque = new DefaultComboBoxModel(this.getEstoques().toArray());
            this.getCbEstoque().setModel(defaultComboBoxModelEstoque);
        } else {
            EstoqueDistribuicaoDAO eddao = new EstoqueDistribuicaoDAO();
            this.setEstoques(eddao.listar());
            DefaultComboBoxModel defaultComboBoxModelEmpresa = new DefaultComboBoxModel(this.getEstoques().toArray());
            this.getCbEstoque().setModel(defaultComboBoxModelEmpresa);
        }
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

    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List getEstoques() {
        return estoques;
    }

    public void setEstoques(List estoques) {
        this.estoques = estoques;
    }

    public JComboBox getCbEstoque() {
        return cbEstoque;
    }

    public void setCbEstoque(JComboBox cbEstoque) {
        this.cbEstoque = cbEstoque;
    }

    public Distribuidor preencherDistribuidor() {

        distribuidor.setNomeFantasia(this.jtxNomeFantasia.getText());
        distribuidor.setPrecoCusto(new BigDecimal(this.jtxCusto.getText()));
        distribuidor.setPrecoVenda(new BigDecimal(this.jtxVenda.getText()));
        distribuidor.setEstoqueDistribuicao((EstoqueDistribuicao) this.cbEstoque.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Distribuidor>> constraintViolation = validator.validate(distribuidor);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Distribuidor> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return distribuidor;
    }

    public void exibirDistribuidor(Distribuidor distribuidor) {
        this.limparCampos();
        if (distribuidor != null) {
            this.jtxNomeFantasia.setText(distribuidor.getNomeFantasia());
            this.jtxCusto.setText(distribuidor.getPrecoCusto().toEngineeringString());
            this.jtxVenda.setText(distribuidor.getPrecoVenda().toEngineeringString());
            this.cbEstoque.setSelectedItem(distribuidor.getEstoqueDistribuicao());
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

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public void limparCampos() {
        this.jtxNomeFantasia.setText(null);
        this.jtxCusto.setText(null);
        this.jtxVenda.setText(null);
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
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        lNomeFantasia = new javax.swing.JLabel();
        jtxNomeFantasia = new javax.swing.JTextField();
        lCusto = new javax.swing.JLabel();
        jtxCusto = new javax.swing.JTextField();
        lVenda = new javax.swing.JLabel();
        jtxVenda = new javax.swing.JTextField();
        lEstoque = new javax.swing.JLabel();
        cbEstoque = new javax.swing.JComboBox();
        lLogo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Distribuidor");

        pDadosEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Distribuidor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        lCusto.setText("Preço de Custo");

        lVenda.setText("Preço de Venda");

        lEstoque.setText("Estoque Distribuição");

        javax.swing.GroupLayout pDadosEmpresaLayout = new javax.swing.GroupLayout(pDadosEmpresa);
        pDadosEmpresa.setLayout(pDadosEmpresaLayout);
        pDadosEmpresaLayout.setHorizontalGroup(
            pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNomeFantasia)
                            .addComponent(lCusto)
                            .addComponent(lVenda)
                            .addComponent(lEstoque))
                        .addGap(18, 18, 18)
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtxCusto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxVenda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEstoque, 0, 257, Short.MAX_VALUE)
                            .addComponent(jtxNomeFantasia)))
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
                        .addComponent(bExcluir)))
                .addContainerGap(22, Short.MAX_VALUE))
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
                    .addComponent(lCusto)
                    .addComponent(jtxCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVenda)
                    .addComponent(jtxVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEstoque)
                    .addComponent(cbEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
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
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        distribuidor = new Distribuidor();
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
    private javax.swing.JComboBox cbEstoque;
    private javax.swing.JTextField jtxCusto;
    private javax.swing.JTextField jtxNomeFantasia;
    private javax.swing.JTextField jtxVenda;
    private javax.swing.JLabel lCusto;
    private javax.swing.JLabel lEstoque;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lNomeFantasia;
    private javax.swing.JLabel lVenda;
    private javax.swing.JPanel pDadosEmpresa;
    // End of variables declaration//GEN-END:variables
}
