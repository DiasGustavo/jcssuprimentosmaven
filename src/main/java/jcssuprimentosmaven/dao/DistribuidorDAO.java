/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Distribuidor;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class DistribuidorDAO {
    public void salvar(Distribuidor distribuidor){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(distribuidor);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Distribuidor> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Distribuidor> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Distribuidor.listar");
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public Distribuidor buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Distribuidor empresa = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Distribuidor.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            empresa = (Distribuidor)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return empresa;
    }
    
    public List<Distribuidor> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Distribuidor> listaDistribuidores = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Distribuidor.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaDistribuidores = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaDistribuidores;
    }
    
    public List<Distribuidor> buscarPorEstoque(EstoqueDistribuicao estoque){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Distribuidor> listaDistribuidores = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Distribuidor.buscarPorEstoque");
            consulta.setLong("estoque",estoque.getId() );
            listaDistribuidores = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaDistribuidores;
    }
    
     public void editar(Distribuidor distribuidor){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(distribuidor);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
     
    public void excluir(Distribuidor distribuidor){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(distribuidor);
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
