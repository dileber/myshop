package com.drcosu.myshop.shopvp.monolist;

import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonoListContract {

    interface Presenter extends BasePresenter{
        void getMonos();
        void clernMono(Integer id);

    }

    interface View extends BaseView<Presenter>{
        void showMonos(MonosWrapper monosWrapper);
    }



}
