/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import jcssuprimentosmaven.controller.MensagemController;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaMensagens;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaMensagens {

    ViewPesquisaMensagens viewPesquisaMensagens;

    public OuvinteViewPesquisaMensagens(ViewPesquisaMensagens view) {
        this.viewPesquisaMensagens = view;
        viewPesquisaMensagens.bEnviadasTodosAddActionListener(new OuvinteEnviadasTodasMensagens());
        viewPesquisaMensagens.bRecebidasTodosAddActionListener(new OuvinteRecebidasTodasMensagens());
        viewPesquisaMensagens.bPesquisarAddActionListener(new OuvinteRecebidasTodasMensagens());
    }

    class OuvinteEnviadasTodasMensagens implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MensagemController mensagemController = new MensagemController();
                Jogador jogador = viewPesquisaMensagens.getJogador();
                List mensagens = mensagemController.buscarPorRemetente(jogador.getLogin());
                viewPesquisaMensagens.listar(mensagens);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe mensagens para listar " + ex.getMessage());
            }
        }

    }

    class OuvinteRecebidasTodasMensagens implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MensagemController mensagemController = new MensagemController();
                Jogador jogador = viewPesquisaMensagens.getJogador();
                List mensagens = mensagemController.buscarPorDestinatario(jogador.getLogin());
                if (mensagens.size() > 0) {
                    viewPesquisaMensagens.listar(mensagens);
                } else {
                    ViewUtil.addMsgErro("Não existe mensagens para listar");
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe mensagens para listar " + ex.getMessage());
            }
        }

    }
}
