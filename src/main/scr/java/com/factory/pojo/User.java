package com.factory.pojo;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long u_id;

    private String username;

    private String password;

    private String f_power;

    private String w_power;

    private String g_power;

    private String s_power;

    private String u_power;

    private String salt;

    private String name;

    private Date lastdate;

    private String company_logo;

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getF_power() {
        return f_power;
    }

    public void setF_power(String f_power) {
        this.f_power = f_power;
    }

    public String getW_power() {
        return w_power;
    }

    public void setW_power(String w_power) {
        this.w_power = w_power;
    }

    public String getG_power() {
        return g_power;
    }

    public void setG_power(String g_power) {
        this.g_power = g_power;
    }

    public String getS_power() {
        return s_power;
    }

    public void setS_power(String s_power) {
        this.s_power = s_power;
    }

    public String getU_power() {
        return u_power;
    }

    public void setU_power(String u_power) {
        this.u_power = u_power;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
