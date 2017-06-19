package com.drcosu.myshop.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderModel extends SModel{
    private Integer orderid;

    private Integer ordercreateuserid;

    private String orderjson;

    private Integer orderstate;

    private Date ordercreatetime;

    private BigDecimal ordermoney;

    private String ordertitle;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrdercreateuserid() {
        return ordercreateuserid;
    }

    public void setOrdercreateuserid(Integer ordercreateuserid) {
        this.ordercreateuserid = ordercreateuserid;
    }

    public String getOrderjson() {
        return orderjson;
    }

    public void setOrderjson(String orderjson) {
        this.orderjson = orderjson == null ? null : orderjson.trim();
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    public Date getOrdercreatetime() {
        return ordercreatetime;
    }

    public void setOrdercreatetime(Date ordercreatetime) {
        this.ordercreatetime = ordercreatetime;
    }

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    public String getOrdertitle() {
        return ordertitle;
    }

    public void setOrdertitle(String ordertitle) {
        this.ordertitle = ordertitle == null ? null : ordertitle.trim();
    }
}
