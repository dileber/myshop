package com.drcosu.myshop.data.source.remote;

import com.drcosu.myshop.app.SHOPConfig;
import com.drcosu.myshop.data.service.MonoService;
import com.drcosu.myshop.data.service.UserService;
import com.drcosu.myshop.data.source.MonoDataSource;
import com.drcosu.myshop.data.source.local.MonoLocalDataSource;
import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonoRemoteDataSource extends BaseRemoteDataSource implements MonoDataSource {

    HRetrofit hRetrofit;
    MonoService monoService;
    private static volatile MonoRemoteDataSource instance;

    private MonoRemoteDataSource() {
        hRetrofit = HRetrofit.getInstence(SHOPConfig.URL);
        monoService = hRetrofit.retrofit.create(MonoService.class);
    }

    public static MonoRemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (MonoRemoteDataSource.class) {
                if (instance == null) {
                    instance = new MonoRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void addmonoimage(File file, final BaseCallback<SWrapper> callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Call<SWrapper> call = monoService.addmonoimage(requestBody);
        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper= response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void addMono(String monoName, String monoDetail, Double monoMoney, String monoPic, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = monoService.addMono( monoName, monoDetail, monoMoney, monoPic);

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper= response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void editmono(Integer id, String monoName, String monoDetail, Double monoMoney, String monoPic, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = monoService.editmono(id, monoName, monoDetail, monoMoney, monoPic);

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper= response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void getmono(final BaseCallback<MonosWrapper> callback) {
        Call<MonosWrapper> call = monoService.getmono();

        call.enqueue(new RetCallback<MonosWrapper>() {
            @Override
            protected void onSuccess(Call<MonosWrapper> call, Response<MonosWrapper> response) {
                MonosWrapper monosWrapper = response.body();
                if(monosWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<MonosWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void deletemono(Integer id, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = monoService.deletemono(id);

        call.enqueue(new RetCallback<SWrapper>() {
            @Override
            protected void onSuccess(Call<SWrapper> call, Response<SWrapper> response) {
                SWrapper sWrapper= response.body();
                if(sWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<SWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }
}