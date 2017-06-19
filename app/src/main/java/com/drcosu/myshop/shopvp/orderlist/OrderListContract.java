package com.drcosu.myshop.shopvp.orderlist;

import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.myshop.shopvp.monolist.MonoListContract;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderListContract {

    interface Presenter extends BasePresenter {
        void getOrders(Integer userId);
    }

    interface View extends BaseView<OrderListContract.Presenter> {
        void showOrders(OrdersWrapper ordersWrapper);
    }


}
