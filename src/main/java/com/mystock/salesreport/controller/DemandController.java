package com.mystock.salesreport.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mystock.salesreport.models.Answer;
import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.service.DemandService;
import com.mystock.salesreport.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/demand")
public class DemandController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DemandService demandService = new DemandService();

        List<Demand> demands = demandService.findAllDemands();

        PrintWriter writer = resp.getWriter();
        try {
            writer.print(getJsonDemands(demands));
            writer.flush();
        } catch (Exception e) {
            writer.print(Answer.getJsonAnswer(false, e.getMessage()));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        DemandService demandService = new DemandService();

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        try {
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            Demand demand = new Gson().fromJson(json, Demand.class);
            Product product = productService.findByName(jsonObject.get("productName").getAsString());
            if (product.getAmount() >= demand.getAmount()) {
                product.setAmount(product.getAmount() - demand.getAmount());
                demand.setProduct(product);
                demandService.saveDemand(demand);
                productService.updateProduct(product);
                writer.print(Answer.getJsonAnswer(true, "OK"));
                writer.flush();
            } else {
                writer.print(Answer.getJsonAnswer(false, "Не хватает товара в наличии!!!"));
                writer.flush();
            }
        } catch (Exception e) {
            writer.print(Answer.getJsonAnswer(false, e.getMessage()));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private String getJsonDemands(List<Demand> demands) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(demands);
    }
}
