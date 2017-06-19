package com.drcosu.myshop.shopvp.order;

import android.support.annotation.NonNull;

import com.drcosu.myshop.data.source.OrderRepository;
import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.UDialog;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderPresenter extends DileberPresenter<OrderContract.View,OrderRepository> implements OrderContract.Presenter {

    public OrderPresenter(@NonNull OrderContract.View view, @NonNull OrderRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getOrder(Integer orderId) {
        mView.loading();
        mDataSource.getOrder(orderId, new BaseDataSource.BaseCallback<OrderShopWrapper>() {
            public void onSuccess(OrderShopWrapper orderShopWrapper) {
                mView.loadDialogDismiss();
                if(orderShopWrapper.getState()==0){
                    mView.showOrder(orderShopWrapper);
                }else{
                    mView.showAlert(UDialog.DIALOG_ERROR,orderShopWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.loadDialogDismiss();
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());

            }
        });
    }

    @Override
    public void updateOrder(Integer orderId, Integer type) {
        mDataSource.updateOrder(orderId, type, new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                if(sWrapper.getState()==0){
                    mView.finish();
                }else{
                    mView.showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());

            }
        });
    }
}
