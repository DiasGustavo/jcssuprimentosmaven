/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.Mensagem;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class MensagemDAO {
    public void salvar(Mensagem mensagem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(mensagem);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Mensagem> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Mensagem> listaMensagens = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Mensagem.listar");
            listaMensagens = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaMensagens;
    }
    
    public Mensagem buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Mensagem mensagem = null;
        try{
            Query consulta = sessao.getNamedQuery("Mensagem.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            mensagem = (Mensagem)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return mensagem;
    }
    
    public void editar(Mensagem mensagem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(mensagem);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Mensagem mensagem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(mensagem);
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
