package com.drcosu.myshop.shopvp.order;

import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.myshop.shopvp.orderlist.OrderListContract;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderContract {

    interface Presenter extends BasePresenter {
        void getOrder(Integer orderId);
        void updateOrder(Integer orderId,Integer type);
    }

    interface View extends BaseView<OrderContract.Presenter> {
        void showOrder(OrderShopWrapper orderShopWrapper);
        void finish();
    }

}
