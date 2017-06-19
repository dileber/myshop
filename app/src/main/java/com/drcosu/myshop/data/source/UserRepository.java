package com.drcosu.myshop.data.source;

import android.support.annotation.NonNull;

import com.drcosu.myshop.data.source.local.UserLocalDataSource;
import com.drcosu.myshop.data.source.remote.UserRemoteDataSource;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.tools.HJson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/8/7.
 */
public class UserRepository extends BaseRepository<UserLocalDataSource,UserRemoteDataSource> implements UserDataSource{

    public static volatile UserRepository instance;
//
//    private final UserDataSource userLocalDataSource;
//
//    private final UserDataSource userRemoteDataSource;

    protected UserRepository(UserLocalDataSource localDataSource, UserRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

//    private UserRepository(UserDataSource userRemoteDataSource, UserDataSource userLocalDataSource) {
//        this.userRemoteDataSource = userRemoteDataSource;
//        this.userLocalDataSource = userLocalDataSource;
//    }

    public static UserRepository getInstance(){
        if(instance==null){
            synchronized (UserRepository.class){
                if(instance==null){
                    instance = new UserRepository(UserLocalDataSource.getInstance(),UserRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void login(@NonNull String userName, @NonNull String userPass, BaseCallback<UserWrapper> callback) {
        remoteDataSource.login(userName,userPass,callback);
    }

    @Override
    public void getUser(BaseCallback<UsersWrapper> callback) {
        remoteDataSource.getUser(callback);
    }

    @Override
    public void getUserByOrder(BaseCallback<UsersWrapper> callback) {
        remoteDataSource.getUserByOrder(callback);
    }
}
