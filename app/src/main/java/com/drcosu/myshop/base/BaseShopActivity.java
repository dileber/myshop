package com.drcosu.myshop.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.drcosu.myshop.shopvp.login.LoginActivity;
import com.drcosu.ndileber.app.ActivityManager;
import com.drcosu.ndileber.mvp.ubase.UBaseActivity;

/**
 * Created by shidawei on 2017/6/5.
 */

public abstract class BaseShopActivity extends UBaseActivity {

    public final static String activityStack = "shop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(this instanceof LoginActivity)){
            ActivityManager.getInstance(activityStack).pushActivity(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!(this instanceof LoginActivity)){
            ActivityManager.getInstance(activityStack).popActivity(this);
        }
    }
}
