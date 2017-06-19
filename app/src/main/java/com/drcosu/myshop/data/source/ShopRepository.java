package com.drcosu.myshop.data.source;

import com.drcosu.myshop.data.source.local.ShopLocalDataSource;
import com.drcosu.myshop.data.source.remote.ShopRemoteDataSource;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

/**
 * Created by shidawei on 2017/6/7.
 */

public class ShopRepository extends BaseRepository<ShopLocalDataSource,ShopRemoteDataSource> implements ShopDataSource {

    public static volatile ShopRepository instance;


    public static ShopRepository getInstance(){
        if(instance==null){
            synchronized (ShopRepository.class){
                if(instance==null){
                    instance = new ShopRepository(ShopLocalDataSource.getInstance(),ShopRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    protected ShopRepository(ShopLocalDataSource localDataSource, ShopRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

    @Override
    public void getShop(Integer userId, BaseCallback<ShopsWrapper> callback) {
        remoteDataSource.getShop(userId, callback);
    }

    @Override
    public void cleanShop(Integer userId, BaseCallback<SWrapper> callback) {
        remoteDataSource.cleanShop(userId, callback);
    }

    @Override
    public void updateShopCount(Integer shopId, Integer count, BaseCallback<SWrapper> callback) {
        remoteDataSource.updateShopCount(shopId, count, callback);
    }

    @Override
    public void deleteOneShop(Integer shopId, BaseCallback<SWrapper> callback) {
        remoteDataSource.deleteOneShop(shopId, callback);
    }
}
