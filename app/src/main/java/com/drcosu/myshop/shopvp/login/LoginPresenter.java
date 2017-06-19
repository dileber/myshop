package com.drcosu.myshop.shopvp.login;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.drcosu.myshop.data.source.UserRepository;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UText;
import com.drcosu.ndileber.tools.string.MD5;

/**
 * Created by shidawei on 16/8/6.
 */
public class LoginPresenter extends DileberPresenter<LoginContract.View,UserRepository> implements LoginContract.Presenter{

    public LoginPresenter(@NonNull LoginContract.View view, @NonNull UserRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void login(String userName, String password) {
        if(userName==null||userName.trim().equals("")){
            mView.toast("用户为空",Toast.LENGTH_SHORT);
            return;
        }
        if(!UText.checkEditText(password.trim(),6)){
            mView.toast("密码不能小于6位数", Toast.LENGTH_SHORT);
            return;
        }
        mView.loading();
        mDataSource.login(userName.trim(), MD5.getStringMD5(password.trim()), new BaseDataSource.BaseCallback<UserWrapper>() {
            @Override
            public void onSuccess(UserWrapper userWrapper) {
                mView.loadDialogDismiss();
                if(userWrapper.getState()== 0){
                    mView.toHome();
                }else if(userWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,userWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.loadDialogDismiss();
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });
    }
}
