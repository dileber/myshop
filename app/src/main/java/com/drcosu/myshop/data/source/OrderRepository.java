package com.drcosu.myshop.data.source;

import com.drcosu.myshop.data.source.local.OrderLocalDataSource;
import com.drcosu.myshop.data.source.local.ShopLocalDataSource;
import com.drcosu.myshop.data.source.remote.OrderRemoteDataSource;
import com.drcosu.myshop.data.source.remote.ShopRemoteDataSource;
import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderRepository extends BaseRepository<OrderLocalDataSource,OrderRemoteDataSource> implements OrderDataSource {

    public static volatile OrderRepository instance;


    public static OrderRepository getInstance(){
        if(instance==null){
            synchronized (OrderRepository.class){
                if(instance==null){
                    instance = new OrderRepository(OrderLocalDataSource.getInstance(), OrderRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    protected OrderRepository(OrderLocalDataSource localDataSource, OrderRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

    @Override
    public void addOrder(Integer userId, Double addMoney, Double huilv, String detail, BaseCallback<SWrapper> callback) {
        remoteDataSource.addOrder(userId, addMoney, huilv, detail, callback);
    }

    @Override
    public void getOrders(Integer userId, BaseCallback<OrdersWrapper> callback) {
        remoteDataSource.getOrders(userId, callback);
    }

    @Override
    public void getOrder(Integer orderId, BaseCallback<OrderShopWrapper> callback) {
        remoteDataSource.getOrder(orderId, callback);
    }

    @Override
    public void updateOrder(Integer orderId, Integer type, BaseCallback<SWrapper> callback) {
        remoteDataSource.updateOrder(orderId, type, callback);
    }

    @Override
    public void gethuilv(BaseCallback<SWrapper> callback) {
        remoteDataSource.gethuilv(callback);
    }
}
