package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;
import com.mystock.salesreport.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PurchaseHibernateDAO implements PurchaseDAO {

    public Purchase findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Purchase purchase = session.get(Purchase.class, id);
        session.close();
        return purchase;
    }

    public void save(Purchase purchase) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(purchase);
        tx1.commit();
        session.close();
    }

    public void update(Purchase purchase) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(purchase);
        tx1.commit();
        session.close();
    }

    public void delete(Purchase purchase) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(purchase);
        tx1.commit();
        session.close();
    }

    public Product findProductById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    public List<Purchase> findPurchaseByProductIdDate(String name, String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Query query = session.createQuery("FROM Purchase WHERE date <= :date AND Product_id = (SELECT id FROM Product WHERE name = :name)");
        query.setParameter("name", name);
        query.setDate("date", startDate);
        List<Purchase> purchases = (List<Purchase>) query.list();
        transaction.commit();
        session.close();
        return purchases;
    }

    public List<Purchase> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Purchase> purchases= (List<Purchase>)  session.createQuery("From Purchase").list();
        session.close();
        return purchases;
    }
}
