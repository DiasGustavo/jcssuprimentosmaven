/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class EstoqueSuprimentoDAO {
    public void salvar(EstoqueSuprimento estoque){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(estoque);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<EstoqueSuprimento> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueSuprimento> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueSuprimento.listar");
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    public EstoqueSuprimento buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        EstoqueSuprimento estoque = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueSuprimento.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            estoque = (EstoqueSuprimento)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return estoque;
    }
    
    public List<EstoqueSuprimento> buscarPorArmazem(ArmazemSuprimento armazemInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueSuprimento> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueSuprimento.buscarPorArmazem");
            consulta.setLong("armazem", armazemInformado.getId());
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    public List<EstoqueSuprimento> buscarPorMateriaArmazem(MateriaPrima materiaPrima , ArmazemSuprimento armazemInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueSuprimento> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueSuprimento.buscarPorMateriaArmazem");
            consulta.setLong("materia", materiaPrima.getId() );
            consulta.setLong("armazem", armazemInformado.getId());
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    /*public List<EstoqueSuprimento> buscarPorEmpresaEstoque(Empresa empresaInformada, String estoque){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueSuprimento> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueSuprimento.buscarPorEmpresaEstoque");
            consulta.setLong("empresa", empresaInformada.getId());
            consulta.setString("estoque", estoque);
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    public List<EstoqueSuprimento> buscarPorNomeEmpresa(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueSuprimento> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueSuprimento.buscarPorNomeEmpresa");
            consulta.setString("nome", nomeInformado);
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }*/
    
    public void editar(EstoqueSuprimento estoque){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(estoque);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(EstoqueSuprimento estoque){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(estoque);
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
