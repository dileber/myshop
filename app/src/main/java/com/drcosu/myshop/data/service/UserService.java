package com.drcosu.myshop.data.service;


import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 16/8/7.
 */
public interface UserService {
    /**
     * 登录
     */
    String LOGIN = "api/login";

    String GETUSER = "api/user/v1/getUser";
    String GETUSERBYORDER = "api/user/v1/getUserByOrder";


    @POST(LOGIN)
    Call<UserWrapper> login(@Query("userName") String userName, @Query("passWord") String passWord);

    @POST(GETUSER)
    Call<UsersWrapper> getUser();

    @POST(GETUSERBYORDER)
    Call<UsersWrapper> getUserByOrder();


}
