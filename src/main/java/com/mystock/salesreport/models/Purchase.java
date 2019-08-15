package com.mystock.salesreport.models;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Expose
    @Column(name = "amount")
    private Integer amount;

    @Expose
    @Column(name = "price")
    private Integer price;

    @Expose
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Purchase() {
    }

    public Purchase(Product product, Integer amount, Integer price, Date date) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
