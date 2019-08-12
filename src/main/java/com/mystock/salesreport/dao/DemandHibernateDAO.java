package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.util.HibernateSessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

public class DemandHibernateDAO implements DemandDAO {

    public Demand findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Demand demand = session.get(Demand.class, id);
        session.close();
        return demand;
    }

    public void save(Demand demand) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(demand);
        tx1.commit();
        session.close();
    }

    public void update(Demand demand) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(demand);
        tx1.commit();
        session.close();
    }

    public void delete(Demand demand) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(demand);
        tx1.commit();
        session.close();
    }

    public Product findProductById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    public List<Demand> findDemandByProductId(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Demand WHERE Product_id = :id");
        query.setParameter("id", id);
        List<Demand> demands = (List<Demand>) query.list();
        session.close();
        return demands;
    }

    public List<Demand> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Demand> demands = (List<Demand>)  session.createQuery("From Demand").list();
        session.close();
        return demands;
    }
}
