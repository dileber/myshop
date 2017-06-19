package com.drcosu.myshop.app;

import android.app.Dialog;
import android.widget.Toast;

import com.drcosu.myshop.MainActivity;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.source.UserRepository;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.myshop.shopvp.login.LoginActivity;
import com.drcosu.ndileber.app.ActivityManager;
import com.drcosu.ndileber.app.SApplication;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.net.RetCallback;
import com.drcosu.ndileber.tools.net.TCookie;
import com.drcosu.ndileber.tools.string.MD5;
import com.drcosu.ndileber.utils.ActivityUtils;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 2017/4/20.
 */
public class SHOPApplication extends SApplication{


    @Override
    public void start() {
        loadDeaultFont = true;
        crash = true;
    }

    @Override
    protected void init() {

    }

    @Override
    public void appForbidden(final Call call, Response response, final RetCallback retCallback) {
        TCookie.clearCookie();
        UDialog.dialogOk("登录过时，重新登录？", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                String login_name = (SHOPPrefer.get(SHOPPrefer.USERNAME,"",String.class));
                String login_pass = (SHOPPrefer.get(SHOPPrefer.USERPASS,"",String.class));
                UserRepository.getInstance().login(login_name, MD5.getStringMD5(login_pass), new BaseDataSource.BaseCallback<UserWrapper>() {
                    @Override
                    public void onSuccess(UserWrapper userWrapper) {
                        if(userWrapper.getState()== 0){
                            call.clone().enqueue(retCallback);
                        }else if(userWrapper.getState()==-1){
                            UUi.toast(ActivityManager.getCurrentActivity(),userWrapper.getMsg(), Toast.LENGTH_SHORT);
                            LoginActivity.start(ActivityManager.getCurrentActivity());
                            ActivityManager.getInstance(BaseShopActivity.activityStack).finishAllActivity();
                        }
                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        UUi.toast(ActivityManager.getCurrentActivity(),e.getMessage(), Toast.LENGTH_SHORT);
                        LoginActivity.start(ActivityManager.getCurrentActivity());
                        ActivityManager.getInstance(BaseShopActivity.activityStack).finishAllActivity();
                    }
                });
                dialog.dismiss();
            }

            @Override
            public void cancel(Dialog dialog) {
                LoginActivity.start(ActivityManager.getCurrentActivity());
                ActivityManager.getInstance(BaseShopActivity.activityStack).finishAllActivity();
                dialog.dismiss();
            }
        }).show();

    }
}
