/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class ArmazemDistribuicaoDAO {
    public void salvar(ArmazemDistribuicao armazem){
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
    
    public List<ArmazemDistribuicao> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemDistribuicao> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemDistribuicao.listar");
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<ArmazemDistribuicao> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemDistribuicao> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemDistribuicao.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<ArmazemDistribuicao> buscarPorFabrica(Fabrica fabrica){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemDistribuicao> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemDistribuicao.buscarPorFabrica");
            consulta.setLong("fabrica", fabrica.getId());
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public ArmazemDistribuicao buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        ArmazemDistribuicao armazem = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemDistribuicao.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            armazem = (ArmazemDistribuicao)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazem;
    }
    
    public void editar(ArmazemDistribuicao armazem){
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
    
    public void excluir(ArmazemDistribuicao armazem){
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
