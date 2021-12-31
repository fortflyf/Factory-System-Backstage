package com.factory.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "w_id")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long w_id;

    private String material;

    private String machining;

    private long length;

    private long wide;

    private long high;

    private long quantity;

    private long price;

    private String abrasives;


    @ManyToOne(targetEntity = Finance.class,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="keyid",referencedColumnName = "f_id")
    private Finance finance=new Finance();

    public Finance getFinance() {
        return finance;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getAbrasives() {
        return abrasives;
    }

    public void setAbrasives(String abrasives) {
        this.abrasives = abrasives;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public long getW_id() {
        return w_id;
    }

    public void setW_id(long w_id) {
        this.w_id = w_id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMachining() {
        return machining;
    }

    public void setMachining(String machining) {
        this.machining = machining;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getWide() {
        return wide;
    }

    public void setWide(long wide) {
        this.wide = wide;
    }

    public long getHigh() {
        return high;
    }

    public void setHigh(long high) {
        this.high = high;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Worker3{" + "w_id=" + w_id + ", material='" + material + '\'' + ", machining='" + machining + '\'' + ", length=" + length + ", wide=" + wide + ", high=" + high + ", quantity=" + quantity + ", finance=" + finance + '}';
    }
}
