package com.drcosu.myshop.data.source.local;

import com.drcosu.myshop.data.source.MonoDataSource;
import com.drcosu.myshop.data.source.UserDataSource;
import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;

import java.io.File;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonoLocalDataSource extends BaseLocalDataSource implements MonoDataSource {


    private static volatile MonoLocalDataSource instance;

    private MonoLocalDataSource() {

    }

    public static MonoLocalDataSource getInstance() {
        if (instance == null) {
            synchronized (MonoLocalDataSource.class) {
                if (instance == null) {
                    instance = new MonoLocalDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void addmonoimage(File file, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void addMono(String monoName, String monoDetail, Double monoMoney, String monoPic, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void editmono(Integer id, String monoName, String monoDetail, Double monoMoney, String monoPic, BaseCallback<SWrapper> callback) {

    }

    @Override
    public void getmono(BaseCallback<MonosWrapper> callback) {

    }

    @Override
    public void deletemono(Integer id, BaseCallback<SWrapper> callback) {

    }
}