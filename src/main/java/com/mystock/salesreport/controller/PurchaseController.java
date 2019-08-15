package com.mystock.salesreport.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mystock.salesreport.models.Answer;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;
import com.mystock.salesreport.service.ProductService;
import com.mystock.salesreport.service.PurchaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/purchase")
public class PurchaseController extends HttpServlet {

    //возвращает список всех покупок в виде JSON
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PurchaseService purchaseService = new PurchaseService();

        List<Purchase> purchases = purchaseService.findAllPurchases();

        PrintWriter writer = resp.getWriter();
        try {
            writer.print(getJsonPurchases(purchases));
            writer.flush();
        } catch (Exception e) {
            writer.print(Answer.getJsonAnswer(false, e.getMessage()));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    //вместе с запросом ожидае JSON строку вида {"productName": "name", "amount": amount, "price": price, "date": "date"};
    //productName - String; amount, price - int; date - String(Dateformat("yyyy-MM-dd"))
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        PurchaseService purchaseService = new PurchaseService();

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        try {
            //считываем JSON в строку, создаем объект JSON из строки
            String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            //создаем покупку на основе JSON, затем по имени товара возвращаем из БД его объект и зыписываем в покупку
            Purchase purchase = new Gson().fromJson(json, Purchase.class);
            Product product = productService.findByName(jsonObject.get("productName").getAsString());
            product.setAmount(product.getAmount() + purchase.getAmount());
            purchase.setProduct(product);

            //сохраняем в БД покупку, так же изменяем количество продуктов в базе данных
            purchaseService.savePurchase(purchase);
            productService.updateProduct(product);

            //возвращаем ответ в виде JSON
            writer.print(Answer.getJsonAnswer(true, "OK"));
            writer.flush();
        } catch (Exception e) {
            writer.print(Answer.getJsonAnswer(false, e.getMessage()));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private String getJsonPurchases(List<Purchase> purchases) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();;
        return gson.toJson(purchases);
    }
}
