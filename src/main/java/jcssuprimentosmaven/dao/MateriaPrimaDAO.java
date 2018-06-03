/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class MateriaPrimaDAO {
    public void salvar(MateriaPrima materiaPrima){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.save(materiaPrima);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<MateriaPrima> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<MateriaPrima> listaMateriaPrima = null;
        
        try{
            Query consulta = sessao.getNamedQuery("MateriaPrima.listar");
            listaMateriaPrima = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaMateriaPrima;
    }
    
    public MateriaPrima buscarPorCodigo (Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        MateriaPrima materiaPrima = null;
        
        try{
            Query consulta = sessao.getNamedQuery("MateriaPrima.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            materiaPrima = (MateriaPrima)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return materiaPrima;
    }
    
    public void editar(MateriaPrima materiaPrima){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.update(materiaPrima);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(MateriaPrima materiaPrima){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(materiaPrima);
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
