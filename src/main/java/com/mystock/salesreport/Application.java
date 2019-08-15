package com.mystock.salesreport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) throws IOException {

    }

    public static String demandPOST(String name, int amount, int price, String date) throws IOException {
        URL url = new URL("http://localhost:8080/demand");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; UTF-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        String jsonInputString = "{\"productName\":\"" + name + "\",\"amount\":" + amount + ",\"price\":" + price + ",\"date\":\"" + date + "\"}";

        return getString(con, jsonInputString);
    }

    public static String demandGET() throws IOException {
        URL url = new URL("http://localhost:8080/demand");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        return getString(con);
    }

    public static String purchasePOST(String name, int amount, int price, String date) throws IOException {
        URL url = new URL("http://localhost:8080/purchase");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; UTF-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        String jsonInputString = "{\"productName\":\"" + name + "\",\"amount\":" + amount + ",\"price\":" + price + ",\"date\":\"" + date + "\"}";

        return getString(con, jsonInputString);
    }

    public static String purchaseGET() throws IOException {
        URL url = new URL("http://localhost:8080/purchase");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        return getString(con);
    }

    public static String newProductPOST(String name) throws IOException {
        URL url = new URL("http://localhost:8080/newproduct");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; UTF-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

//        String jsonInputString = "{\"name\": \"" + name + "\", \"amount\":" + amount + "}";
        String jsonInputString = "{\"name\": \"" + name + "\"}";

        return getString(con, jsonInputString);
    }

    public static String newProductGET() throws IOException {
        URL url = new URL("http://localhost:8080/newproduct");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        return getString(con);
    }

    public static String salesreportGET(String name, String date) throws IOException {
        URL url = new URL("http://localhost:8080/salesreport?productName=" + name + "&date=" + date);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        return getString(con);
    }

    private static String getString(HttpURLConnection con) throws IOException {
        con.setRequestMethod("GET");

        int code = con.getResponseCode();
        System.out.println(code);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            return response.toString();
        }
    }

    private static String getString(HttpURLConnection con, String jsonInputString) throws IOException {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println(code);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            return response.toString();
        }
    }
}
