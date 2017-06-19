package com.drcosu.myshop.shopvp.user;

import android.app.Dialog;
import android.support.annotation.NonNull;

import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.source.ShopRepository;
import com.drcosu.myshop.data.source.UserRepository;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;

/**
 * Created by shidawei on 2017/6/5.
 */

public class UserPresenter extends DileberPresenter<UserContract.View,UserRepository> implements UserContract.Presenter{

    public UserPresenter(@NonNull UserContract.View view, @NonNull UserRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getUser() {
        mView.loading();
        mDataSource.getUser(new BaseDataSource.BaseCallback<UsersWrapper>() {
            @Override
            public void onSuccess(UsersWrapper usersWrapper) {
                mView.loadDialogDismiss();
                if(usersWrapper.getState()== 0){
                    mView.showUser(usersWrapper);
                }else if(usersWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,usersWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.loadDialogDismiss();
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });
    }

    @Override
    public void getUserByOrder() {
        mView.loading();
        mDataSource.getUserByOrder(new BaseDataSource.BaseCallback<UsersWrapper>() {
            @Override
            public void onSuccess(UsersWrapper usersWrapper) {
                mView.loadDialogDismiss();
                if(usersWrapper.getState()== 0){
                    mView.showUser(usersWrapper);
                }else if(usersWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,usersWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.loadDialogDismiss();
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });
    }

    @Override
    public void jump(UserModel user) {
        mView.jump(user);
    }

    @Override
    public void cleanShop(final Integer userId) {
        mView.dialogOk("确定要删除当前用户的购物车么", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                dialog.dismiss();
                ShopRepository.getInstance().cleanShop(userId, new BaseDataSource.BaseCallback<SWrapper>() {
                    @Override
                    public void onSuccess(SWrapper sWrapper) {
                        if(sWrapper.getState()==0){
                            mView.refresh();
                        }else if(sWrapper.getState()==-1){
                            mView.showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
                    }
                });

            }

            @Override
            public void cancel(Dialog dialog) {
                dialog.dismiss();
            }
        });
    }
}
