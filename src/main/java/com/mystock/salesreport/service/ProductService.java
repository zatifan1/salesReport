package com.mystock.salesreport.service;

import com.mystock.salesreport.dao.ProductDAO;
import com.mystock.salesreport.dao.ProductHibernateDAO;
import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductHibernateDAO();

    public Product findProduct(int id) {
        return productDAO.findById(id);
    }

    public Product findByName(String name){return productDAO.findByName(name);}

    public void saveProduct(Product product) {

        productDAO.save(product);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    public void deleteProduct(Product product) {
        productDAO.delete(product);
    }

    public Purchase findPurchase(int id) {
        return productDAO.findPurchaseById(id);
    }

    public Demand findDemand(int id) {
        return productDAO.findDemandById(id);
    }

    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }
}
