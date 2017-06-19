package com.drcosu.myshop.shopvp.orderlist;

import android.support.annotation.NonNull;

import com.drcosu.myshop.data.source.OrderRepository;
import com.drcosu.myshop.data.source.ShopRepository;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.myshop.shopvp.shop.ShopContract;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.UDialog;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderListPresenter  extends DileberPresenter<OrderListContract.View,OrderRepository> implements OrderListContract.Presenter {

    public OrderListPresenter(@NonNull OrderListContract.View view, @NonNull OrderRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getOrders(Integer userId) {
        mDataSource.getOrders(userId, new BaseDataSource.BaseCallback<OrdersWrapper>() {
            @Override
            public void onSuccess(OrdersWrapper ordersWrapper) {
                if(ordersWrapper.getState()==0){
                    mView.showOrders(ordersWrapper);
                }else if(ordersWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,ordersWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());

            }
        });
    }
}
