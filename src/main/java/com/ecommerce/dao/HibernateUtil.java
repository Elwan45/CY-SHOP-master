package com.ecommerce.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory factory;
    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();
        return entityManager;
    }
    public static EntityManager beginTransaction() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }
    public static void commitTransaction(EntityManager em) {

        try{
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction()!=null) em.getTransaction().rollback();
            System.out.println("Error :" +ex.getMessage());
        }
    }
}


