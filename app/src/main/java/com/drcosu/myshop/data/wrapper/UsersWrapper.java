package com.drcosu.myshop.data.wrapper;

import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 2017/6/5.
 */

public class UsersWrapper extends SWrapper{

    List<UserModel> data;

    public List<UserModel> getData() {
        return data;
    }

    public void setData(List<UserModel> data) {
        this.data = data;
    }
}
