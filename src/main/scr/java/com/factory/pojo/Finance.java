package com.factory.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "f_id")
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long f_id;

    private String customer;

    private Date nowdate;

    private Date orderdate;

    private Date deliverydate;

    private String state;

    private long arrears;

    private String remarks;

    private String copystate;

    @OneToMany(targetEntity = Worker.class,mappedBy = "finance")
    private Set<Worker> workers=new HashSet<Worker>();

    public String getCopystate() {
        return copystate;
    }

    public void setCopystate(String copystate) {
        this.copystate = copystate;
    }

    public Date getNowdate() {
        return nowdate;
    }

    public void setNowdate(Date nowdate) {
        this.nowdate = nowdate;
    }

    @Override
    public String toString() {
        return "Finance{" + "f_id=" + f_id + ", customer='" + customer + '\'' + ", orderdate=" + orderdate + ", deliverydate=" + deliverydate + ", state='" + state + '\'' + ", arrears=" + arrears + ", remarks='" + remarks + '\'' + ", workers=" + workers + '}';
    }

    public long getF_id() {
        return f_id;
    }

    public void setF_id(long f_id) {
        this.f_id = f_id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }



    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getArrears() {
        return arrears;
    }

    public void setArrears(long arrears) {
        this.arrears = arrears;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

}
