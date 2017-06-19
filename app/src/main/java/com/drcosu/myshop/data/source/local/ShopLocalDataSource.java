package com.drcosu.myshop.data.source.local;

import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.myshop.data.source.ShopDataSource;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;

/**
 * Created by shidawei on 2017/6/7.
 */

public class ShopLocalDataSource extends BaseLocalDataSource implements ShopDataSource {

    private static volatile ShopLocalDataSource instance;

    private ShopLocalDataSource() {

    }

    public static ShopLocalDataSource getInstance() {
        if (instance == null) {
            synchronized (ShopLocalDataSource.class) {
                if (instance == null) {
                    instance = new ShopLocalDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void getShop(Integer userId, BaseCallback<ShopsWrapper> callback) {

    }

    @Override
    public void cleanShop(Integer userId, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void updateShopCount(Integer shopId, Integer count, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void deleteOneShop(Integer shopId, BaseCallback<SWrapper> callback) {

    }
}
