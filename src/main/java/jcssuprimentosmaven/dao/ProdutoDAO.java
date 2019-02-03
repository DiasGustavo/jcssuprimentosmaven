/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Produto;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class ProdutoDAO {
    public Long salvar(Produto produto){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long codigo = null;
        try{
            transacao = sessao.beginTransaction();
            codigo = (Long) sessao.save(produto);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
        return codigo;
    }
    
    public List<Produto> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Produto> produtos = null;
        try{
            Query consulta = sessao.getNamedQuery("Produto.listar");
            produtos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return produtos;
    }
    
    public List<Produto> buscarPorFabrica(Fabrica fabrica){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Produto> produtos = null;
        try{
            Query consulta = sessao.getNamedQuery("Produto.buscarPorFabrica");
            consulta.setLong("fabrica", fabrica.getId());
            produtos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return produtos;
    }
    
    public Produto buscarPorCodigo(Long codigo){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Produto produto = null;
        try{
            Query consulta = sessao.getNamedQuery("Produto.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            produto = (Produto) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return produto;
    }
    
    public void editar(Produto produto){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.update(produto);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
        
    }
    
    public void excluir(Produto produto){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(produto);
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
