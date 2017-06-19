package com.drcosu.myshop.data.wrapper;

import com.drcosu.myshop.data.model.OrderModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrdersWrapper extends SWrapper {

    List<OrderModel> data;

    public List<OrderModel> getData() {
        return data;
    }

    public void setData(List<OrderModel> data) {
        this.data = data;
    }
}
