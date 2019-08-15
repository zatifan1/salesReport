package com.mystock.salesreport.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mystock.salesreport.models.Answer;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/newproduct")
public class NewProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        List<Product> products = productService.findAllProducts();

        PrintWriter writer = resp.getWriter();
        try {
            writer.print(getJsonProducts(products));
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
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        try {
            Product product = new Gson().fromJson(req.getReader(), Product.class);
            System.out.println(product);

            productService.saveProduct(product);
            writer.print(Answer.getJsonAnswer(true, "OK"));
            writer.flush();
        } catch (Exception e) {
            writer.print(Answer.getJsonAnswer(false, e.getMessage()));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private String getJsonProducts(List<Product> products) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        ;
        return gson.toJson(products);
    }
}
