package com.drcosu.myshop;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.source.MonoRepository;
import com.drcosu.myshop.data.source.UserRepository;
import com.drcosu.myshop.shopvp.login.LoginActivity;
import com.drcosu.myshop.shopvp.monolist.MonoListFragment;
import com.drcosu.myshop.shopvp.monolist.MonoListPresenter;
import com.drcosu.myshop.shopvp.user.UserFragment;
import com.drcosu.myshop.shopvp.user.UserPresenter;
import com.drcosu.ndileber.mvp.fragment.BaseFragment;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.SFont;
import com.drcosu.ndileber.tools.permissions.PermissionsManager;
import com.drcosu.ndileber.tools.permissions.PermissionsResultAction;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;

public class MainActivity extends BaseShopActivity implements BaseFragment.OnBaseInteractionListener {

    MonoListFragment monoListFragment;
    UserFragment userFragment;

    UserPresenter userPresenter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    userFragment = ActivityUtils.replaceFragment(getSupportFragmentManager(),R.id.content, UserFragment.newInstance());
                    new UserPresenter(userFragment, UserRepository.getInstance());
                    return true;
                case R.id.navigation_earth:
                    monoListFragment = ActivityUtils.replaceFragment(getSupportFragmentManager(),R.id.content, MonoListFragment.newInstance());
                    new MonoListPresenter(monoListFragment, MonoRepository.getInstance());
                    return true;
            }
            return false;
        }

    };

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_main;
    }

    private boolean isFirst = true;

    private String f = "isFirst";

    SFont action_bar_right_clickable;

    @Override
    protected void initView(Bundle savedInstanceState) {
        requestPermissions();
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("首页");
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar,uToolBar);

        action_bar_right_clickable = findView(R.id.action_bar_right_clickable);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(savedInstanceState!=null){
            isFirst = savedInstanceState.getBoolean(f);
        }
        if(isFirst){
            userFragment = ActivityUtils.replaceFragment(getSupportFragmentManager(),R.id.content, UserFragment.newInstance());
            new UserPresenter(userFragment, UserRepository.getInstance());
            isFirst = !isFirst;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(f, isFirst);
        super.onSaveInstanceState(outState);
    }

    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                //toast("权限OK",Toast.LENGTH_SHORT);
            }

            @Override
            public void onDenied(String permission) {
                //toast(permission+"没有权限，需要用户在设置中添加权限",Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    public void onRightButtonString(BaseFragment fragment, String str, View.OnClickListener onClickListener) {
        if(fragment instanceof UserFragment){
            action_bar_right_clickable.setVisibility(View.VISIBLE);
            action_bar_right_clickable.setText(R.string.dileber_icon_user);
            action_bar_right_clickable.setOnClickListener(onClickListener);

        }else if(fragment instanceof MonoListFragment){
            action_bar_right_clickable.setText(R.string.dileber_icon_plus);
            action_bar_right_clickable.setVisibility(View.VISIBLE);
            action_bar_right_clickable.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void onTitleName(BaseFragment fragment, String title) {
        setTitle(title);
    }

    @Override
    public void onBackPressed() {
        dialogOk("你确定要退出么", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                dialog.dismiss();
                MainActivity.super.onBackPressed();
            }

            @Override
            public void cancel(Dialog dialog) {
                dialog.dismiss();
            }
        });
    }
}