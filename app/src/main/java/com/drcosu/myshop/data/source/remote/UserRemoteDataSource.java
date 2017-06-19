package com.drcosu.myshop.data.source.remote;

import android.support.annotation.NonNull;

import com.drcosu.myshop.app.SHOPConfig;
import com.drcosu.myshop.data.source.UserDataSource;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.myshop.data.service.*;
import com.drcosu.ndileber.tools.net.RetCallback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 16/8/6.
 */
public class UserRemoteDataSource extends BaseRemoteDataSource implements UserDataSource {

    private static volatile UserRemoteDataSource instance;

    HRetrofit hRetrofit;
    UserService userService;

    private UserRemoteDataSource(){

        hRetrofit = HRetrofit.getInstence(SHOPConfig.URL);
        userService = hRetrofit.retrofit.create(UserService.class);
    }


    public static UserRemoteDataSource getInstance(){
        if (instance==null){
            synchronized (UserRemoteDataSource.class){
                if(instance==null){
                    instance = new UserRemoteDataSource();
                }
            }
        }
        return instance;
    }


    @Override
    public void login(@NonNull String userName, @NonNull String userPass, final BaseCallback<UserWrapper> callback) {
        Call<UserWrapper> call = userService.login(userName,userPass);

        call.enqueue(new RetCallback<UserWrapper>() {
            @Override
            protected void onSuccess(Call<UserWrapper> call, Response<UserWrapper> response) {
                UserWrapper userWrapper = response.body();
                if(userWrapper!=null){
                    setCookie(response);
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UserWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getUser(final BaseCallback<UsersWrapper> callback) {
        Call<UsersWrapper> call = userService.getUser();

        call.enqueue(new RetCallback<UsersWrapper>() {
            @Override
            protected void onSuccess(Call<UsersWrapper> call, Response<UsersWrapper> response) {
                UsersWrapper usersWrapper = response.body();
                if(usersWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UsersWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getUserByOrder(final BaseCallback<UsersWrapper> callback) {
        Call<UsersWrapper> call = userService.getUserByOrder();

        call.enqueue(new RetCallback<UsersWrapper>() {
            @Override
            protected void onSuccess(Call<UsersWrapper> call, Response<UsersWrapper> response) {
                UsersWrapper usersWrapper = response.body();
                if(usersWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<UsersWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }
}
