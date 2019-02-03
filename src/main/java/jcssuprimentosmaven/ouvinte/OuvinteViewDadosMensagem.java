/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jcssuprimentosmaven.controller.MensagemController;
import jcssuprimentosmaven.domain.Mensagem;
import jcssuprimentosmaven.view.ViewDadosMensagem;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosMensagem {
    ViewDadosMensagem viewDadosMensagem;
    
    public OuvinteViewDadosMensagem(ViewDadosMensagem view){
        this.viewDadosMensagem = view;
        viewDadosMensagem.bEnviarAddActionListener(new OuvinteEnviarMensagem());
    }
    
    class OuvinteEnviarMensagem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Mensagem mensagem = viewDadosMensagem.preencherMensagem();
            if(!viewDadosMensagem.getErro()){
                MensagemController mensagemController = new MensagemController();
                mensagemController.setMensagemCadastro(mensagem);
                mensagemController.salvar();
                viewDadosMensagem.limparCampos();
            }else{
                viewDadosMensagem.setError(false);
            }
        }
        
    }
}
