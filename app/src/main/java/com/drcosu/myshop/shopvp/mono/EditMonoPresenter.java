package com.drcosu.myshop.shopvp.mono;

import android.support.annotation.NonNull;

import com.drcosu.myshop.data.source.MonoRepository;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.UDialog;

import java.io.File;

/**
 * Created by shidawei on 2017/6/6.
 */

public class EditMonoPresenter extends DileberPresenter<EditMonoContract.View,MonoRepository> implements EditMonoContract.Presenter{

    public EditMonoPresenter(@NonNull EditMonoContract.View view, @NonNull MonoRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void editmono(Integer id, String monoName, String monoDetail, Double monoMoney, String monoPic) {
        mView.loading();
        mDataSource.editmono(id, monoName, monoDetail, monoMoney, monoPic, new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                mView.loadDialogDismiss();

                if(sWrapper.getState()==0){
                    mView.showResult(sWrapper);
                }else if(sWrapper.getState() == -1){
                    mView.showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
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
    public void addmono(String monoName, String monoDetail, Double monoMoney, String monoPic) {
        mView.loading();

        mDataSource.addMono( monoName, monoDetail, monoMoney, monoPic, new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                mView.loadDialogDismiss();
                if(sWrapper.getState()==0){
                    mView.showResult(sWrapper);
                }else if(sWrapper.getState() == -1){
                    mView.showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
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
