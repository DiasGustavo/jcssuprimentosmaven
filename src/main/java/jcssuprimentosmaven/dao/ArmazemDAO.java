/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.Armazem;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class ArmazemDAO {
    public void salvar(Armazem armazem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(armazem);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Armazem> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Armazem> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Armazem.listar");
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<Armazem> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Armazem> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Armazem.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public Armazem buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Armazem armazem = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Armazem.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            armazem = (Armazem)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazem;
    }
    
    public void editar(Armazem armazem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(armazem);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Armazem armazem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(armazem);
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
