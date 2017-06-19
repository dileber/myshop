package com.drcosu.myshop.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

public class ShopModel  extends SModel {
    private Integer shopid;

    private Integer shopuserid;

    private Integer shopmonoid;

    private Integer shopstatus;

    private Integer shopcount;

    private MonoModel mono;

    public MonoModel getMono() {
        return mono;
    }

    public void setMono(MonoModel mono) {
        this.mono = mono;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getShopuserid() {
        return shopuserid;
    }

    public void setShopuserid(Integer shopuserid) {
        this.shopuserid = shopuserid;
    }

    public Integer getShopmonoid() {
        return shopmonoid;
    }

    public void setShopmonoid(Integer shopmonoid) {
        this.shopmonoid = shopmonoid;
    }

    public Integer getShopstatus() {
        return shopstatus;
    }

    public void setShopstatus(Integer shopstatus) {
        this.shopstatus = shopstatus;
    }

    public Integer getShopcount() {
        return shopcount;
    }

    public void setShopcount(Integer shopcount) {
        this.shopcount = shopcount;
    }
}