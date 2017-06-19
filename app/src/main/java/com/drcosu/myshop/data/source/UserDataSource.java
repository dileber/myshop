package com.drcosu.myshop.data.source;

import android.support.annotation.NonNull;

import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 16/8/6.
 */
public interface UserDataSource extends BaseDataSource{

    void login(@NonNull String userName,@NonNull String userPass, BaseCallback<UserWrapper> callback);

    void getUser(BaseCallback<UsersWrapper> callback);

    void getUserByOrder(BaseCallback<UsersWrapper> callback);
}
