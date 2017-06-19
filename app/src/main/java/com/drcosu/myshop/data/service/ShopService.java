package com.drcosu.myshop.data.service;

import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.myshop.data.wrapper.UserWrapper;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by shidawei on 2017/6/6.
 */

public interface ShopService {

    String GETSHOP = "api/shop/v1/getshopbyuser";
    String CLEANSHOP = "/api/shop/v1/cleanshopbyuser";
    String UPDATECOUNT = "/api/shop/v1/updateShopCount";
    String DELETEONESHOP = "/api/shop/v1/deleteOneShop";

    @POST(GETSHOP)
    Call<ShopsWrapper> getShop(@Query("userId") Integer userId);

    @POST(CLEANSHOP)
    Call<SWrapper> cleanShop(@Query("userId") Integer userId);

    @POST(UPDATECOUNT)
    Call<SWrapper> updateShopCount(@Query("shopId") Integer shopId, @Query("count")Integer count);

    @POST(DELETEONESHOP)
    Call<SWrapper> deleteOneShop(@Query("shopId") Integer shopId);


}
