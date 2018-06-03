/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Gustavo
 */
public class HibernateUtil {

    /*private static final SessionFactory sessionFactory = buildSessionFactory();
    
     private static SessionFactory buildSessionFactory(){
     try{
     //criar a sessão a partir do hibernate.cfg.xml
     Configuration configuration = new Configuration();
     configuration.configure();

     ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
     SessionFactory session = configuration.buildSessionFactory(serviceRegistry);

     return session;
            
     }catch(Throwable ex){
     System.err.println("Falha ao tentar criar o SessionFactory." + ex);
     throw new ExceptionInInitializerError(ex);
     }
     }
    
     public static SessionFactory getSessionFactory(){
     return sessionFactory;
     }
    
     public static Connection getConexao() {
     Session sessao = sessionFactory.openSession();

     Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
     @Override
     public Connection execute(Connection conn) throws SQLException {
     return conn;
     }
     });
     return conexao;
     }*/
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {
            //carrega as configurações do hibernate do arquivo hibernate.cfg.xml    
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("falha do hibernate " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
