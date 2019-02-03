/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class EmpresaDAO {
    public Long salvar(Empresa empresa){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long codigo = null;
        try{
            transacao = sessao.beginTransaction();
            codigo = (Long)sessao.save(empresa);
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
    
    public List<Empresa> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Empresa> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Empresa.listar");
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public List<Empresa> buscarPorNome(String nomeInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Empresa> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Empresa.buscarPorNome");
            consulta.setString("nome", nomeInformado);
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
     public List<Empresa> buscarPorJogador(Jogador jogadorInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Empresa> listaEmpresas = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Empresa.buscarPorJogador");
            consulta.setLong("jogador", jogadorInformado.getId());
            listaEmpresas = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return listaEmpresas;
    }
    
    public Empresa buscarPorCodigo(Long codigoInformado){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Empresa empresa = null;
        
        try{
            Query consulta = sessao.getNamedQuery("Empresa.buscarPorCodigo");
            consulta.setLong("codigo", codigoInformado);
            empresa = (Empresa)consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return empresa;
    }
    
    public void editar(Empresa empresa){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(empresa);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Empresa empresa){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(empresa);
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
