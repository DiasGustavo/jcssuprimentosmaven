/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
<<<<<<< HEAD
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
import jcssuprimentosmaven.domain.Solicitacao;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class SolicitacaoDAO {
    public Long salvar(Solicitacao solicitacao){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long codigo = null;
        
        try{
            transacao = sessao.beginTransaction();
            codigo = (Long) sessao.save(solicitacao);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
        return codigo;
    }
    
    public List<Solicitacao> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List solicitacoes = null;
        try{
            Query consulta = sessao.getNamedQuery("Solicitacao.listar");
            solicitacoes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return solicitacoes;
    }
    
    public Solicitacao buscarPorCodigo(Long codigo){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Solicitacao solicitacao = null;
        try{
            Query consulta = sessao.getNamedQuery("Solicitacao.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            solicitacao = (Solicitacao) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return solicitacao;
    }
    
<<<<<<< HEAD
    public List<Solicitacao> buscarPorArmazemStatus(ArmazemDistribuicao armazem, String status){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List solicitacoes = null;
        try{
            Query consulta = sessao.getNamedQuery("Solicitacao.buscarPorArmazemStatus");
            consulta.setLong("armazem", armazem.getId());
            consulta.setString("status", status);
            solicitacoes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return solicitacoes;
    }
    
=======
>>>>>>> 422d0a7184ad0fae75859fb8671f48ecf0ffb1a9
    public void editar(Solicitacao solicitacao){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(solicitacao);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Solicitacao solicitacao){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(solicitacao);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
}
