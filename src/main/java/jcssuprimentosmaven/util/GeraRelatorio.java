/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.util;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Gustavo
 */
public class GeraRelatorio {

    public void geraRelatorioProcessos() {
        try {
            String caminho = ".\\reports\\saudeFinanceira.jasper";

            Map<String, Object> parametros = new HashMap<>();

            Connection conexao = HibernateUtil.getConexao();

            JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
            viewReportFrame("Relatório", relatorio);
            //JasperPrintManager.printReport(relatorio, true);

        } catch (JRException ex) {
            ViewUtil.addMsgErro("Ocorreru ao gerar o relatório " + ex.getMessage());
        }

    }

    public void geradorDeRelatorios(String caminho, Map<String, Object> parametros) {
        Connection conexao = HibernateUtil.getConexao();
        try {
            // compilando o relatório
            JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
            viewReportFrame("Relatório", relatorio);
            //JasperPrintManager.printReport(relatorio, true);

        } catch (JRException ex) {
            ViewUtil.addMsgErro("Ocorreru ao gerar o relatório " + ex.getMessage());
        }

    }

    private static void viewReportFrame(String titulo, JasperPrint print) {

        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        JRViewer viewer = new JRViewer(print);

        // cria o JFrame
        JFrame frameRelatorio = new JFrame(titulo);

        // adiciona o JRViewer no JFrame
        frameRelatorio.add(viewer, BorderLayout.CENTER);

        // configura o tamanho padrão do JFrame
        frameRelatorio.setSize(500, 500);

        // maximiza o JFrame para ocupar a tela toda.
        frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // configura a operação padrão quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // exibe o JFrame
        frameRelatorio.setVisible(true);

    }
}
