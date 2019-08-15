package com.mystock.salesreport.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mystock.salesreport.models.Answer;
import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;
import com.mystock.salesreport.service.DemandService;
import com.mystock.salesreport.service.ProductService;
import com.mystock.salesreport.service.PurchaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/salesreport")
public class SalesReportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        DemandService demandService = new DemandService();
        PurchaseService purchaseService = new PurchaseService();

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        int purchaseCost = 0;
        int demandCost = 0;

        try {
            String paramName1 = "productName";
            String paramValue1 = req.getParameter(paramName1);
            String paramName2 = "date";
            String paramValue2 = req.getParameter(paramName2);

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(paramValue2);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Product product = productService.findByName(paramValue1);

            if(product != null){
            List<Demand> demands = demandService.findDemands(paramValue1, dateFormat.format(date));
            List<Purchase> purchases = purchaseService.findPurchases(paramValue1, dateFormat.format(date));

            for (Purchase purchase : purchases) {
                purchaseCost += purchase.getAmount() * purchase.getPrice();
            }
            for (Demand demand : demands) {
                demandCost += demand.getAmount() * demand.getPrice();
            }
            int profit = demandCost - purchaseCost;
            writer.print(getJsonAnswer(true, String.valueOf(profit)));
            writer.flush();
            } else {
                writer.print(getJsonAnswer(false, "Нет такого продукта!"));
                writer.flush();
            }
        } catch (Exception e) {
            writer.print(getJsonAnswer(false, e.getMessage()));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private String getJsonAnswer(Boolean isAdd, String message) {
        Answer answer = new Answer(isAdd, message);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(answer);
    }
}
