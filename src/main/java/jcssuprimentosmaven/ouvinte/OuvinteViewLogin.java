/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jcssuprimentosmaven.controller.JogadorController;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewLogin;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewLogin {
    ViewLogin viewLogin;
    
    public OuvinteViewLogin(ViewLogin view){
        this.viewLogin = view;
        viewLogin.bAcessarAddActionListener(new OuvinteAutenticar());
        
    }
    
    class OuvinteAutenticar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Jogador jogador = viewLogin.preencherUsuario();
                JogadorController jogadorController = new JogadorController();
                jogadorController.setJogadorCadastro(jogador);
                Jogador autentico = jogadorController.autenticarUsuario();                
                if(autentico != null){
                viewLogin.iniciaSistema(true,autentico);
                }else{
                    viewLogin.iniciaSistema(false, null);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao autenticar o usuário " + ex.getMessage());
                System.exit(0);
            }
        }
        
    }
    
}
