package com.drcosu.myshop.data.source.local;

import com.drcosu.myshop.data.source.OrderDataSource;
import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderLocalDataSource extends BaseLocalDataSource implements OrderDataSource {

    private static volatile OrderLocalDataSource instance;

    private OrderLocalDataSource() {

    }

    public static OrderLocalDataSource getInstance() {
        if (instance == null) {
            synchronized (OrderLocalDataSource.class) {
                if (instance == null) {
                    instance = new OrderLocalDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void addOrder(Integer userId, Double addMoney, Double huilv, String detail, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void getOrders(Integer userId, BaseCallback<OrdersWrapper> callback) {

    }

    @Override
    public void getOrder(Integer orderId, BaseCallback<OrderShopWrapper> callback) {

    }

    @Override
    public void updateOrder(Integer orderId, Integer type, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void gethuilv(BaseCallback<SWrapper> callback) {

    }
}
