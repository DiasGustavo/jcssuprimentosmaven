/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;

import jcssuprimentosmaven.domain.Juiz;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class JuizDAO {
    public void salvar(Juiz juiz ){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(juiz);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
            throw ex;
        }finally{
            sessao.close();
        }
    }
    
    public List<Juiz> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Juiz> listaJuizes = null;
        try{
            Query consulta = sessao.getNamedQuery("Juiz.listar");
            listaJuizes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaJuizes;
    }
    
    public Juiz buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Juiz juiz = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Juiz.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            juiz = (Juiz) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return juiz;
    }
    
    public List<Juiz> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Juiz> listaJuizes = null;
        try{
            Query consulta = sessao.getNamedQuery("Juiz.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaJuizes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaJuizes;
    }
    
    public void editar(Juiz juiz){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(juiz);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
            throw ex;
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Juiz juiz){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(juiz);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
            throw ex;
        }finally{
            sessao.close();
        }
    }
}