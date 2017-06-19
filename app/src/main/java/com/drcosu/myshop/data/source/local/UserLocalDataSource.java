package com.drcosu.myshop.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.drcosu.myshop.data.source.UserDataSource;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.local.DBManager;
import com.drcosu.ndileber.tools.HJson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/8/6.
 */
public class UserLocalDataSource extends BaseLocalDataSource implements UserDataSource {


    private static volatile UserLocalDataSource instance;

    private UserLocalDataSource(){

    }

    public static UserLocalDataSource getInstance(){
        if (instance==null){
            synchronized (UserLocalDataSource.class){
                if(instance==null){
                    instance = new UserLocalDataSource();
                }
            }
        }
        return instance;
    }


    @Override
    public void login(@NonNull String userName, @NonNull String userPass, BaseCallback<UserWrapper> callback) {

    }

    @Override
    public void getUser(BaseCallback<UsersWrapper> callback) {

    }

    @Override
    public void getUserByOrder(BaseCallback<UsersWrapper> callback) {

    }
}
