package com.mystock.salesreport.dao;

import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;

import java.util.List;

public interface DemandDAO {
    Demand findById(int id);
    void save(Demand demand);
    void update(Demand demand);
    void delete(Demand demand);
    Product findProductById(int id);
    List<Demand> findDemandByProductId(int id);
    List<Demand> findAll();
}
