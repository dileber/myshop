package com.drcosu.myshop.data.service;

import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by shidawei on 2017/6/5.
 */

public interface MonoService {

    String ADDMONO = "/api/mono/v1/add_mono";

    String EDITMONO = "/api/mono/v1/editmono";

    String ADDMONOIMAGE = "/api/mono/v1/addmonoimage";

    String GETMONO = "/api/mono/v1/getMono";

    String DELETEMONO = "/api/mono/v1/delete_mono";

    @Multipart
    @POST(ADDMONOIMAGE)
    Call<SWrapper> addmonoimage(@Part("file\"; filename=\"image.png\"") RequestBody file);

    @POST(ADDMONO)
    Call<SWrapper> addMono(@Query("monoName") String monoName, @Query("monoDetail") String monoDetail,
                              @Query("monoMoney") Double monoMoney, @Query("monoPic") String monoPic);

    @POST(EDITMONO)
    Call<SWrapper> editmono(@Query("id") Integer id,@Query("monoName") String monoName,
                            @Query("monoDetail") String monoDetail, @Query("monoMoney") Double monoMoney,
                            @Query("monoPic") String monoPic);

    @POST(DELETEMONO)
    Call<SWrapper> deletemono(@Query("id") Integer id);

    @POST(GETMONO)
    Call<MonosWrapper> getmono();
}
