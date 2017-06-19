package com.drcosu.myshop.data.source;

import com.drcosu.myshop.data.source.local.MonoLocalDataSource;
import com.drcosu.myshop.data.source.remote.MonoRemoteDataSource;
import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.io.File;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonoRepository extends BaseRepository<MonoLocalDataSource,MonoRemoteDataSource> implements MonoDataSource{

    public static volatile MonoRepository instance;

    public MonoRepository(MonoLocalDataSource localDataSource, MonoRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }


    public static MonoRepository getInstance(){
        if(instance==null){
            synchronized (MonoRepository.class){
                if(instance==null){
                    instance = new MonoRepository(MonoLocalDataSource.getInstance(),MonoRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public void addmonoimage(File file, BaseCallback<SWrapper> callback) {
        remoteDataSource.addmonoimage(file, callback);
    }

    @Override
    public void addMono(String monoName, String monoDetail, Double monoMoney, String monoPic, BaseCallback<SWrapper> callback) {
        remoteDataSource.addMono(monoName, monoDetail, monoMoney, monoPic, callback);
    }

    @Override
    public void editmono(Integer id, String monoName, String monoDetail, Double monoMoney, String monoPic, BaseCallback<SWrapper> callback) {
        remoteDataSource.editmono(id, monoName, monoDetail, monoMoney, monoPic, callback);
    }

    @Override
    public void getmono(BaseCallback<MonosWrapper> callback) {
        remoteDataSource.getmono(callback);
    }

    @Override
    public void deletemono(Integer id, BaseCallback<SWrapper> callback) {
        remoteDataSource.deletemono(id, callback);
    }
}
