package com.drcosu.myshop.data.model;

import java.util.List;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderShopModel {

    List<ShopModel> shopModels;

    Double jiajia;

    Double huilv;

    String detail;

    public List<ShopModel> getShopModels() {
        return shopModels;
    }

    public void setShopModels(List<ShopModel> shopModels) {
        this.shopModels = shopModels;
    }

    public Double getJiajia() {
        return jiajia;
    }

    public void setJiajia(Double jiajia) {
        this.jiajia = jiajia;
    }

    public Double getHuilv() {
        return huilv;
    }

    public void setHuilv(Double huilv) {
        this.huilv = huilv;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
