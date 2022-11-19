package com.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    public static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
        }catch(Throwable e) {
            System.out.println("failed to create sessionFactory"+e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
