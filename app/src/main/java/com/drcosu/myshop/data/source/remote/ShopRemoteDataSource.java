package com.drcosu.myshop.data.source.remote;

import com.drcosu.myshop.app.SHOPConfig;
import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.myshop.data.service.ShopService;
import com.drcosu.myshop.data.service.UserService;
import com.drcosu.myshop.data.source.ShopDataSource;
import com.drcosu.myshop.data.source.local.ShopLocalDataSource;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.mvp.data.source.remote.BaseRemoteDataSource;
import com.drcosu.ndileber.tools.HRetrofit;
import com.drcosu.ndileber.tools.net.RetCallback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shidawei on 2017/6/7.
 */

public class ShopRemoteDataSource extends BaseRemoteDataSource implements ShopDataSource {

    private static volatile ShopRemoteDataSource instance;


    HRetrofit hRetrofit;
    ShopService shopService;

    private ShopRemoteDataSource(){

        hRetrofit = HRetrofit.getInstence(SHOPConfig.URL);
        shopService = hRetrofit.retrofit.create(ShopService.class);
    }
    public static ShopRemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (ShopRemoteDataSource.class) {
                if (instance == null) {
                    instance = new ShopRemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void getShop(Integer userId, final BaseCallback<ShopsWrapper> callback) {
        Call<ShopsWrapper> call = shopService.getShop(userId);

        call.enqueue(new RetCallback<ShopsWrapper>() {
            @Override
            protected void onSuccess(Call<ShopsWrapper> call, Response<ShopsWrapper> response) {
                ShopsWrapper shopsWrapper = response.body();
                if(shopsWrapper!=null){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new DataSourceException("数据返回为空"));
                }
            }

            @Override
            protected void failure(Call<ShopsWrapper> call, Throwable throwable) {
                callback.onFailure(new DataSourceException(throwable.getMessage()));
            }
        });
    }

    @Override
    public void cleanShop(Integer userId, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = shopService.cleanShop(userId);

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
    public void updateShopCount(Integer shopId, Integer count, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = shopService.updateShopCount(shopId,count);

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
    public void deleteOneShop(Integer shopId, final BaseCallback<SWrapper> callback) {
        Call<SWrapper> call = shopService.deleteOneShop(shopId);

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
