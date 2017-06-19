package com.drcosu.myshop.shopvp.order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.model.OrderModel;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.source.OrderRepository;
import com.drcosu.myshop.shopvp.shop.ShopActivity;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;

public class OrderActivity extends BaseShopActivity {


    private static final String ORDER = "order";

    public static void start(Context context, OrderModel order){
        Intent intent = new Intent();
        intent.setClass(context,OrderActivity.class);
        intent.putExtra(ORDER,order);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("订单详情");
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar,uToolBar);

        OrderModel order = (OrderModel) getIntent().getSerializableExtra(ORDER);

        OrderFragment orderFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.content,OrderFragment.newInstance(order));
        new OrderPresenter(orderFragment, OrderRepository.getInstance());
    }
}
