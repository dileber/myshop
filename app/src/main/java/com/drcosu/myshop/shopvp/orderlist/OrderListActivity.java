package com.drcosu.myshop.shopvp.orderlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.source.OrderRepository;
import com.drcosu.myshop.data.source.ShopRepository;
import com.drcosu.myshop.shopvp.shop.ShopActivity;
import com.drcosu.myshop.shopvp.shop.ShopFragment;
import com.drcosu.myshop.shopvp.shop.ShopPresenter;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;

public class OrderListActivity extends BaseShopActivity {

    private static final String USER = "user";

    public static void start(Context context, UserModel userModel){
        Intent intent = new Intent();
        intent.setClass(context,OrderListActivity.class);
        intent.putExtra(USER,userModel);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("订单列表");
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar,uToolBar);

        UserModel user = (UserModel) getIntent().getSerializableExtra(USER);

        OrderListFragment orderListFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.content,OrderListFragment.newInstance(user));
        new OrderListPresenter(orderListFragment, OrderRepository.getInstance());

    }
}
