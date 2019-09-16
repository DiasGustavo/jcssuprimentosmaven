/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.ProducaoController;
import jcssuprimentosmaven.dao.EmpresaDAO;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Producao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Gustavo Data de criação: 03//08/2019 Data de útilma modificação:
 * 03/08/2019
 */
public class PainelDemonstrativo extends javax.swing.JInternalFrame {

    Jogador jogador;
    private JPanel pGraficos;

    /**
     * Creates new form PainelDemonstrativo
     */
    public PainelDemonstrativo() {
        initComponents();
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;

    }

    public PainelDemonstrativo(Jogador jogador) {
        initComponents();
        this.jogador = jogador;
        pGraficos = new JPanel();
        if(jogador.getFuncao()==0){
            //administrador
            //jPcaixa.setVisible(false);
            jPcaixa.setEnabled(false);
            jPstkFabrica.setEnabled(false);
            jPstkProducao.setVisible(true);
            
        }

    }

    private CategoryDataset dadosCaixa() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        EmpresaDAO edao = new EmpresaDAO();
        List empresas = edao.buscarPorJogador(this.jogador);
        Empresa empresa = (Empresa) empresas.get(0);

        dataset.addValue(empresa.getCapitalInicial(), "Capital Inicial", "Rodada Atual");
        dataset.addValue(empresa.getCapitalAtual(), "Capital Atual", "Rodada Atual");
        BigDecimal despesas = empresa.getCapitalInicial().subtract(empresa.getCapitalAtual());
        dataset.addValue(despesas, "Despesas", "Rodada Atual");

        return dataset;
    }
    
    private CategoryDataset pegaDadosEmpresas(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        EmpresaDAO edao = new EmpresaDAO();
        List empresas = edao.listar();
       

        for(int i = 0; i < empresas.size();i++){
            Empresa empresa = (Empresa) empresas.get(i);
            dataset.addValue(empresa.getCapitalAtual(), empresa.getNomeFantasia(), "Capital Atual");
        }      

        return dataset;
    }

    private CategoryDataset dadosSuprimento() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
        FabricaController fabricaController = new FabricaController();
        EmpresaController empresaController = new EmpresaController();
        List empresas = empresaController.buscarPorJogador(this.jogador);
        Empresa empresa = (Empresa) empresas.get(0);
        List fabricas = fabricaController.buscarPorEmpresa(empresa);
        Fabrica fabrica = (Fabrica) fabricas.get(0);
        List armazens = armazemFabricaController.buscarPorFabrica(fabrica);
        if (armazens.size() > 0) {
            for (int i = 0; i < armazens.size(); i++) {
                ArmazemFabrica armTemp = (ArmazemFabrica) armazens.get(i);
                dataset.addValue(armTemp.getQuantidade(), armTemp.getMateriaPrima().getDescricao() +"\n"+ armTemp.getQuantidade(), "Matéria-Prima");
            }
        }

        return dataset;
    }

    private CategoryDataset dadosProducao() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ProducaoController producaoController = new ProducaoController();
        FabricaController fabricaController = new FabricaController();
        EmpresaController empresaController = new EmpresaController();
        ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
        List empresas = empresaController.buscarPorJogador(this.jogador);
        Empresa empresa = (Empresa) empresas.get(0);
        List fabricas = fabricaController.buscarPorEmpresa(empresa);
        Fabrica fabrica = (Fabrica) fabricas.get(0);
        List armazensFabrica = armazemFabricaController.buscarPorFabrica(fabrica);
        List<Producao> producoes = new ArrayList<Producao>();
        Producao producao = null;
        for (int i = 0; i < armazensFabrica.size(); i++) {
            producao = producaoController.buscarPorArmazem((ArmazemFabrica) armazensFabrica.get(i));
            producoes.add(producao);
        }
        if(producoes.size()>0){
            for(int j = 0; j < producoes.size() && j < 3; j++){
                Producao proTemp = (Producao)producoes.get(j);
                
                dataset.addValue(proTemp.getProdutos(), "Calçados - " + j+1 +"\n" + proTemp.getProdutos(), "Produtos");
            }
        }
        
        return dataset;
    }

    private PieDataset dadosCaixaPie() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        EmpresaDAO edao = new EmpresaDAO();
        List empresas = edao.buscarPorJogador(this.jogador);
        Empresa empresa = (Empresa) empresas.get(0);

        dataset.setValue("Capital Inicial R$ \n" + empresa.getCapitalInicial(), empresa.getCapitalInicial());
        dataset.setValue("Capital Atual R$ \n" + empresa.getCapitalAtual(), empresa.getCapitalAtual());
        BigDecimal despesas = empresa.getCapitalInicial().subtract(empresa.getCapitalAtual());
        dataset.setValue("Despesas R$ \n" + despesas, despesas);
        return dataset;
    }
    
    public void fluxoEmpresas(){
        //captura dos dados
        CategoryDataset cds = pegaDadosEmpresas();
        //definindo os eixos
        String titulo = "Empresas";
        String eixoy = "Valores";
        String txt_legenda = "Legenda:";
        //configurando os gráficos
        boolean legenda = true;
        boolean tooltips = true;
        boolean urls = true;

        JFreeChart graf = ChartFactory.createBarChart3D(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);

        ChartPanel myChartPanel = new ChartPanel(graf, true);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
        //dimensionar com 70% de largura da tela
        // e altura com 50% da tela

        
        myChartPanel.setSize(jPstkProducao.getWidth(), jPstkProducao.getHeight());
        myChartPanel.setVisible(true);
        //definindo no painel 
        jPstkProducao.setSize((dimensao.width * 90) / 100, (dimensao.height * 33) / 100);
        jPstkProducao.removeAll();
        jPstkProducao.add(myChartPanel);
        jPstkProducao.revalidate();
        jPstkProducao.repaint();
    }

    public void caixa() {
        //captura dos dados
        CategoryDataset cds = dadosCaixa();
        PieDataset dados = dadosCaixaPie();
        //definindo os eixos
        String titulo = "Caixa Atual " + this.jogador.getLogin();
        String eixoy = "Valores";
        String txt_legenda = "Legenda:";
        //configurando os gráficos
        boolean legenda = true;
        boolean tooltips = false;
        boolean urls = true;

        //JFreeChart graf = ChartFactory.createBarChart3D(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);
        JFreeChart grafico = ChartFactory.createPieChart3D(titulo, dados, legenda, tooltips, urls);
        ChartPanel myChartPanel = new ChartPanel(grafico, true);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
        //dimensionar com 70% de largura da tela
        // e altura com 50% da tela

        myChartPanel.setSize(pGraficos.getWidth(), pGraficos.getHeight());
        myChartPanel.setVisible(true);
        //definindo no painel 
        //captura as dimensoes da tela
        pGraficos.setSize((dimensao.width * 90) / 100, (dimensao.height * 33) / 100);
        //pGraficos.removeAll();
        pGraficos.add(myChartPanel);
        pGraficos.revalidate();
        pGraficos.repaint();
    }

    public void armazemFabrica() {
        //captura dos dados
        CategoryDataset cds = dadosSuprimento();
        //definindo os eixos
        String titulo = "Armazém Fábrica " + this.jogador.getLogin();
        String eixoy = "Valores";
        String txt_legenda = "Legenda:";
        //configurando os gráficos
        boolean legenda = true;
        boolean tooltips = true;
        boolean urls = true;

        JFreeChart graf = ChartFactory.createBarChart3D(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);

        ChartPanel myChartPanel = new ChartPanel(graf, true);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
        //dimensionar com 70% de largura da tela
        // e altura com 50% da tela

        myChartPanel.setSize(pGraficos.getWidth(), pGraficos.getHeight());
        myChartPanel.setVisible(true);
        //definindo no painel 
        pGraficos.setSize((dimensao.width * 90) / 100, (dimensao.height * 33) / 100);
        //pGraficos.removeAll();
        pGraficos.add(myChartPanel);
        pGraficos.revalidate();
        pGraficos.repaint();
    }
    
    public void producao(){
        //captura dos dados
        CategoryDataset cds = dadosProducao();
        //definindo os eixos
        String titulo = "Últimas produções";
        String eixoy = "Valores";
        String txt_legenda = "Legenda:";
        //configurando os gráficos
        boolean legenda = true;
        boolean tooltips = true;
        boolean urls = true;

        JFreeChart graf = ChartFactory.createBarChart3D(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);

        ChartPanel myChartPanel = new ChartPanel(graf, true);
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
        //dimensionar com 70% de largura da tela
        // e altura com 50% da tela

        myChartPanel.setSize(pGraficos.getWidth(), pGraficos.getHeight());
        myChartPanel.setVisible(true);
        //definindo no painel 
        pGraficos.setSize((dimensao.width * 90) / 100, (dimensao.height * 33) / 100);
        //pGraficos.removeAll();
        pGraficos.add(myChartPanel);
        pGraficos.revalidate();
        pGraficos.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPcaixa = new javax.swing.JPanel();
        jPstkFabrica = new javax.swing.JPanel();
        jPstkProducao = new javax.swing.JPanel();

        setClosable(true);
        setTitle("Indicadores");

        jPcaixa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPcaixaLayout = new javax.swing.GroupLayout(jPcaixa);
        jPcaixa.setLayout(jPcaixaLayout);
        jPcaixaLayout.setHorizontalGroup(
            jPcaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPcaixaLayout.setVerticalGroup(
            jPcaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jPstkFabrica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPstkFabrica.setPreferredSize(new java.awt.Dimension(0, 234));

        javax.swing.GroupLayout jPstkFabricaLayout = new javax.swing.GroupLayout(jPstkFabrica);
        jPstkFabrica.setLayout(jPstkFabricaLayout);
        jPstkFabricaLayout.setHorizontalGroup(
            jPstkFabricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );
        jPstkFabricaLayout.setVerticalGroup(
            jPstkFabricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jPstkProducao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPstkProducao.setPreferredSize(new java.awt.Dimension(0, 234));

        javax.swing.GroupLayout jPstkProducaoLayout = new javax.swing.GroupLayout(jPstkProducao);
        jPstkProducao.setLayout(jPstkProducaoLayout);
        jPstkProducaoLayout.setHorizontalGroup(
            jPstkProducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPstkProducaoLayout.setVerticalGroup(
            jPstkProducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPcaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPstkFabrica, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jPstkProducao, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPcaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPstkFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPstkProducao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPcaixa;
    private javax.swing.JPanel jPstkFabrica;
    private javax.swing.JPanel jPstkProducao;
    // End of variables declaration//GEN-END:variables
}
