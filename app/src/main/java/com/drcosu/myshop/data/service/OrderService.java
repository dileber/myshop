package com.drcosu.myshop.data.service;

import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 2017/6/7.
 */

public interface OrderService {

    String ADDORDER = "/api/order/v1/addorder";
    String GETORDERS = "/api/order/v1/getorders";
    String GETORDER = "/api/order/v1/getorder";
    String UPORDER = "/api/order/v1/updateorder";
    String GETHUILV = "/api/order/v1/gethuilv";

    @POST(ADDORDER)
    Call<SWrapper> addOrder(@Query("userId") Integer userId, @Query("addMoney")  Double addMoney,
                            @Query("huilv")Double huilv, @Query("detail")  String detail) ;

    @POST(GETORDERS)
    Call<OrdersWrapper> getOrders(@Query("userId") Integer userId);

    @POST(GETORDER)
    Call<OrderShopWrapper> getOrder(@Query("orderId")Integer orderId);

    @POST(UPORDER)
    Call<SWrapper> updateOrder(@Query("orderId")Integer orderId,@Query("type")Integer type);

    @POST(GETHUILV)
    Call<SWrapper> gethuilv();
}
