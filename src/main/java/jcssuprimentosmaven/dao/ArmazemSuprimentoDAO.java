/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class ArmazemSuprimentoDAO {
    public void salvar(ArmazemSuprimento armazem){
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
    
    public List<ArmazemSuprimento> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemSuprimento> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemSuprimento.listar");
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<ArmazemSuprimento> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemSuprimento> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemSuprimento.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public ArmazemSuprimento buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        ArmazemSuprimento armazem = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemSuprimento.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            armazem = (ArmazemSuprimento)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazem;
    }
    
    public List<ArmazemSuprimento> buscarPorFabrica(Fabrica fabrica){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemSuprimento> armazens = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemSuprimento.buscarPorFabrica");
            consulta.setLong("fabrica", fabrica.getId());
            armazens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazens;
    }
    
    public void editar(ArmazemSuprimento armazem){
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
    
    public void excluir(ArmazemSuprimento armazem){
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
