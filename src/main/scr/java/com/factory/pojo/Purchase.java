package com.factory.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long p_id;

    private String p_company;

    private String p_material;

    private long p_length;

    private long p_wide;

    private long p_high;

    private long p_quantity;

    private long p_price;

    private String p_state;

    private String p_remarks;

    private Date p_orderdate;

    private Date p_nowdate;

    public Date getP_nowdate() {
        return p_nowdate;
    }

    public void setP_nowdate(Date p_nowdate) {
        this.p_nowdate = p_nowdate;
    }

    public Date getP_orderdate() {
        return p_orderdate;
    }

    public void setP_orderdate(Date p_orderdate) {
        this.p_orderdate = p_orderdate;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public String getP_company() {
        return p_company;
    }

    public void setP_company(String p_company) {
        this.p_company = p_company;
    }

    public String getP_material() {
        return p_material;
    }

    public void setP_material(String p_material) {
        this.p_material = p_material;
    }

    public long getP_length() {
        return p_length;
    }

    public void setP_length(long p_length) {
        this.p_length = p_length;
    }

    public long getP_wide() {
        return p_wide;
    }

    public void setP_wide(long p_wide) {
        this.p_wide = p_wide;
    }

    public long getP_high() {
        return p_high;
    }

    public void setP_high(long p_high) {
        this.p_high = p_high;
    }

    public long getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(long p_quantity) {
        this.p_quantity = p_quantity;
    }

    public long getP_price() {
        return p_price;
    }

    public void setP_price(long p_price) {
        this.p_price = p_price;
    }

    public String getP_state() {
        return p_state;
    }

    public void setP_state(String p_state) {
        this.p_state = p_state;
    }

    public String getP_remarks() {
        return p_remarks;
    }

    public void setP_remarks(String p_remarks) {
        this.p_remarks = p_remarks;
    }
}
