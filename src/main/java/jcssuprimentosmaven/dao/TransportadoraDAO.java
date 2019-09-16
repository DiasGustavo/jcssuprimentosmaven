/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Transportadora;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class TransportadoraDAO {
    public void salvar(Transportadora transportadora){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(transportadora);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Transportadora> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Transportadora> listaTransportadoras = null;
        try{
            Query consulta = sessao.getNamedQuery("Transportadora.listar");
            listaTransportadoras = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaTransportadoras;
    }
    
    public Transportadora buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transportadora transportadora = null;
        try{
            Query consulta = sessao.getNamedQuery("Transportadora.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            transportadora = (Transportadora)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return transportadora;
    }
    
    public List<Transportadora> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Transportadora> listaTransportadoras = null;
        try{
            Query consulta = sessao.getNamedQuery("Transportadora.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaTransportadoras = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaTransportadoras;
    }
    
    public void editar(Transportadora transportadora){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(transportadora);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Transportadora transportadora){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(transportadora);
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
