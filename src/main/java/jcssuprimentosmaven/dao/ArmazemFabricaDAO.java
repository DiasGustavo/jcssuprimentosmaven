/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 * @author Gustavo
 * Criação: 26/07/2018 
 * Última Alteração 26/07/2018
 * @version 1.0
 * obs.: criação do armazém da fábrica
 */
public class ArmazemFabricaDAO {
    public void salvar(ArmazemFabrica armazem){
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
    
    public List<ArmazemFabrica> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemFabrica> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemFabrica.listar");
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<ArmazemFabrica> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemFabrica> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemFabrica.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<ArmazemFabrica> buscarPorFabrica(Fabrica fabricaInformada){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemFabrica> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemFabrica.buscarPorFabrica");
            consulta.setLong("fabrica", fabricaInformada.getId());
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<ArmazemFabrica> buscarPorFabricaMateriaPrima(Fabrica fabricaInformada, MateriaPrima materia){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<ArmazemFabrica> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemFabrica.buscarPorFabricaMateriaPrima");
            consulta.setLong("fabrica", fabricaInformada.getId());
            consulta.setLong("materia", materia.getId());
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public ArmazemFabrica buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        ArmazemFabrica armazem = null;
        
        try{
            Query consulta = sessao.getNamedQuery("ArmazemFabrica.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            armazem = (ArmazemFabrica)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazem;
    }
    
    public void editar(ArmazemFabrica armazem){
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
    
    public void excluir(ArmazemFabrica armazem){
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
