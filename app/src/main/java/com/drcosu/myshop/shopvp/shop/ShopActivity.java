package com.drcosu.myshop.shopvp.shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.drcosu.myshop.MainActivity;
import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.source.ShopRepository;
import com.drcosu.ndileber.app.ActivityManager;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;

public class ShopActivity extends BaseShopActivity {

    private static final String USER = "user";

    public static void start(Context context, UserModel userModel){
        Intent intent = new Intent();
        intent.setClass(context,ShopActivity.class);
        intent.putExtra(USER,userModel);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("购物车");
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar,uToolBar);

        UserModel user = (UserModel) getIntent().getSerializableExtra(USER);

        ShopFragment shopFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.content,ShopFragment.newInstance(user));
        new ShopPresenter(shopFragment, ShopRepository.getInstance());

    }
}
