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
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.controller.ArmazemSuprimentoController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueSuprimentoController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.MateriaPrimaController;
import jcssuprimentosmaven.controller.MeioTransporteController;
import jcssuprimentosmaven.dao.MateriaPrimaDAO;
import jcssuprimentosmaven.dao.MeioTransporteDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Rodada;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.domain.Transporte;
import jcssuprimentosmaven.util.ViewUtil;

/**
 * @author Gustavo Criação: 10/06/2018 Última Alteração 04/08/2018
 * @version 1.0 obs.: remoção de alguns carregamentos
 */
public class ViewDadosTransporte extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDadosJuiz
     */
    private String acao;
    private Transporte transporte;
    private boolean error = false;
    private List<Rodada> rodadas;
    private List<MateriaPrima> materias;
    private List<MeioTransporte> meios;
    private List<Empresa> empresas;
    private List<ArmazemFabrica> armazensFabrica;
    private Jogador jogador;
    private String destino;

    /* para carregar o enum no combobox tem que editar a propriedade
     *  Model na guia propriedades e adicionar new javax.swing.DefaultComboBoxModel(StatusEnum.values())
     */
    public ViewDadosTransporte() {
        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\caminhao70p.png")); // NOI18N

        MeioTransporteDAO mtdao = new MeioTransporteDAO();
        this.setMeios(mtdao.listar());
        DefaultComboBoxModel defaultComboBoxModelTransporte = new DefaultComboBoxModel(this.getMeios().toArray());
        this.getCbMeioTransporte().setModel(defaultComboBoxModelTransporte);

        MateriaPrimaDAO mpdao = new MateriaPrimaDAO();
        this.setMaterias(mpdao.listar());
        DefaultComboBoxModel defaultComboBoxModelMateria = new DefaultComboBoxModel(this.getMaterias().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModelMateria);
    }

    public ViewDadosTransporte(Jogador usuario) {

        initComponents();
        lLogo.setIcon(new javax.swing.ImageIcon(".\\imagem\\caminhao70p.png")); // NOI18N
        this.jogador = usuario;

        MeioTransporteController meioTransporteController = new MeioTransporteController();
        meioTransporteController.listar();
        this.setMeios(meioTransporteController.getListaMeiosTransportes());
        DefaultComboBoxModel defaultComboBoxModelTransporte = new DefaultComboBoxModel(this.getMeios().toArray());
        this.getCbMeioTransporte().setModel(defaultComboBoxModelTransporte);

        //selecionar as materias-primas de um fornecedor específico
        EmpresaController empresaController1 = new EmpresaController();
        List empresasSelecionadas = empresaController1.buscarPorJogador(this.jogador);
        Empresa empresaSel = (Empresa) empresasSelecionadas.get(0);

        FabricaController fabricaController = new FabricaController();
        List fabricas = fabricaController.buscarPorEmpresa(empresaSel);
        Fabrica fabrica = (Fabrica) fabricas.get(0);

        ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
        List armazensFab = armazemFabricaController.buscarPorFabrica(fabrica);
        this.setArmazensFabrica(armazensFab);
        DefaultComboBoxModel defaultComboBoxModelArmazensFabrica = new DefaultComboBoxModel(this.getArmazensFabrica().toArray());
        this.getCbDestino().setModel(defaultComboBoxModelArmazensFabrica);

        ArmazemSuprimentoController armazemSuprimentoController = new ArmazemSuprimentoController();
        List armazens = armazemSuprimentoController.buscarPorFabrica(fabrica);
        ArmazemSuprimento armazem = (ArmazemSuprimento) armazens.get(0);

        EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
        List estoques = estoqueSuprimentoController.buscarPorArmazem(armazem);
        this.setMaterias(estoques);
        DefaultComboBoxModel defaultComboBoxModelMateria = new DefaultComboBoxModel(this.getMaterias().toArray());
        this.getCbMateriaPrima().setModel(defaultComboBoxModelMateria);

    }

    public List<ArmazemFabrica> getArmazensFabrica() {
        return armazensFabrica;
    }

    public void setArmazensFabrica(List<ArmazemFabrica> armazensFabrica) {
        this.armazensFabrica = armazensFabrica;
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

    public JComboBox getCbMeioTransporte() {
        return cbMeioTransporte;
    }

    public void setCbMeioTransporte(JComboBox cbTransporte) {
        this.cbMeioTransporte = cbTransporte;
    }

    public JComboBox getCbDestino() {
        return cbDestino;
    }

    public void setCbDestino(JComboBox cbDestino) {
        this.cbDestino = cbDestino;
    }

    public JComboBox getCbMateriaPrima() {
        return cbMateriaPrima;
    }

    public void setCbMateriaPrima(JComboBox cbMateriaPrima) {
        this.cbMateriaPrima = cbMateriaPrima;
    }

    public List<MateriaPrima> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaPrima> materias) {
        this.materias = materias;
    }

    public List<MeioTransporte> getMeios() {
        return meios;
    }

    public void setMeios(List<MeioTransporte> meios) {
        this.meios = meios;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public JComboBox getCbStatus() {
        return cbStatus;
    }

    public void setCbStatus(JComboBox cbStatus) {
        this.cbStatus = cbStatus;
    }

    public JTextField getJtxQuantidade() {
        return jtxQuantidade;
    }

    public void setJtxQuantidade(JTextField jtxQuantidade) {
        this.jtxQuantidade = jtxQuantidade;
    }

    public JTextField getJtxTempo() {
        return jtxTempo;
    }

    public void setJtxTempo(JTextField jtxTempo) {
        this.jtxTempo = jtxTempo;
    }

    public JTextField getJtxValor() {
        return jtxValor;
    }

    public void setJtxValor(JTextField jtxValor) {
        this.jtxValor = jtxValor;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Transporte preencherTransporte() {
        EstoqueSuprimento estoque = (EstoqueSuprimento) this.cbMateriaPrima.getSelectedItem();
        MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
        materiaPrimaController.setCodigo(estoque.getMateriaPrima().getId());
        materiaPrimaController.carregarDados();
        MateriaPrima materiaPrima = materiaPrimaController.getMateriaPrimaCadastro();
        transporte.setMateriaPrima(materiaPrima);

        transporte.setMeioTransporte((MeioTransporte) this.cbMeioTransporte.getSelectedItem());
        transporte.setArmazemFabrica((ArmazemFabrica) this.cbDestino.getSelectedItem());

        transporte.setQuantidade(new BigDecimal(this.jtxQuantidade.getText()));
        transporte.setTempo(this.jtxTempo.getText());
        transporte.setValor(new BigDecimal(this.jtxValor.getText()));
        transporte.setStatus((String) this.cbStatus.getSelectedItem());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transporte>> constraintViolation = validator.validate(transporte);

        if (!constraintViolation.isEmpty()) {
            String mensagem = "";
            for (ConstraintViolation<Transporte> erro : constraintViolation) {
                mensagem += erro.getMessage() + "\n";
                ViewUtil.addMsgErro(mensagem);
            }
            this.error = true;
        }

        return transporte;
    }

    public void exibirTransporte(Transporte transporte) {
        this.limparCampos();
        //if (transporte != null) {
        this.cbMateriaPrima.setSelectedItem(transporte.getMateriaPrima());
        this.cbMeioTransporte.setSelectedItem(transporte.getMeioTransporte());
        this.jtxQuantidade.setText(transporte.getQuantidade().toEngineeringString());
        this.jtxValor.setText(transporte.getValor().toEngineeringString());
        this.jtxTempo.setText(transporte.getTempo());
        this.cbStatus.setSelectedItem(transporte.getStatus());
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

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public void limparCampos() {
        //this.cbMateriaPrima.setSelectedIndex(0);
        //this.cbMeioTransporte.setSelectedIndex(0);
        //this.cbDestino.setSelectedIndex(0);
        //this.cbRodada.setSelectedIndex(0);
        this.jtxQuantidade.setText(null);
        this.jtxValor.setText(null);
        this.jtxTempo.setText(null);

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
        lMateriaPrima = new javax.swing.JLabel();
        bNovo = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bGravar = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        cbMateriaPrima = new javax.swing.JComboBox();
        cbMeioTransporte = new javax.swing.JComboBox();
        lMeioTransporte = new javax.swing.JLabel();
        lTempo = new javax.swing.JLabel();
        jtxTempo = new javax.swing.JTextField();
        lValor = new javax.swing.JLabel();
        jtxValor = new javax.swing.JTextField();
        lDestino = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox();
        lQuantidade = new javax.swing.JLabel();
        jtxQuantidade = new javax.swing.JTextField();
        lStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox();
        lLogo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Cadastro - Transporte");

        pDadosEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Transporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lMateriaPrima.setText("Mat. Prima");
        lMateriaPrima.setToolTipText("Materia do Suprimento");

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

        cbMateriaPrima.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        cbMeioTransporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));

        lMeioTransporte.setText("Meio de Transporte");

        lTempo.setText("Tempo Entrega");

        jtxTempo.setEditable(false);

        lValor.setText("Valor do Frete");

        jtxValor.setEditable(false);

        lDestino.setText("Fab. Destino");

        cbDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Armazém Suprimento", "Fábrica", "Armazém Distribuição", "Distribuidor(Empresa)" }));

        lQuantidade.setText("Quantidade");

        jtxQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxQuantidadeKeyPressed(evt);
            }
        });

        lStatus.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendente", "Entregue" }));

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
                            .addComponent(lMateriaPrima)
                            .addComponent(lMeioTransporte)
                            .addComponent(lTempo)
                            .addComponent(lValor)
                            .addComponent(lDestino)
                            .addComponent(lQuantidade)
                            .addComponent(lStatus))
                        .addGap(22, 22, 22)
                        .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbMeioTransporte, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxValor)
                            .addComponent(jtxTempo)
                            .addComponent(jtxQuantidade)
                            .addComponent(cbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pDadosEmpresaLayout.setVerticalGroup(
            pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosEmpresaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMateriaPrima)
                    .addComponent(cbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMeioTransporte)
                    .addComponent(cbMeioTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDestino)
                    .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuantidade)
                    .addComponent(jtxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTempo)
                    .addComponent(jtxTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lValor)
                    .addComponent(jtxValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pDadosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStatus)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addComponent(pDadosEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        limparCampos();
        transporte = new Transporte();
    }//GEN-LAST:event_bNovoActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void jtxQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxQuantidadeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            MeioTransporte meio = (MeioTransporte) this.cbMeioTransporte.getSelectedItem();
            this.jtxTempo.setText(meio.getTempo());

            int quantidade = Integer.valueOf(this.jtxQuantidade.getText());
            BigDecimal valor = meio.getTaxa_transporte().multiply(new BigDecimal(quantidade)).add(meio.getTaxa_seguro().multiply(new BigDecimal(quantidade)));
            this.jtxValor.setText(valor.toEngineeringString());
            this.cbStatus.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jtxQuantidadeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton bEditar;
    protected javax.swing.JButton bExcluir;
    protected javax.swing.JButton bGravar;
    protected javax.swing.JButton bNovo;
    private javax.swing.JComboBox cbDestino;
    private javax.swing.JComboBox cbMateriaPrima;
    private javax.swing.JComboBox cbMeioTransporte;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JTextField jtxQuantidade;
    private javax.swing.JTextField jtxTempo;
    private javax.swing.JTextField jtxValor;
    private javax.swing.JLabel lDestino;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lMateriaPrima;
    private javax.swing.JLabel lMeioTransporte;
    private javax.swing.JLabel lQuantidade;
    private javax.swing.JLabel lStatus;
    private javax.swing.JLabel lTempo;
    private javax.swing.JLabel lValor;
    private javax.swing.JPanel pDadosEmpresa;
    // End of variables declaration//GEN-END:variables
}
