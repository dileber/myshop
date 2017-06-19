package com.drcosu.myshop.shopvp.monolist;

import android.app.Dialog;
import android.support.annotation.NonNull;

import com.drcosu.myshop.data.source.MonoRepository;
import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonoListPresenter extends DileberPresenter<MonoListContract.View,MonoRepository> implements MonoListContract.Presenter {
    public MonoListPresenter(@NonNull MonoListContract.View view, @NonNull MonoRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getMonos() {
        mView.loading();
        mDataSource.getmono(new BaseDataSource.BaseCallback<MonosWrapper>() {
            @Override
            public void onSuccess(MonosWrapper monosWrapper) {
                mView.loadDialogDismiss();
                if(monosWrapper.getState()==0){
                    mView.showMonos(monosWrapper);
                }else if(monosWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,monosWrapper.getMsg());
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
    public void clernMono(final Integer id) {
        mView.dialogOk("确定要删除当前商品么", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                dialog.dismiss();
                mDataSource.deletemono(id,new BaseDataSource.BaseCallback<SWrapper>() {
                    @Override
                    public void onSuccess(SWrapper sWrapper) {
                        if(sWrapper.getState()==0){
                            getMonos();
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
