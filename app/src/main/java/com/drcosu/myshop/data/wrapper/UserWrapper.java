package com.drcosu.myshop.data.wrapper;

import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 2017/6/5.
 */

public class UserWrapper extends SWrapper{

    UserModel data;

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

}
