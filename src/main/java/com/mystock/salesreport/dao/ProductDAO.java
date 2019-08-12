package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;

import java.util.List;

public interface ProductDAO {
    Product findById(int id);
    Product findByName(String name);
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Purchase findPurchaseById(int id);
    Demand findDemandById(int id);
    List<Product> findAll();
}
