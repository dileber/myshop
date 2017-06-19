package com.drcosu.myshop.shopvp.user;

import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.contract.BaseContract;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by shidawei on 2017/6/5.
 */

public class UserContract {

    interface View extends BaseView<Presenter>{
        void showUser(UsersWrapper usersWrapper);
        void jump(UserModel user);
        void refresh();
    }

    interface Presenter extends BasePresenter{
        void getUser();
        void getUserByOrder();
        void jump(UserModel user);
        void cleanShop(Integer userId);
    }

}
