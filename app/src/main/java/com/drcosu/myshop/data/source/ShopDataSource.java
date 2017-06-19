package com.drcosu.myshop.data.source;

import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by shidawei on 2017/6/7.
 */

public interface ShopDataSource extends BaseDataSource{

    void getShop(Integer userId, BaseCallback<ShopsWrapper> callback);

    void cleanShop(Integer userId,BaseCallback<SWrapper> callback);

    void updateShopCount( Integer shopId,Integer count,BaseCallback<SWrapper> callback);

    void deleteOneShop( Integer shopId,BaseCallback<SWrapper> callback);

}
