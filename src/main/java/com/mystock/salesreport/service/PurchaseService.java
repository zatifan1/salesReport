package com.mystock.salesreport.service;

import com.mystock.salesreport.dao.PurchaseDAO;
import com.mystock.salesreport.dao.PurchaseHibernateDAO;
import com.mystock.salesreport.models.Purchase;

import java.util.List;

public class PurchaseService {
    private PurchaseDAO PurchaseDAO = new PurchaseHibernateDAO();

    public Purchase findPurchase(int id){
        return PurchaseDAO.findById(id);
    }

    public void savePurchase(Purchase Purchase){
        PurchaseDAO.save(Purchase);
    }

    public void updatePurchase(Purchase Purchase){
        PurchaseDAO.update(Purchase);
    }

    public void deletePurchase(Purchase Purchase){
        PurchaseDAO.delete(Purchase);
    }

    public void findProduct(int id){
        PurchaseDAO.findProductById(id);
    }

    public List<Purchase> findProductPurchases(int id){
        return PurchaseDAO.findPurchaseByProductId(id);
    }

    public List<Purchase> findAllPurchases(){
        return PurchaseDAO.findAll();
    }
}
