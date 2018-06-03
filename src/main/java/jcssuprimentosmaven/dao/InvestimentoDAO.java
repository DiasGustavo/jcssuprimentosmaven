/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.Investimento;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class InvestimentoDAO {
    public void salvar(Investimento investimento){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.save(investimento);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Investimento> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Investimento> listaInvestimentos = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Investimento.listar");
            listaInvestimentos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaInvestimentos;
    }
    
    public Investimento buscarPorCodigo (Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Investimento investimento = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Investimento.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            investimento = (Investimento)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return  investimento;
    }
    
    public void editar(Investimento investimento){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.update(investimento);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Investimento investimento){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(investimento);
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
