package com.drcosu.myshop.data.wrapper;

import com.drcosu.myshop.data.model.OrderShopModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderShopWrapper extends SWrapper {

    OrderShopModel data;

    public OrderShopModel getData() {
        return data;
    }

    public void setData(OrderShopModel data) {
        this.data = data;
    }
}
