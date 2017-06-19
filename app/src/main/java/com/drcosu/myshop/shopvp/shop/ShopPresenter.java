package com.drcosu.myshop.shopvp.shop;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.drcosu.myshop.data.source.OrderRepository;
import com.drcosu.myshop.data.source.ShopRepository;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;

/**
 * Created by shidawei on 2017/6/7.
 */

public class ShopPresenter extends DileberPresenter<ShopContract.View,ShopRepository> implements ShopContract.Presenter {

    public ShopPresenter(@NonNull ShopContract.View view, @NonNull ShopRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getShop(Integer userId) {
        mDataSource.getShop(userId, new BaseDataSource.BaseCallback<ShopsWrapper>() {
            @Override
            public void onSuccess(ShopsWrapper shopsWrapper) {
                if(shopsWrapper.getState()==0){
                    mView.showShop(shopsWrapper);
                }else if(shopsWrapper.getState()==-1){
                    mView.showAlert(UDialog.DIALOG_ERROR,shopsWrapper.getMsg());
                }
            }

            @Override
            public void onFailure(DataSourceException e) {
                mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());
            }
        });
    }

    @Override
    public void addOrder(Integer userId, Double addMoney, Double huilv, String detail) {
        OrderRepository.getInstance().addOrder(userId, addMoney, huilv, detail, new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                if(sWrapper.getState()==0){
                    mView.showResult(sWrapper);
                }else{
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
    public void getHuilv() {
        OrderRepository.getInstance().gethuilv(new BaseDataSource.BaseCallback<SWrapper>() {
            @Override
            public void onSuccess(SWrapper sWrapper) {
                if(sWrapper.getState()==0){
                    mView.showHuilv(sWrapper.getMsg());
                }else{
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
    public void deleteOneShop(final Integer userId, final Integer shopId) {
        mView.dialogOk("确定要删除当前商品么", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                dialog.dismiss();
                mDataSource.deleteOneShop(shopId, new BaseDataSource.BaseCallback<SWrapper>() {
                    @Override
                    public void onSuccess(SWrapper sWrapper) {
                        if(sWrapper.getState()==0){
                            getShop(userId);
                        }else{
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

    @Override
    public void updataShopCount(final Integer userId, final Integer shopId) {


        mView.showMydialog(new ShopFragment.DialogMy() {
            @Override
            public void mz(DialogInterface dialog, TextView count) {
                String c = count.getText().toString();
                if(c==null||c.equals("")){
                    mView.toast("数字能为空", Toast.LENGTH_SHORT);
                    return;
                }
                Integer num = Integer.parseInt(c);
                if(num<=0){
                    mView.toast("数字必须大于0", Toast.LENGTH_SHORT);
                    return;
                }
                mDataSource.updateShopCount(shopId, num, new BaseDataSource.BaseCallback<SWrapper>() {
                    @Override
                    public void onSuccess(SWrapper sWrapper) {
                        if(sWrapper.getState()==0){
                            getShop(userId);
                        }else{
                            mView.showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(DataSourceException e) {
                        mView.showAlert(UDialog.DIALOG_ERROR,e.getMessage());

                    }
                });
            }
        });

    }
}
