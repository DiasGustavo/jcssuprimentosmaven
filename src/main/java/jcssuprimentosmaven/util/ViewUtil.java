/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class ViewUtil {
    
    public static void addMsgInfo(String mensagem){
        JOptionPane.showMessageDialog(null,mensagem);
    }
    
    public static void addMsgErro (String mensagem){
        JOptionPane.showMessageDialog(null,mensagem,"Erro",JOptionPane.ERROR_MESSAGE);
    }
}
