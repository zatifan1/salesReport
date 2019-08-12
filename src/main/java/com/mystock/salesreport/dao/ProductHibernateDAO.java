package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;
import com.mystock.salesreport.util.HibernateSessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductHibernateDAO implements ProductDAO {
    public Product findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }
    public Product findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Criteria productCriteria = session.createCriteria(Product.class);
        productCriteria.add(Restrictions.eq("name", name));
        Product product = (Product) productCriteria.uniqueResult();
        session.close();
        return product;
    }

    public void save(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    public void update(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(product);
        tx1.commit();
        session.close();
    }

    public void delete(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(product);
        tx1.commit();
        session.close();
    }

    public Purchase findPurchaseById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Purchase purchase = session.get(Purchase.class, id);
        session.close();
        return purchase;
    }

    public Demand findDemandById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Demand demand = session.get(Demand.class, id);
        session.close();
        return demand;
    }

    public List<Product> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Product> products = (List<Product>)  session.createQuery("From Product").list();
        session.close();
        return products;
    }
}
