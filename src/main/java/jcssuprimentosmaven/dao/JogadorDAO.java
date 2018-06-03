/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class JogadorDAO {

    public void salvar(Jogador jogador) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();
            sessao.save(jogador);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {
            sessao.close();
        }
    }

    public List<Jogador> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Jogador> listaJogadores = null;
        try {
            Query consulta = sessao.getNamedQuery("Jogador.listar");
            listaJogadores = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return listaJogadores;
    }

    public Jogador buscarPorCodigo(Long codigoInformado) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Jogador jogador = null;
        try {
            Query consulta = sessao.getNamedQuery("Jogador.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            jogador = (Jogador) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return jogador;
    }

    public void editar(Jogador jogador) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();
            sessao.update(jogador);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {
            sessao.close();
        }
    }

    public void excluir(Jogador jogador) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();
            sessao.delete(jogador);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {
            sessao.close();
        }
    }
}
