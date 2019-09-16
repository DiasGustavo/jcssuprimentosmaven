/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
<<<<<<< HEAD
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
=======
import java.util.Calendar;
import java.util.Date;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import jcssuprimentosmaven.controller.AvisoController;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaArmazemDistribuicao;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaArmazemFabrica;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaArmazemSuprimento;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaDistribuidor;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaEmpresa;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaEstoqueDistribuicao;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaEstoqueSuprimento;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaFabrica;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaInvestimento;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaJogador;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaMateriaPrima;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaMeioTransporte;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaMensagens;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaPedido;
<<<<<<< HEAD
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaPessoa;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaProducao;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaProduto;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaRodada;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaSolicitacao;
import jcssuprimentosmaven.ouvinte.OuvinteViewPesquisaTransporte;
<<<<<<< HEAD
import jcssuprimentosmaven.util.GeraRelatorio;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

/**
 *
 * @author Gustavo
 */
public class ViewPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form ViewPrincipal
     */
    private ViewSobre viewSobre;
    private ViewPesquisaJogador viewPesquisaJogador;
    private ViewPesquisaRodada viewPesquisaRodada;
    private ViewPesquisaFornecedor viewPesquisaFornecedor;
    private ViewPesquisaTransportadora viewPesquisaTransportadora;
    private ViewPesquisaMateriaPrima viewPesquisaMateriaPrima;
    private ViewPesquisaMeioTransporte viewPesquisaMeioTransporte;
    private ViewPesquisaEmpresa viewPesquisaEmpresa;
    private ViewPesquisaInvestimento viewPesquisaInvestimento;
    private ViewFluxo viewFluxo;
    private ViewPesquisaFabrica viewPesquisaFabrica;
    private ViewPesquisaArmazemSuprimento viewPesquisaArmazem;
    private ViewPesquisaMensagens viewPesquisaMensagens;
    private ViewDadosAvisos viewDadosAvisos;
    private ViewPesquisaDistribuidor viewPesquisaDistribuidor;
    private ViewPesquisaArmazemFabrica viewPesquisaArmazemFabrica;
    private ViewPesquisaArmazemDistribuicao viewPesquisaArmazemDistribuicao;
    private ViewPesquisaProducao viewPesquisaProducao;
    private ViewPesquisaTransporte viewPesquisaTransporte;
    private ViewPesquisaEstoqueSuprimento veiwPesquisaEstoqueSuprimento;
    private ViewPesquisaEstoqueDistribuicao viewPesquisaEstoqueDistribuicao;
    private ViewPesquisaPedido viewPesquisaPedido;
    private ViewPesquisaSolicitacao viewPesquisaSolicitacao;
    private ViewPesquisaProduto viewPesquisaProduto;
<<<<<<< HEAD
    private PainelDemonstrativo painelExibicao;
    private ViewPesquisaPessoa viewPesquisaPessoa;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    private Jogador jogador;

    public ViewPrincipal() {
        //jogador = new Jogador();
        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        if (jogador.getFuncao() == 3) {
            this.CadastroMenu.setVisible(false);
        }

    }

    /**
     * Construtor que leva em construção da tela
     *
     * @param jogador
     */
    public ViewPrincipal(Jogador jogador) {

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("JCSSUPRIMENTOS - " + jogador.getLogin());
<<<<<<< HEAD
        this.jogador = jogador;
        //captura as dimensoes da tela
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();

        /*viewDadosAvisos = new ViewDadosAvisos();
        desktopPane.add(viewDadosAvisos);
        viewDadosAvisos.setVisible(true);
        viewDadosAvisos.setPosicao();*/
        if (this.jogador.getFuncao() == 1) {
            //quando for participante
            this.RelatorioMenu.setVisible(false);
            this.CadastroMenu.setVisible(false);
            //viewDadosAvisos.getpDadosAvisosJ().setVisible(false);
            //viewDadosAvisos.setLocation((dimensao.width * 75) / 100, 0);

            painelExibicao = new PainelDemonstrativo(jogador);
            painelExibicao.setLayout(new BorderLayout());
            //dimensionar com 70% de largura da tela
            // e altura com 50% da tela
            painelExibicao.setSize((dimensao.width * 30) / 100, (dimensao.height * 80) / 100);
            desktopPane.add(painelExibicao);
            painelExibicao.caixa();
            painelExibicao.armazemFabrica();
            painelExibicao.producao();
            painelExibicao.setVisible(true);

            viewFluxo = new ViewFluxo(this.jogador);
            desktopPane.add(viewFluxo);
            viewFluxo.setVisible(true);
            viewFluxo.setLocation((dimensao.width * 31) / 100, 0);
=======

        viewDadosAvisos = new ViewDadosAvisos();
        desktopPane.add(viewDadosAvisos);
        viewDadosAvisos.setVisible(true);
        viewDadosAvisos.setPosicao();

        if (jogador.getFuncao() == 1) {
            //quando for participante
            this.RelatorioMenu.setVisible(false);
            this.CadastroMenu.setVisible(false);
            viewDadosAvisos.getpDadosAvisosJ().setVisible(false);            
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        } else {
            //quando for Juiz
            //this.MinhaEmpresa.setVisible(false);
            this.Pesquisa.setVisible(false);
            this.CadastroMenu.setVisible(true);
<<<<<<< HEAD
            //viewDadosAvisos.getpDadosAvisosP().setVisible(false);
            painelExibicao = new PainelDemonstrativo(jogador);
            painelExibicao.setLayout(new BorderLayout());

            //dimensionar com 70% de largura da tela
            // e altura com 50% da tela
            painelExibicao.setSize((dimensao.width * 80) / 100, (dimensao.height * 50) / 100);
            desktopPane.add(painelExibicao);
            painelExibicao.fluxoEmpresas();
            painelExibicao.setVisible(true);
=======
            viewDadosAvisos.getpDadosAvisosP().setVisible(false);          
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

        }

    }

    private void barraStatus() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(200, 200);

        // cria a barra inferior e insere no espaço inferior do JFrame
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        frame.setVisible(true);
        desktopPane.add(frame);

    }

    private void bloquearMenus() {
        AvisoController avisoController = new AvisoController();
        //verifica se a rodada expirou
        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        Date data2 = calendar.getTime();

        if (data2.after(avisoController.ultimaRodada().getDataFim()) && this.jogador.getFuncao() == 1) {
            //indica que a rodada terminou e o jogador não pode fazer nada
            this.SistemaMenu.setVisible(false);
            this.MinhaEmpresa.setVisible(false);
            this.Pesquisa.setVisible(false);
        }
    }

    private void avisos(int funcao) {
        if (funcao == 1) {
            viewDadosAvisos = new ViewDadosAvisos();
            viewDadosAvisos.setVisible(true);
            desktopPane.add(viewDadosAvisos);
            viewDadosAvisos.setPosicao();
            viewDadosAvisos.getpDadosAvisosJ().setVisible(false);
        } else {
            viewDadosAvisos.getpDadosAvisosP().setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        SistemaMenu = new javax.swing.JMenu();
        FluxoMenuItem = new javax.swing.JMenuItem();
        SairMenuItem = new javax.swing.JMenuItem();
        CadastroMenu = new javax.swing.JMenu();
        CadRodadaMenuItem = new javax.swing.JMenuItem();
        CadJogadorMenuItem = new javax.swing.JMenuItem();
<<<<<<< HEAD
        CadAlunoMenuItem = new javax.swing.JMenuItem();
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        CadEmpresaMenuItem = new javax.swing.JMenuItem();
        CadFabricaMenuItem = new javax.swing.JMenuItem();
        cadFabricaMenuItem = new javax.swing.JMenuItem();
        CadDistribuidor = new javax.swing.JMenuItem();
        CadMateriaPrimaMenuItem = new javax.swing.JMenuItem();
        CadMeioTransporteMenuItem = new javax.swing.JMenuItem();
        CadInvestimentoMenuItem = new javax.swing.JMenuItem();
        CadArmazemSuprimentoMenuItem = new javax.swing.JMenuItem();
        CadArmazemDistribuicaoMenuItem = new javax.swing.JMenuItem();
        CadEstoqueSuprimento = new javax.swing.JMenuItem();
        CadEstoqueDistribuicao = new javax.swing.JMenuItem();
        CadPedido = new javax.swing.JMenuItem();
        CadSolicitacao = new javax.swing.JMenuItem();
        CadProduto = new javax.swing.JMenuItem();
        MinhaEmpresa = new javax.swing.JMenu();
        empresaItem = new javax.swing.JMenuItem();
        producaoItem = new javax.swing.JMenuItem();
        transporteItem = new javax.swing.JMenuItem();
        estoqueSuprimentoItem = new javax.swing.JMenuItem();
        estoqueDistribuicaoItem = new javax.swing.JMenuItem();
        CadInvestimentoMenuItemUsuario = new javax.swing.JMenuItem();
        Comunicacao = new javax.swing.JMenu();
        MensagensMenuItem = new javax.swing.JMenuItem();
        AvisosMenuItem = new javax.swing.JMenuItem();
        RelatorioMenu = new javax.swing.JMenu();
<<<<<<< HEAD
        rSaudeFinanceira = new javax.swing.JMenuItem();
        rAlunos = new javax.swing.JMenuItem();
        rProducao = new javax.swing.JMenuItem();
=======
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        deleteMenuItem = new javax.swing.JMenuItem();
        Pesquisa = new javax.swing.JMenu();
        pesquisaMercado = new javax.swing.JMenuItem();
        AjudaMenu = new javax.swing.JMenu();
        sobreMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JCSSUPRIMENTOS");

        SistemaMenu.setMnemonic('f');
        SistemaMenu.setText("Sistema");

        FluxoMenuItem.setText("Fluxo");
        FluxoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FluxoMenuItemActionPerformed(evt);
            }
        });
        SistemaMenu.add(FluxoMenuItem);

        SairMenuItem.setMnemonic('x');
        SairMenuItem.setText("Sair");
        SairMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairMenuItemActionPerformed(evt);
            }
        });
        SistemaMenu.add(SairMenuItem);

        menuBar.add(SistemaMenu);

        CadastroMenu.setText("Cadastros");

        CadRodadaMenuItem.setText("Rodada");
        CadRodadaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadRodadaMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadRodadaMenuItem);

<<<<<<< HEAD
        CadJogadorMenuItem.setText("Equipe");
=======
        CadJogadorMenuItem.setText("Jogador");
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        CadJogadorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadJogadorMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadJogadorMenuItem);

<<<<<<< HEAD
        CadAlunoMenuItem.setText("Aluno");
        CadAlunoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadAlunoMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadAlunoMenuItem);

=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        CadEmpresaMenuItem.setText("Empresa");
        CadEmpresaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadEmpresaMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadEmpresaMenuItem);

        CadFabricaMenuItem.setText("Fábrica");
        CadFabricaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadFabricaMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadFabricaMenuItem);

        cadFabricaMenuItem.setText("Armazém Fábrica");
        cadFabricaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadFabricaMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(cadFabricaMenuItem);

        CadDistribuidor.setText("Distribuidor");
        CadDistribuidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadDistribuidorActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadDistribuidor);

        CadMateriaPrimaMenuItem.setText("Materia-Prima");
        CadMateriaPrimaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadMateriaPrimaMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadMateriaPrimaMenuItem);

        CadMeioTransporteMenuItem.setText("Meio de Transporte");
        CadMeioTransporteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadMeioTransporteMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadMeioTransporteMenuItem);

        CadInvestimentoMenuItem.setText("Investimento");
        CadInvestimentoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadInvestimentoMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadInvestimentoMenuItem);

        CadArmazemSuprimentoMenuItem.setText("Armazém Suprimento");
        CadArmazemSuprimentoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadArmazemSuprimentoMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadArmazemSuprimentoMenuItem);

        CadArmazemDistribuicaoMenuItem.setText("Armazém Distribuição");
        CadArmazemDistribuicaoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadArmazemDistribuicaoMenuItemActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadArmazemDistribuicaoMenuItem);

        CadEstoqueSuprimento.setText("Estoque Suprimento");
        CadEstoqueSuprimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadEstoqueSuprimentoActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadEstoqueSuprimento);

        CadEstoqueDistribuicao.setText("Estoque Distribuição");
        CadEstoqueDistribuicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadEstoqueDistribuicaoActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadEstoqueDistribuicao);

        CadPedido.setText("Pedido");
        CadPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadPedidoActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadPedido);

        CadSolicitacao.setText("Solicitação");
        CadSolicitacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadSolicitacaoActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadSolicitacao);

        CadProduto.setText("Produto");
        CadProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadProdutoActionPerformed(evt);
            }
        });
        CadastroMenu.add(CadProduto);

        menuBar.add(CadastroMenu);

        MinhaEmpresa.setText("Minha Empresa");

        empresaItem.setText("Empresa");
        empresaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaItemActionPerformed(evt);
            }
        });
        MinhaEmpresa.add(empresaItem);

        producaoItem.setText("Produção");
        producaoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                producaoItemActionPerformed(evt);
            }
        });
        MinhaEmpresa.add(producaoItem);

        transporteItem.setText("Transportar");
        transporteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transporteItemActionPerformed(evt);
            }
        });
        MinhaEmpresa.add(transporteItem);

        estoqueSuprimentoItem.setText("Estoque Suprimento");
        estoqueSuprimentoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoqueSuprimentoItemActionPerformed(evt);
            }
        });
        MinhaEmpresa.add(estoqueSuprimentoItem);

        estoqueDistribuicaoItem.setText("Estoque Distribuição");
        estoqueDistribuicaoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoqueDistribuicaoItemActionPerformed(evt);
            }
        });
        MinhaEmpresa.add(estoqueDistribuicaoItem);

        CadInvestimentoMenuItemUsuario.setText("Investimento");
        CadInvestimentoMenuItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadInvestimentoMenuItemUsuarioActionPerformed(evt);
            }
        });
        MinhaEmpresa.add(CadInvestimentoMenuItemUsuario);

        menuBar.add(MinhaEmpresa);

        Comunicacao.setText("Comunicação");

        MensagensMenuItem.setText("Mensagens");
        MensagensMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MensagensMenuItemActionPerformed(evt);
            }
        });
        Comunicacao.add(MensagensMenuItem);

        AvisosMenuItem.setText("Avisos");
        AvisosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvisosMenuItemActionPerformed(evt);
            }
        });
        Comunicacao.add(AvisosMenuItem);

        menuBar.add(Comunicacao);

        RelatorioMenu.setMnemonic('e');
        RelatorioMenu.setText("Relatórios");
<<<<<<< HEAD

        rSaudeFinanceira.setMnemonic('t');
        rSaudeFinanceira.setText("Saúde Financeira");
        rSaudeFinanceira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSaudeFinanceiraActionPerformed(evt);
            }
        });
        RelatorioMenu.add(rSaudeFinanceira);

        rAlunos.setMnemonic('y');
        rAlunos.setText("Alunos Inscritos");
        rAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rAlunosActionPerformed(evt);
            }
        });
        RelatorioMenu.add(rAlunos);

        rProducao.setMnemonic('p');
        rProducao.setText("Produção");
        rProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rProducaoActionPerformed(evt);
            }
        });
        RelatorioMenu.add(rProducao);
=======
        RelatorioMenu.setEnabled(false);

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        RelatorioMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        RelatorioMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        RelatorioMenu.add(pasteMenuItem);
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        RelatorioMenu.add(deleteMenuItem);

        menuBar.add(RelatorioMenu);

        Pesquisa.setText("Pesquisa");

        pesquisaMercado.setText("Pesquisa de Mercado");
        pesquisaMercado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaMercadoActionPerformed(evt);
            }
        });
        Pesquisa.add(pesquisaMercado);

        menuBar.add(Pesquisa);

        AjudaMenu.setMnemonic('h');
        AjudaMenu.setText("Ajuda");

        sobreMenuItem.setMnemonic('a');
        sobreMenuItem.setText("Sobre");
        sobreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreMenuItemActionPerformed(evt);
            }
        });
        AjudaMenu.add(sobreMenuItem);

        menuBar.add(AjudaMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SairMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SairMenuItemActionPerformed

    private void sobreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreMenuItemActionPerformed
        // TODO add your handling code here:
        if (viewSobre == null) {
            viewSobre = new ViewSobre();
            desktopPane.add(viewSobre);
        }
        viewSobre.setVisible(true);
        viewSobre.setPosicao();
    }//GEN-LAST:event_sobreMenuItemActionPerformed

    private void FluxoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FluxoMenuItemActionPerformed
        // TODO add your handling code here:
        viewFluxo = new ViewFluxo(this.jogador);
        desktopPane.add(viewFluxo);
        viewFluxo.setVisible(true);
        viewFluxo.setPosicao();

    }//GEN-LAST:event_FluxoMenuItemActionPerformed

    private void MensagensMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MensagensMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaMensagens = new ViewPesquisaMensagens(this.jogador);
        OuvinteViewPesquisaMensagens ouvinte = new OuvinteViewPesquisaMensagens(viewPesquisaMensagens);
        desktopPane.add(viewPesquisaMensagens);
        viewPesquisaMensagens.setVisible(true);
        viewPesquisaMensagens.setJogador(this.jogador);
        viewPesquisaMensagens.setPosicao();
    }//GEN-LAST:event_MensagensMenuItemActionPerformed

    private void empresaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaItemActionPerformed
        viewPesquisaEmpresa = new ViewPesquisaEmpresa(this.jogador);
        OuvinteViewPesquisaEmpresa ouvinte = new OuvinteViewPesquisaEmpresa(viewPesquisaEmpresa, this.jogador);
        desktopPane.add(viewPesquisaEmpresa);
        viewPesquisaEmpresa.setVisible(true);
        viewPesquisaEmpresa.setPosicao();
    }//GEN-LAST:event_empresaItemActionPerformed

    private void AvisosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvisosMenuItemActionPerformed
        // TODO add your handling code here:
        viewDadosAvisos = new ViewDadosAvisos();
        desktopPane.add(viewDadosAvisos);
        viewDadosAvisos.setVisible(true);
        //viewDadosAvisos.setPosicao();
        if (jogador.getFuncao() == 1) {
            viewDadosAvisos.getpDadosAvisosJ().setVisible(false);
        } else {
            viewDadosAvisos.getpDadosAvisosP().setVisible(false);
        }
    }//GEN-LAST:event_AvisosMenuItemActionPerformed

    private void pesquisaMercadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaMercadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisaMercadoActionPerformed

    private void producaoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_producaoItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaProducao = new ViewPesquisaProducao(this.jogador);
        OuvinteViewPesquisaProducao ouvinte = new OuvinteViewPesquisaProducao(viewPesquisaProducao, this.jogador);
        desktopPane.add(viewPesquisaProducao);
        viewPesquisaProducao.setPosicao();
        viewPesquisaProducao.setVisible(true);
    }//GEN-LAST:event_producaoItemActionPerformed

    private void transporteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transporteItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaTransporte = new ViewPesquisaTransporte(this.jogador);
        OuvinteViewPesquisaTransporte ouvinte = new OuvinteViewPesquisaTransporte(viewPesquisaTransporte, this.jogador);
        desktopPane.add(viewPesquisaTransporte);
        viewPesquisaTransporte.setPosicao();
        viewPesquisaTransporte.setVisible(true);
    }//GEN-LAST:event_transporteItemActionPerformed

    private void estoqueSuprimentoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoqueSuprimentoItemActionPerformed
        // TODO add your handling code here:
        veiwPesquisaEstoqueSuprimento = new ViewPesquisaEstoqueSuprimento(this.jogador);
        OuvinteViewPesquisaEstoqueSuprimento ouvinte = new OuvinteViewPesquisaEstoqueSuprimento(veiwPesquisaEstoqueSuprimento, this.jogador);
        desktopPane.add(veiwPesquisaEstoqueSuprimento);
        veiwPesquisaEstoqueSuprimento.setVisible(true);
        veiwPesquisaEstoqueSuprimento.setPosicao();
    }//GEN-LAST:event_estoqueSuprimentoItemActionPerformed

    private void estoqueDistribuicaoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoqueDistribuicaoItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaEstoqueDistribuicao = new ViewPesquisaEstoqueDistribuicao(this.jogador);
        OuvinteViewPesquisaEstoqueDistribuicao ouvinte = new OuvinteViewPesquisaEstoqueDistribuicao(viewPesquisaEstoqueDistribuicao, this.jogador);
        desktopPane.add(viewPesquisaEstoqueDistribuicao);
        viewPesquisaEstoqueDistribuicao.setVisible(true);
        viewPesquisaEstoqueDistribuicao.setPosicao();
    }//GEN-LAST:event_estoqueDistribuicaoItemActionPerformed

    private void CadInvestimentoMenuItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadInvestimentoMenuItemUsuarioActionPerformed
        viewPesquisaInvestimento = new ViewPesquisaInvestimento(this.jogador);
        OuvinteViewPesquisaInvestimento ouvinte = new OuvinteViewPesquisaInvestimento(viewPesquisaInvestimento, this.jogador);
        desktopPane.add(viewPesquisaInvestimento);
        viewPesquisaInvestimento.setVisible(true);
        viewPesquisaInvestimento.setPosicao();
    }//GEN-LAST:event_CadInvestimentoMenuItemUsuarioActionPerformed

    private void CadEstoqueDistribuicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadEstoqueDistribuicaoActionPerformed
        // TODO add your handling code here:
        viewPesquisaEstoqueDistribuicao = new ViewPesquisaEstoqueDistribuicao(this.jogador);
        OuvinteViewPesquisaEstoqueDistribuicao ouvinte = new OuvinteViewPesquisaEstoqueDistribuicao(viewPesquisaEstoqueDistribuicao, this.jogador);
        desktopPane.add(viewPesquisaEstoqueDistribuicao);
        viewPesquisaEstoqueDistribuicao.setVisible(true);
        viewPesquisaEstoqueDistribuicao.setPosicao();
    }//GEN-LAST:event_CadEstoqueDistribuicaoActionPerformed

    private void CadEstoqueSuprimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadEstoqueSuprimentoActionPerformed
        // TODO add your handling code here:
        veiwPesquisaEstoqueSuprimento = new ViewPesquisaEstoqueSuprimento();
        OuvinteViewPesquisaEstoqueSuprimento ouvinte = new OuvinteViewPesquisaEstoqueSuprimento(veiwPesquisaEstoqueSuprimento, this.jogador);
        desktopPane.add(veiwPesquisaEstoqueSuprimento);
        veiwPesquisaEstoqueSuprimento.setVisible(true);
        veiwPesquisaEstoqueSuprimento.setPosicao();
    }//GEN-LAST:event_CadEstoqueSuprimentoActionPerformed

    private void CadInvestimentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadInvestimentoMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaInvestimento = new ViewPesquisaInvestimento(this.jogador);
        OuvinteViewPesquisaInvestimento ouvinte = new OuvinteViewPesquisaInvestimento(viewPesquisaInvestimento, this.jogador);
        desktopPane.add(viewPesquisaInvestimento);
        viewPesquisaInvestimento.setVisible(true);
        viewPesquisaInvestimento.setPosicao();
    }//GEN-LAST:event_CadInvestimentoMenuItemActionPerformed

    private void CadEmpresaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadEmpresaMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaEmpresa = new ViewPesquisaEmpresa(this.jogador);
        OuvinteViewPesquisaEmpresa ouvinte = new OuvinteViewPesquisaEmpresa(viewPesquisaEmpresa, this.jogador);
        desktopPane.add(viewPesquisaEmpresa);
        viewPesquisaEmpresa.setVisible(true);
        viewPesquisaEmpresa.setPosicao();
    }//GEN-LAST:event_CadEmpresaMenuItemActionPerformed

    private void CadDistribuidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadDistribuidorActionPerformed
        // TODO add your handling code here:
        viewPesquisaDistribuidor = new ViewPesquisaDistribuidor(this.jogador);
        OuvinteViewPesquisaDistribuidor ouvinte = new OuvinteViewPesquisaDistribuidor(viewPesquisaDistribuidor, this.jogador);
        desktopPane.add(viewPesquisaDistribuidor);
        viewPesquisaDistribuidor.setVisible(true);
        viewPesquisaDistribuidor.setPosicao();
    }//GEN-LAST:event_CadDistribuidorActionPerformed

    private void CadFabricaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadFabricaMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaFabrica = new ViewPesquisaFabrica(this.jogador);
        OuvinteViewPesquisaFabrica ouvinte = new OuvinteViewPesquisaFabrica(viewPesquisaFabrica, this.jogador);
        desktopPane.add(viewPesquisaFabrica);
        viewPesquisaFabrica.setVisible(true);
        viewPesquisaFabrica.setPosicao();
    }//GEN-LAST:event_CadFabricaMenuItemActionPerformed

    private void CadMeioTransporteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadMeioTransporteMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaMeioTransporte = new ViewPesquisaMeioTransporte(this.jogador);
        viewPesquisaMeioTransporte.bPesquisaDestino.setVisible(false);
        OuvinteViewPesquisaMeioTransporte ouvinte = new OuvinteViewPesquisaMeioTransporte(viewPesquisaMeioTransporte);
        desktopPane.add(viewPesquisaMeioTransporte);
        viewPesquisaMeioTransporte.setVisible(true);
        viewPesquisaMeioTransporte.setPosicao();
    }//GEN-LAST:event_CadMeioTransporteMenuItemActionPerformed

    private void CadMateriaPrimaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadMateriaPrimaMenuItemActionPerformed
        viewPesquisaMateriaPrima = new ViewPesquisaMateriaPrima(this.jogador);
        OuvinteViewPesquisaMateriaPrima ouvinte = new OuvinteViewPesquisaMateriaPrima(viewPesquisaMateriaPrima, this.jogador);
        desktopPane.add(viewPesquisaMateriaPrima);
        viewPesquisaMateriaPrima.setVisible(true);
        viewPesquisaMateriaPrima.setPosicao();
    }//GEN-LAST:event_CadMateriaPrimaMenuItemActionPerformed

    private void CadArmazemDistribuicaoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadArmazemDistribuicaoMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaArmazemDistribuicao = new ViewPesquisaArmazemDistribuicao();
<<<<<<< HEAD
        OuvinteViewPesquisaArmazemDistribuicao ouvinte = new OuvinteViewPesquisaArmazemDistribuicao(viewPesquisaArmazemDistribuicao, this.jogador);
=======
        OuvinteViewPesquisaArmazemDistribuicao ouvinte = new OuvinteViewPesquisaArmazemDistribuicao(viewPesquisaArmazemDistribuicao);
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        desktopPane.add(viewPesquisaArmazemDistribuicao);
        viewPesquisaArmazemDistribuicao.setVisible(true);
        viewPesquisaArmazemDistribuicao.setPosicao();
    }//GEN-LAST:event_CadArmazemDistribuicaoMenuItemActionPerformed

    private void CadArmazemSuprimentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadArmazemSuprimentoMenuItemActionPerformed
        // TODO add your handling code here:
        viewPesquisaArmazem = new ViewPesquisaArmazemSuprimento();
        OuvinteViewPesquisaArmazemSuprimento ouvinte = new OuvinteViewPesquisaArmazemSuprimento(viewPesquisaArmazem, this.jogador);
        desktopPane.add(viewPesquisaArmazem);
        viewPesquisaArmazem.setVisible(true);
        viewPesquisaArmazem.setPosicao();
    }//GEN-LAST:event_CadArmazemSuprimentoMenuItemActionPerformed

    private void CadJogadorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadJogadorMenuItemActionPerformed
        // TODO add your handling code here:
        //if (viewPesquisaJogador == null) {
        viewPesquisaJogador = new ViewPesquisaJogador();
        OuvinteViewPesquisaJogador ouvinte = new OuvinteViewPesquisaJogador(viewPesquisaJogador);
        // }

        desktopPane.add(viewPesquisaJogador);
        viewPesquisaJogador.setVisible(true);
        viewPesquisaJogador.setPosicao();
    }//GEN-LAST:event_CadJogadorMenuItemActionPerformed

    private void CadRodadaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadRodadaMenuItemActionPerformed
        // TODO add your handling code here:
        //if (viewPesquisaRodada == null) {
        viewPesquisaRodada = new ViewPesquisaRodada();
        OuvinteViewPesquisaRodada ouvinte = new OuvinteViewPesquisaRodada(viewPesquisaRodada);
        //}

        desktopPane.add(viewPesquisaRodada);
        viewPesquisaRodada.setVisible(true);
        viewPesquisaRodada.setPosicao();
    }//GEN-LAST:event_CadRodadaMenuItemActionPerformed

    private void CadPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadPedidoActionPerformed
        // TODO add your handling code here:
        viewPesquisaPedido = new ViewPesquisaPedido(this.jogador);
<<<<<<< HEAD
        OuvinteViewPesquisaPedido ouvinte = new OuvinteViewPesquisaPedido(viewPesquisaPedido, this.jogador);
=======
        OuvinteViewPesquisaPedido ouvinte = new OuvinteViewPesquisaPedido(viewPesquisaPedido,this.jogador);
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        desktopPane.add(viewPesquisaPedido);
        viewPesquisaPedido.setVisible(true);
        viewPesquisaPedido.setPosicao();
    }//GEN-LAST:event_CadPedidoActionPerformed

    private void CadSolicitacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadSolicitacaoActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        viewPesquisaSolicitacao = new ViewPesquisaSolicitacao(this.jogador);
        OuvinteViewPesquisaSolicitacao ouvinte = new OuvinteViewPesquisaSolicitacao(viewPesquisaSolicitacao, this.jogador);
=======
        viewPesquisaSolicitacao = new ViewPesquisaSolicitacao();
        OuvinteViewPesquisaSolicitacao ouvinte = new OuvinteViewPesquisaSolicitacao(viewPesquisaSolicitacao);
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        desktopPane.add(viewPesquisaSolicitacao);
        viewPesquisaSolicitacao.setVisible(true);
        viewPesquisaSolicitacao.setPosicao();
    }//GEN-LAST:event_CadSolicitacaoActionPerformed

    private void CadProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadProdutoActionPerformed
        // TODO add your handling code here:
        viewPesquisaProduto = new ViewPesquisaProduto();
        OuvinteViewPesquisaProduto ouvinte = new OuvinteViewPesquisaProduto(viewPesquisaProduto);
        desktopPane.add(viewPesquisaProduto);
        viewPesquisaProduto.setVisible(true);
        viewPesquisaProduto.setPosicao();
    }//GEN-LAST:event_CadProdutoActionPerformed

    private void cadFabricaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadFabricaMenuItemActionPerformed
<<<<<<< HEAD
        viewPesquisaArmazemFabrica = new ViewPesquisaArmazemFabrica(this.jogador);
=======
       viewPesquisaArmazemFabrica = new ViewPesquisaArmazemFabrica();
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
        OuvinteViewPesquisaArmazemFabrica ouvinte = new OuvinteViewPesquisaArmazemFabrica(viewPesquisaArmazemFabrica, this.jogador);
        desktopPane.add(viewPesquisaArmazemFabrica);
        viewPesquisaArmazemFabrica.setVisible(true);
        viewPesquisaArmazemFabrica.setPosicao();
    }//GEN-LAST:event_cadFabricaMenuItemActionPerformed

<<<<<<< HEAD
    private void CadAlunoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadAlunoMenuItemActionPerformed
        viewPesquisaPessoa = new ViewPesquisaPessoa();
        OuvinteViewPesquisaPessoa ouvinte = new OuvinteViewPesquisaPessoa(viewPesquisaPessoa);
        desktopPane.add(viewPesquisaPessoa);
        viewPesquisaPessoa.setVisible(true);
        viewPesquisaPessoa.setPosicao();
    }//GEN-LAST:event_CadAlunoMenuItemActionPerformed

    private void rSaudeFinanceiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSaudeFinanceiraActionPerformed
        GeraRelatorio saudeFinanceira = new GeraRelatorio();
        saudeFinanceira.geraRelatorioProcessos();
    }//GEN-LAST:event_rSaudeFinanceiraActionPerformed

    private void rAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rAlunosActionPerformed
        GeraRelatorio saudeFinanceira = new GeraRelatorio();
        String caminho = ".\\reports\\alunos.jasper";
        Map<String, Object> parametros = new HashMap<>();
        saudeFinanceira.geradorDeRelatorios(caminho, parametros);
    }//GEN-LAST:event_rAlunosActionPerformed

    private void rProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rProducaoActionPerformed
        GeraRelatorio saudeFinanceira = new GeraRelatorio();
        String caminho = ".\\reports\\producao.jasper";
        Map<String, Object> parametros = new HashMap<>();
        saudeFinanceira.geradorDeRelatorios(caminho, parametros);
    }//GEN-LAST:event_rProducaoActionPerformed

=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    public Jogador getJogador() {
        if (jogador == null) {
            jogador = new Jogador();
        }
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AjudaMenu;
    private javax.swing.JMenuItem AvisosMenuItem;
<<<<<<< HEAD
    private javax.swing.JMenuItem CadAlunoMenuItem;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    private javax.swing.JMenuItem CadArmazemDistribuicaoMenuItem;
    private javax.swing.JMenuItem CadArmazemSuprimentoMenuItem;
    private javax.swing.JMenuItem CadDistribuidor;
    private javax.swing.JMenuItem CadEmpresaMenuItem;
    private javax.swing.JMenuItem CadEstoqueDistribuicao;
    private javax.swing.JMenuItem CadEstoqueSuprimento;
    private javax.swing.JMenuItem CadFabricaMenuItem;
    private javax.swing.JMenuItem CadInvestimentoMenuItem;
    private javax.swing.JMenuItem CadInvestimentoMenuItemUsuario;
    private javax.swing.JMenuItem CadJogadorMenuItem;
    private javax.swing.JMenuItem CadMateriaPrimaMenuItem;
    private javax.swing.JMenuItem CadMeioTransporteMenuItem;
    private javax.swing.JMenuItem CadPedido;
    private javax.swing.JMenuItem CadProduto;
    private javax.swing.JMenuItem CadRodadaMenuItem;
    private javax.swing.JMenuItem CadSolicitacao;
    private javax.swing.JMenu CadastroMenu;
    private javax.swing.JMenu Comunicacao;
    private javax.swing.JMenuItem FluxoMenuItem;
    private javax.swing.JMenuItem MensagensMenuItem;
    private javax.swing.JMenu MinhaEmpresa;
    private javax.swing.JMenu Pesquisa;
    private javax.swing.JMenu RelatorioMenu;
    private javax.swing.JMenuItem SairMenuItem;
    private javax.swing.JMenu SistemaMenu;
    private javax.swing.JMenuItem cadFabricaMenuItem;
<<<<<<< HEAD
=======
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem empresaItem;
    private javax.swing.JMenuItem estoqueDistribuicaoItem;
    private javax.swing.JMenuItem estoqueSuprimentoItem;
    private javax.swing.JMenuBar menuBar;
<<<<<<< HEAD
    private javax.swing.JMenuItem pesquisaMercado;
    private javax.swing.JMenuItem producaoItem;
    private javax.swing.JMenuItem rAlunos;
    private javax.swing.JMenuItem rProducao;
    private javax.swing.JMenuItem rSaudeFinanceira;
=======
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem pesquisaMercado;
    private javax.swing.JMenuItem producaoItem;
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    private javax.swing.JMenuItem sobreMenuItem;
    private javax.swing.JMenuItem transporteItem;
    // End of variables declaration//GEN-END:variables

}
