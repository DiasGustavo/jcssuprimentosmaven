/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Transporte;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class TransporteDAO {
    public void salvar(Transporte transporte){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(transporte);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Transporte> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Transporte> listaTransportes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Transporte.listar");
            listaTransportes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaTransportes;
    }
    
    public Transporte buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transporte transporte = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Transporte.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            transporte = (Transporte)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return transporte;
    }
    
    public List<Transporte> buscarPorEmpresa(Empresa empresa){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Transporte> listaTransportes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Transporte.buscarPorEmpresa");
            consulta.setLong("empresa", empresa.getId());
            listaTransportes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaTransportes;
    }
    
    public List<Transporte> buscarPorArmazemFabrica(ArmazemFabrica armazemFabrica){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Transporte> listaTransportes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Transporte.buscarPorArmazemFabrica");
            consulta.setLong("armazemFabrica", armazemFabrica.getId());
            listaTransportes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaTransportes;
    }
    
     public List<Transporte> buscarPorDestino(String destino){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Transporte> listaTransportes = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Transporte.buscarPorDestino");
            consulta.setString("destino", destino);
            listaTransportes = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaTransportes;
    }
    
    public void editar(Transporte transporte){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(transporte);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Transporte transporte){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(transporte);
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
