package com.drcosu.myshop.shopvp.shop;

import android.content.DialogInterface;

import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by shidawei on 2017/6/7.
 */

public class ShopContract {

    interface Presenter extends BasePresenter{
        void getShop(Integer userId);
        void addOrder(Integer userId,Double addMoney,Double huilv,String detail);
        void getHuilv();
        void deleteOneShop(Integer userId,Integer shopId);
        void updataShopCount(Integer userId,Integer shopId);
    }

    interface View extends BaseView<Presenter> {
        void showShop(ShopsWrapper shopsWrapper);
        void showHuilv(String huilv);
        void showResult(SWrapper sWrapper);
        void showMydialog(final ShopFragment.DialogMy onClickListener);
    }


}
