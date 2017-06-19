package com.drcosu.myshop.shopvp.login;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by shidawei on 16/8/6.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void toHome();
    }
    interface Presenter extends BasePresenter{
        void login(String userName,String password);
    }
}
