/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import org.hibernate.Query;

/**
 *
 * @author Gustavo
 */
public class FabricaDAO {
    public void salvar(Fabrica armazem){
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
    
    public List<Fabrica> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Fabrica> listaArmazens = null;
        try{
            Query consulta = sessao.getNamedQuery("Fabrica.listar");
            listaArmazens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaArmazens;
    }
    
    public List<Fabrica> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Fabrica> listaArmazens = null;
        try{
            Query consulta = sessao.getNamedQuery("Fabrica.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaArmazens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaArmazens;
    }
    
    public List<Fabrica> buscarPorEmpresa(Empresa empresa){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Fabrica> listaArmazens = null;
        try{
            Query consulta = sessao.getNamedQuery("Fabrica.buscarPorEmpresa");
            consulta.setLong("empresa", empresa.getId());
            listaArmazens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaArmazens;
    }
    
    public Fabrica buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Fabrica armazem = null;
        try{
            Query consulta = sessao.getNamedQuery("Fabrica.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            armazem = (Fabrica) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazem;
    }
    
    public void editar(Fabrica armazem){
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
    
    public void excluir(Fabrica armazem){
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
