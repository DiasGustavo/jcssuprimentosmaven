/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.main;

import jcssuprimentosmaven.dao.JogadorDAO;
import jcssuprimentosmaven.ouvinte.OuvinteViewLogin;
import jcssuprimentosmaven.view.ViewLogin;

/**
 *
 * @author Gustavo
 */
public class Jcssuprimentosmaven {
        
    public static void main(String[] args){
        /*ViewPrincipal viewPrincipal = new ViewPrincipal();
        viewPrincipal.setLocationRelativeTo(null);
        viewPrincipal.setVisible(true);
        
        JogadorDAO ddao = new JogadorDAO();
        ddao.listar();*/
        ViewLogin viewLogin = new ViewLogin();
        OuvinteViewLogin ouvinte = new OuvinteViewLogin(viewLogin);
        viewLogin.setLocationRelativeTo(null);
        viewLogin.setVisible(true);
        
        JogadorDAO ddao = new JogadorDAO();
        ddao.listar();
    }
    
}
