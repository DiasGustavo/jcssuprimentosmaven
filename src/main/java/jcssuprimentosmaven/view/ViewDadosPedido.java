/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import jcssuprimentosmaven.dao.ArmazemSuprimentoDAO;
import jcssuprimentosmaven.dao.EmpresaDAO;
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.dao.MateriaPrimaDAO;
import jcssuprimentosmaven.dao.MeioTransporteDAO;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.domain.Pedido;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class ViewDadosPedido extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Pedido pedido;
    private boolean error = false;
    private List materias;
    private List armazens;
    private List transportes;
    private Jogador jogador;
    
    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosPedido() {
        initComponents();

        //carregar as materias prima
        MateriaPrimaDAO mpdao = new MateriaPrimaDAO();
        this.setMaterias(mpdao.listar());
        DefaultComboBoxModel defaultComboBoxModelFabrica = new DefaultComboBoxModel(this.getMaterias().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModelFabrica);
        //carrregar os armazens
        ArmazemSuprimentoDAO asdao = new ArmazemSuprimentoDAO();
        this.setArmazens(asdao.listar());
        DefaultComboBoxModel defaultComboBoxModelArmazem = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelArmazem);
        //Meio de Transporte
        MeioTransporteDAO mtdao = new MeioTransporteDAO();
        this.setTransportes(mtdao.listar());
        DefaultComboBoxModel defaultComboBoxModelEstoque = new DefaultComboBoxModel(this.getTransportes().toArray());
        this.getCbMeioTransporte().setModel(defaultComboBoxModelEstoque);
        this.lTotal.setVisible(false);

        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\caixa110_110.png"));
    }
    
    public ViewDadosPedido(Jogador jogador) {
        initComponents();
        
        this.jogador = jogador;
        //carregar as materias prima
        MateriaPrimaDAO mpdao = new MateriaPrimaDAO();
        this.setMaterias(mpdao.listar());
        DefaultComboBoxModel defaultComboBoxModelFabrica = new DefaultComboBoxModel(this.getMaterias().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModelFabrica);
        //carrregar os armazens
        EmpresaDAO edao = new EmpresaDAO();
        FabricaDAO fdao = new FabricaDAO();
        Empresa empresa = (Empresa) edao.buscarPorJogador(this.jogador).get(0);
        Fabrica fabrica = (Fabrica) fdao.buscarPorEmpresa(empresa).get(0);
        ArmazemSuprimentoDAO asdao = new ArmazemSuprimentoDAO();
        this.setArmazens(asdao.buscarPorFabrica(fabrica));
        DefaultComboBoxModel defaultComboBoxModelArmazem = new DefaultComboBoxModel(this.getArmazens().toArray());
        this.getCbArmazem().setModel(defaultComboBoxModelArmazem);
        //Meio de Transporte
        MeioTransporteDAO mtdao = new MeioTransporteDAO();
        this.setTransportes(mtdao.listar());
        DefaultComboBoxModel defaultComboBoxModelEstoque = new DefaultComboBoxModel(this.getTransportes().toArray());
        this.getCbMeioTransporte().setModel(defaultComboBoxModelEstoque);
        this.lTotal.setText("0.00");
        this.lTotal.setVisible(true);
        logo.setIcon(new javax.swing.ImageIcon(".\\imagem\\caixa110_110.png"));
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
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

    public List getMaterias() {
        return materias;
    }

    public void setMaterias(List materias) {
        this.materias = materias;
    }

    public List getTransportes() {
        return transportes;
    }

    public void setTransportes(List transportes) {
        this.transportes = transportes;
    }

    public JComboBox getCbMateriaPrima() {
        return cbMateriaPrima;
    }

    public void setCbMateriaPrima(JComboBox cbMateriaPrima) {
        this.cbMateriaPrima = cbMateriaPrima;
    }

    public JComboBox getCbMeioTransporte() {
        return cbMeioTransporte;
    }

    public void setCbMeioTransporte(JComboBox cbMeioTransporte) {
        this.cbMeioTransporte = cbMeioTransporte;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public JTextField getJtxQuantidade() {
        return jtxQuantidade;
    }

    public void setJtxQuantidade(JTextField jtxQuantidade) {
        this.jtxQuantidade = jtxQuantidade;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Pedido preencherPedido() {

        pedido.setQuantidade(new BigDecimal(this.jtxQuantidade.getText()));
        pedido.setDescricao(this.jtxDescricao.getText());
        pedido.setMateriPrima((MateriaPrima) this.cbMateriaPrima.getSelectedItem());
        pedido.setArmazemSuprimento((ArmazemSuprimento) this.cbArmazem.getSelectedItem());
        pedido.setMeioTransporte((MeioTransporte) this.cbMeioTransporte.getSelectedItem());
        pedido.setValor(new BigDecimal(this.lTotal.getText()));
        pedido.setStatus((String) cbStatus.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Pedido>> constraintViolation = validator.validate(pedido);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Pedido> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return pedido;
    }

    public void exibirPedido(Pedido pedido) {
        this.limparCampos();
        //if (juiz != null) {
        this.jtxQuantidade.setText(pedido.getQuantidade().toEngineeringString());
        this.jtxDescricao.setText(pedido.getDescricao());
        this.cbArmazem.setSelectedItem(pedido.getArmazemSuprimento());
        this.cbMateriaPrima.setSelectedItem(pedido.getMateriPrima());
        this.cbMeioTransporte.setSelectedItem(pedido.getMeioTransporte());
        if(pedido.getValor().equals(null)){
            this.lTotal.setText("0,00");
        }else{
            this.lTotal.setText(pedido.getValor().toEngineeringString());
        }
        this.cbStatus.setSelectedItem(pedido.getStatus());
        //} 
    }

    public void bGravarProducaoAddActionListener(ActionListener ouvinte) {
        bGravar.addActionListener(ouvinte);
    }

    public void bEditarProducaoAddActionListener(ActionListener ouvinte) {
        bEditar.addActionListener(ouvinte);
    }

    public void bExcluirProducaoAddActionListener(ActionListener ouvinte) {
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
        this.jtxDescricao.setText(null);
        this.cbArmazem.setSelectedIndex(0);
        this.cbMateriaPrima.setSelectedIndex(0);
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
        lMateriaPrima = new javax.swing.JLabel();
        cbMateriaPrima = new javax.swing.JComboBox();
        lArmazem = new javax.swing.JLabel();
        cbArmazem = new javax.swing.JComboBox();
        lDescricao = new javax.swing.JLabel();
        jtxDescricao = new javax.swing.JTextField();
        lQuantidade = new javax.swing.JLabel();
        jtxQuantidade = new javax.swing.JTextField();
        lMeioTransporte = new javax.swing.JLabel();
        cbMeioTransporte = new javax.swing.JComboBox();
        lStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        logo = new javax.swing.JLabel();
        lValor = new javax.swing.JLabel();
        lTotal = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Pedido");

        pDadosJuiz.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Produção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        lMateriaPrima.setText("Matéria Prima");

        cbMateriaPrima.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lArmazem.setText("Armazém");
        lArmazem.setToolTipText("Armazém de Suprimento que está solicitando");

        cbArmazem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lDescricao.setText("Descrição");

        lQuantidade.setText("Quantidade");

        jtxQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxQuantidadeKeyPressed(evt);
            }
        });

        lMeioTransporte.setText("Meio de Transporte");

        cbMeioTransporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lStatus.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Entregue" }));

        javax.swing.GroupLayout pDadosJuizLayout = new javax.swing.GroupLayout(pDadosJuiz);
        pDadosJuiz.setLayout(pDadosJuizLayout);
        pDadosJuizLayout.setHorizontalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lMateriaPrima)
                            .addGroup(pDadosJuizLayout.createSequentialGroup()
                                .addComponent(bNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bGravar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bExcluir)))
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addGroup(pDadosJuizLayout.createSequentialGroup()
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDescricao)
                            .addComponent(lQuantidade)
                            .addComponent(lArmazem)
                            .addComponent(lMeioTransporte)
                            .addComponent(lStatus))
                        .addGap(21, 21, 21)
                        .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtxQuantidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtxDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbArmazem, javax.swing.GroupLayout.Alignment.LEADING, 0, 284, Short.MAX_VALUE)
                                    .addComponent(cbMateriaPrima, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbMeioTransporte, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pDadosJuizLayout.setVerticalGroup(
            pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosJuizLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMateriaPrima)
                    .addComponent(cbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbArmazem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lArmazem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMeioTransporte)
                    .addComponent(cbMeioTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuantidade)
                    .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDescricao)
                    .addComponent(jtxDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStatus)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pDadosJuizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNovo)
                    .addComponent(bCancelar)
                    .addComponent(bExcluir)
                    .addComponent(bGravar)
                    .addComponent(bEditar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        lValor.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lValor.setText("Valor:");

        lTotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lTotal.setForeground(new java.awt.Color(0, 204, 255));
        lTotal.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lTotal)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosJuiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lValor)
                            .addComponent(lTotal))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        pedido = new Pedido();
    }//GEN-LAST:event_bNovoActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed
    /**
     * Cadastra um pedido e ao apertar ENTER calcula-se o valor do pedido.
     * @param KeyEvent 
     */
    private void jtxQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxQuantidadeKeyPressed
        int quantidade = Integer.valueOf(this.jtxQuantidade.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            MateriaPrima materia = (MateriaPrima) this.cbMateriaPrima.getSelectedItem();
            if (quantidade <= Integer.valueOf(materia.getQuantidade())) {
                MeioTransporte meio = (MeioTransporte) this.cbMeioTransporte.getSelectedItem();
                BigDecimal valor = meio.getTaxa_transporte().multiply(new BigDecimal(quantidade)).add(meio.getTaxa_seguro().multiply(new BigDecimal(quantidade)));
                this.lTotal.setText(valor.toEngineeringString());
                this.lTotal.setVisible(true);
                this.cbStatus.setSelectedIndex(0);
            }else{
                ViewUtil.addMsgErro("Quantidade em estoque menor do que o solicitado!");
            }
        }
    }//GEN-LAST:event_jtxQuantidadeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton bEditar;
    protected javax.swing.JButton bExcluir;
    protected javax.swing.JButton bGravar;
    protected javax.swing.JButton bNovo;
    private javax.swing.JComboBox cbArmazem;
    private javax.swing.JComboBox cbMateriaPrima;
    private javax.swing.JComboBox cbMeioTransporte;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JTextField jtxDescricao;
    private javax.swing.JTextField jtxQuantidade;
    private javax.swing.JLabel lArmazem;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lMateriaPrima;
    private javax.swing.JLabel lMeioTransporte;
    private javax.swing.JLabel lQuantidade;
    private javax.swing.JLabel lStatus;
    private javax.swing.JLabel lTotal;
    private javax.swing.JLabel lValor;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pDadosJuiz;
    // End of variables declaration//GEN-END:variables
}
