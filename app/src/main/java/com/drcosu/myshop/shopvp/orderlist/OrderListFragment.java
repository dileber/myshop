package com.drcosu.myshop.shopvp.orderlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopFragment;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.wrapper.OrdersWrapper;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;


public class OrderListFragment extends BaseShopFragment implements OrderListContract.View {

    private static final String USER = "user";

    private UserModel user;

    public static OrderListFragment newInstance(UserModel user) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean retain() {
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (UserModel) getArguments().getSerializable(USER);
        }
    }
    OrderListContract.Presenter mPresenter;

    @Override
    public void setPresenter(OrderListContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }

    @Override
    public void showOrders(OrdersWrapper ordersWrapper) {
        orderListAdapter.clean();
        if(ordersWrapper.getData()!=null){
            orderListAdapter.addData(ordersWrapper.getData());
        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_order_list;
    }

    RecyclerView order_list;
    OrderListAdapter orderListAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        order_list = findView(R.id.order_list);
        order_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderListAdapter = new OrderListAdapter();
        order_list.setAdapter(orderListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getOrders(user.getUserid());
    }

    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }
}
