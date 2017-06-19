package com.drcosu.myshop.data.source;

import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 2017/6/7.
 */

public interface OrderDataSource extends BaseDataSource{

    void addOrder(Integer userId, Double addMoney,
                  Double huilv, String detail, BaseCallback<SWrapper> callback) ;

    void getOrders( Integer userId ,BaseCallback<OrdersWrapper> callback);

    void getOrder(Integer orderId,BaseCallback<OrderShopWrapper> callback);

    void updateOrder(Integer orderId,Integer type,BaseCallback<SWrapper> callback);

    void gethuilv(BaseCallback<SWrapper> callback);

}
