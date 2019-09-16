/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.Pessoa;
import jcssuprimentosmaven.domain.PessoaUsuario;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class PessoaDAO {
    public void salvar(Pessoa pessoa ){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(pessoa);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
            throw ex;
        }finally{
            sessao.close();
        }
    }
    
    public List<Pessoa> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pessoa> listaArmazens = null;
        try{
            Query consulta = sessao.getNamedQuery("Pessoa.listar");
            listaArmazens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaArmazens;
    }
    
    public Pessoa buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Pessoa pessoa = null;
        try{
            Query consulta = sessao.getNamedQuery("Pessoa.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            pessoa = (Pessoa) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pessoa;
    }
    
    public List<Pessoa> buscarPorMatricula(String codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pessoa> pessoas = null;
        try{
            Query consulta = sessao.getNamedQuery("Pessoa.buscarPorMatricula");
            consulta.setString("matricula", codigoInformado);
            pessoas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pessoas;
    }
    
    public void editar(Pessoa pessoa ){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(pessoa);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
            throw ex;
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Pessoa pessoa ){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(pessoa);
            transacao.commit();
        }catch(RuntimeException ex){
            if (transacao != null){
                transacao.rollback();
            }
            throw ex;
        }finally{
            sessao.close();
        }
    }
}
