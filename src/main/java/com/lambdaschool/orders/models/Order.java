package com.lambdaschool.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordernum;
    private double orderamount;
    private double advanceamount;
    private String orderdescription;

    //fixme
    //@ManyToOne
    //    @JoinColumn(name = "restaurantid",
    //            nullable = false)
    //    @JsonIgnoreProperties("menus")
    //    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    private Customer customer;

    //@ManyToMany()
    //    @JoinTable(name = "restaurantpayments",
    //            joinColumns = @JoinColumn(name = "restaurantid"),
    //            inverseJoinColumns = @JoinColumn(name = "paymentid"))
    //    @JsonIgnoreProperties("restaurants")
    //    Set<Payment> payments = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "orderspayments", joinColumns = @JoinColumn(name = "ordernum"), inverseJoinColumns = @JoinColumn(name = "paymentid"))
    @JsonIgnoreProperties("orders")
    Set<Payment> payments = new HashSet<>();

    public Order() {
    }

    public Order(double orderamount, double advanceamount, Customer customer, String orderdescription) {
        this.orderamount = orderamount;
        this.advanceamount = advanceamount;
        this.orderdescription = orderdescription;
        this.customer = customer;
    }

    public long getOrdernum() {
        return ordernum;
    }

    public double getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(double orderamount) {
        this.orderamount = orderamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void addPayments(Payment payment) {
        Set<Payment> currPayments = getPayments();
        currPayments.add(payment);
        setPayments(currPayments);
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
