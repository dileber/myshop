package com.drcosu.myshop.shopvp.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.source.UserRepository;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;

public class UserActivity extends BaseShopActivity {

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_user;
    }



    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("下单用户");
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar,uToolBar);
        UserFragment userFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.user_fragment,UserFragment.newInstance());
        new UserPresenter(userFragment, UserRepository.getInstance());

    }

}
