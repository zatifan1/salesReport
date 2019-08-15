package com.mystock.salesreport.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Answer {
    private Boolean result;
    private String message;

    public Answer() {
    }

    public Answer(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public Boolean getAdd() {
        return result;
    }

    public void setAdd(Boolean add) {
        result = add;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getJsonAnswer(Boolean isAdd, String message) {
        if(message == null){
            message = "Error";
        }
        Answer answer = new Answer(isAdd, message);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(answer);
    }
}
