package com.drcosu.myshop.shopvp.mono;

import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

import java.io.File;

/**
 * Created by shidawei on 2017/6/6.
 */

public class EditMonoContract {
    interface Presenter extends BasePresenter{
        void editmono(Integer id, String monoName, String monoDetail, Double monoMoney, String monoPic);
        void addmono(String monoName, String monoDetail, Double monoMoney, String monoPic);
    }

    interface View extends BaseView<Presenter>{
        void showResult(SWrapper sWrapper);
    }

}
