/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.MeioTransporte;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class MeioTransporteDAO {

    public void salvar(MeioTransporte meioTransporte){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.save(meioTransporte);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<MeioTransporte> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<MeioTransporte> listaMeiosTransporte = null;
        
        try{
            Query consulta = sessao.getNamedQuery("MeioTransporte.listar");
            listaMeiosTransporte = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaMeiosTransporte;
    }
    
    public MeioTransporte buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        MeioTransporte meio = null;
        try{
            Query consulta = sessao.getNamedQuery("MeioTransporte.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            meio = (MeioTransporte)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return meio;
    }
    
    public List<MeioTransporte> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<MeioTransporte> listaMeiosTransporte = null;
        
        try{
            Query consulta = sessao.getNamedQuery("MeioTransporte.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaMeiosTransporte = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaMeiosTransporte;
    }
    
    public List<MeioTransporte> buscarPorStatus(String statusInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<MeioTransporte> listaMeiosTransporte = null;
        
        try{
            Query consulta = sessao.getNamedQuery("MeioTransporte.buscarPorStatus");
            consulta.setString("status", statusInformado);
            listaMeiosTransporte = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaMeiosTransporte;
    }
    
    public List<MeioTransporte> buscarPorDestino(String destinoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<MeioTransporte> listaMeiosTransporte = null;
        
        try{
            Query consulta = sessao.getNamedQuery("MeioTransporte.buscarPorDestino");
            consulta.setString("destino", destinoInformado);
            listaMeiosTransporte = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaMeiosTransporte;
    }
    
    public void editar(MeioTransporte meioTransporte){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.update(meioTransporte);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(MeioTransporte meioTransporte){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(meioTransporte);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
}
