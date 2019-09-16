/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class EstoqueDistribuicaoDAO {
    public void salvar(EstoqueDistribuicao estoque){
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
    
    public List<EstoqueDistribuicao> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueDistribuicao> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueDistribuicao.listar");
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    public EstoqueDistribuicao buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        EstoqueDistribuicao estoque = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueDistribuicao.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            estoque = (EstoqueDistribuicao)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return estoque;
    }
    
    /*public List<EstoqueDistribuicao> buscarPorEmpresa(Empresa empresaInformada){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueDistribuicao> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueDistribuicao.buscarPorEmpresa");
            consulta.setLong("empresa", empresaInformada.getId());
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
     public List<EstoqueDistribuicao> buscarPorEmpresaEstoque(Empresa empresaInformada, String estoque){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueDistribuicao> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueDistribuicao.buscarPorEmpresaEstoque");
            consulta.setLong("empresa", empresaInformada.getId());
            consulta.setString("estoque", estoque);
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }*/
    
    public List<EstoqueDistribuicao> buscarPorProduto(Produto produto){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueDistribuicao> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueDistribuicao.buscarPorProduto");
            consulta.setLong("produto", produto.getId());
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    public List<EstoqueDistribuicao> buscarPorArmazem(ArmazemDistribuicao armazem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<EstoqueDistribuicao> listaEstoques = null;
        
        try{
            Query consulta = sessao.getNamedQuery("EstoqueDistribuicao.buscarPorArmazem");
            consulta.setLong("armazem", armazem.getId());
            listaEstoques = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEstoques;
    }
    
    
    public void editar(EstoqueDistribuicao estoque){
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
     
    public void excluir(EstoqueDistribuicao estoque){
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
