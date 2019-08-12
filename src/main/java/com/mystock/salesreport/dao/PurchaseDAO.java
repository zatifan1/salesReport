package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;

import java.util.List;

public interface PurchaseDAO {
    Purchase findById(int id);
    void save(Purchase purchase);
    void update(Purchase purchase);
    void delete(Purchase purchase);
    Product findProductById(int id);
    List<Purchase> findPurchaseByProductId(int id);
    List<Purchase> findAll();
}
