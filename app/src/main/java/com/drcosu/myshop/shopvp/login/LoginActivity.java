package com.drcosu.myshop.shopvp.login;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.drcosu.myshop.MainActivity;
import com.drcosu.myshop.R;
import com.drcosu.myshop.app.SHOPPrefer;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.source.UserRepository;
import com.drcosu.myshop.shopvp.user.UserActivity;
import com.drcosu.ndileber.tools.HPref;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.net.TCookie;
import com.drcosu.ndileber.utils.UToolBar;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseShopActivity implements LoginContract.View {
    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,LoginActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_login;
    }

    EditText login_name,login_pass;
    TextView login_button;

    @Override
    protected void initView(Bundle savedInstanceState) {
        if(!TCookie.getCookie().equals("")){
            toHome();
            return;
        }

        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("登录");
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar,uToolBar);
        login_name = findView(R.id.login_name);
        login_pass = findView(R.id.login_pass);
        login_button = findView(R.id.login_button);

        login_name.setText(SHOPPrefer.get(SHOPPrefer.USERNAME,"",String.class));
        login_pass.setText(SHOPPrefer.get(SHOPPrefer.USERPASS,"",String.class));

        mPresenter = new LoginPresenter(this,UserRepository.getInstance());
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SHOPPrefer.push(SHOPPrefer.USERNAME,login_name.getText().toString());
                SHOPPrefer.push(SHOPPrefer.USERPASS,login_pass.getText().toString());
                mPresenter.login(login_name.getText().toString(),login_pass.getText().toString());
            }
        });

    }

    LoginContract.Presenter mPresenter;

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void toHome() {
        MainActivity.start(this);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter!=null){
            mPresenter.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
    }

}


