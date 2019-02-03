/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Producao;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class ProducaoDAO {
    public void salvar(Producao producao){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(producao);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Producao> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Producao> listaProducoes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Producao.listar");
            listaProducoes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaProducoes;
    }
    
    public Producao buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Producao producao = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Producao.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            producao = (Producao)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return producao;
    }
    
   /* public List<Producao> buscarPorMateria(String materia){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Producao> producoes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Producao.buscarPorMateria");
            consulta.setString("descricao", materia);
            producoes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return producoes;
    }*/
    
    public List<Producao> buscarPorFabrica(Fabrica fabrica){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Producao> producoes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Producao.buscarPorFabrica");
            consulta.setLong("fabrica", fabrica.getId());
            producoes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return producoes;
    }
    
    public void editar(Producao producao){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(producao);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Producao producao){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(producao);
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
