/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.dao;
import java.util.List;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Pedido;
import jcssuprimentosmaven.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Gustavo
 */
public class PedidoDAO {
    public void salvar(Pedido pedido){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.save(pedido);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public List<Pedido> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pedido> pedidos = null;
        try{
            Query consulta = sessao.getNamedQuery("Pedido.listar");
            pedidos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pedidos;
    }
    
    public List<Pedido> buscarPorArmazemSuprimento(ArmazemSuprimento armazem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pedido> pedidos = null;
        try{
            Query consulta = sessao.getNamedQuery("Pedido.buscarPorArmazemSuprimento");
            consulta.setLong("armazemSuprimento", armazem.getId());
            pedidos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pedidos;
    }
    
    public List<Pedido> buscarPorArmazemSuprimentoStatus(ArmazemSuprimento armazem){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pedido> pedidos = null;
        try{
            Query consulta = sessao.getNamedQuery("Pedido.buscarPorArmazemSuprimentoStatus");
            consulta.setLong("armazemSuprimento", armazem.getId());
            consulta.setString("status", "Pendente");
            pedidos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pedidos;
    }
    
    
    public List<Pedido> buscarPeidoPorCodigo(Long codigo){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pedido> pedidos = null;
        try{
            Query consulta = sessao.getNamedQuery("Pedido.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            pedidos = consulta.list();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pedidos;
    }
    
    public Pedido buscarPorCodigo(Long codigo){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Pedido pedido = null;
        try{
            Query consulta = sessao.getNamedQuery("Pedido.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            pedido = (Pedido) consulta.uniqueResult();
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            sessao.close();
        }
        return pedido;
    }
    
    public void editar(Pedido pedido){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.update(pedido);
            transacao.commit();
        }catch(RuntimeException ex){
            if(transacao != null){
                transacao.rollback();
            }
        }finally{
            sessao.close();
        }
    }
    
    public void excluir(Pedido pedido){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try{
            transacao = sessao.beginTransaction();
            sessao.delete(pedido);
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
