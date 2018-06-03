/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Fornecedor;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class FornecedorDAO {
    public void salvar(Fornecedor fornecedor){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.save(fornecedor);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Fornecedor> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Fornecedor> listaFornecedores = null;
        try{
            Query consulta = sessao.getNamedQuery("Fornecedor.listar");
            listaFornecedores = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaFornecedores;
    }
    
    public Fornecedor buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Fornecedor fornecedor = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Fornecedor.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            fornecedor = (Fornecedor)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return fornecedor;
    }
    
    public void editar(Fornecedor fornecedor){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.update(fornecedor);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Fornecedor fornecedor){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(fornecedor);
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
