/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;

import java.util.List;
import jcssuprimentosmaven.domain.PessoaUsuario;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gustavo
 */
public class PessoaUsuarioDAO {
    public void salvar(PessoaUsuario pessoa ){
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
    
    public List<PessoaUsuario> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PessoaUsuario> listaArmazens = null;
        try{
            Query consulta = sessao.getNamedQuery("PessoaUsuario.listar");
            listaArmazens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaArmazens;
    }
    
    public PessoaUsuario buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        PessoaUsuario armazem = null;
        try{
            Query consulta = sessao.getNamedQuery("PessoaUsuario.listar");
            consulta.setLong("codigo", codigoInformado);
            armazem = (PessoaUsuario) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return armazem;
    }
}
