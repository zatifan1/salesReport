package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.util.HibernateSessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Demand> findDemandByProductIdDate(String name, String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Query query = session.createQuery("FROM Demand WHERE date <= :date AND Product_id = (SELECT id FROM Product WHERE name = :name)");
        query.setParameter("name", name);
        query.setDate("date", startDate);
        List<Demand> demands = (List<Demand>) query.list();
        transaction.commit();
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
