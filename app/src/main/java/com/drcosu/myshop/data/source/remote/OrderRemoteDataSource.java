package com.drcosu.myshop.data.source.remote;

import com.drcosu.myshop.app.SHOPConfig;
import com.drcosu.myshop.data.service.MonoService;
import com.drcosu.myshop.data.service.OrderService;
import com.drcosu.myshop.data.source.OrderDataSource;
import com.drcosu.myshop.data.source.local.OrderLocalDataSource;
import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderRemoteDataSource extends BaseRemoteDataSource implements OrderDataSource {
    HRetrofit hRetrofit;
    OrderService orderService;
    private static volatile OrderRemoteDataSource instance;

    private OrderRemoteDataSource() {
        hRetrofit = HRetrofit.getInstence(SHOPConfig.URL);
        orderService = hRetrofit.retrofit.create(OrderService.class);
    }

    public static OrderRemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (OrderRemoteDataSource.class) {
                if (instance == null) {
                    instance = new OrderRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void addOrder(Integer userId, Double addMoney, Double huilv, String detail, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = orderService.addOrder(userId, addMoney, huilv, detail);

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper = response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getOrders(Integer userId, final BaseCallback<OrdersWrapper> callback) {
        Call<OrdersWrapper> call = orderService.getOrders(userId);

        call.enqueue(new RetCallback<OrdersWrapper>() {
            @Override
            protected void onSuccess(Call<OrdersWrapper> call, Response<OrdersWrapper> response) {
                OrdersWrapper ordersWrapper = response.body();
                if(ordersWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<OrdersWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getOrder(Integer orderId, final BaseCallback<OrderShopWrapper> callback) {
        Call<OrderShopWrapper> call = orderService.getOrder(orderId);

        call.enqueue(new RetCallback<OrderShopWrapper>() {
            @Override
            protected void onSuccess(Call<OrderShopWrapper> call, Response<OrderShopWrapper> response) {
                OrderShopWrapper orderShopWrapper = response.body();
                if(orderShopWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<OrderShopWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void updateOrder(Integer orderId, Integer type, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = orderService.updateOrder(orderId, type);

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper = response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void gethuilv(final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = orderService.gethuilv();

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper = response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }
}