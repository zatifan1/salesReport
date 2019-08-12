package com.mystock.salesreport.service;

import com.mystock.salesreport.dao.DemandDAO;
import com.mystock.salesreport.dao.DemandHibernateDAO;
import com.mystock.salesreport.models.Demand;

import java.util.List;

public class DemandService {
    private DemandDAO demandDao = new DemandHibernateDAO();

    public Demand findDemand(int id){
        return demandDao.findById(id);
    }

    public void saveDemand(Demand demand){
        demandDao.save(demand);
    }

    public void updateDemand(Demand demand){
        demandDao.update(demand);
    }

    public void deleteDemand(Demand demand){
        demandDao.delete(demand);
    }

    public void findProduct(int id){
        demandDao.findProductById(id);
    }

    public List<Demand> findProductDemands(int id) {return demandDao.findDemandByProductId(id);}

    public List<Demand> findAllDemands(){
        return demandDao.findAll();
    }
}
