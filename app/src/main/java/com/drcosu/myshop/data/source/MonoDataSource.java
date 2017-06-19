package com.drcosu.myshop.data.source;

import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.io.File;

/**
 * Created by shidawei on 2017/6/5.
 */

public interface MonoDataSource extends BaseDataSource {

    void addmonoimage(File file, BaseCallback<SWrapper> callback);

    void addMono(String monoName, String monoDetail,
                               Double monoMoney, String monoPic,BaseCallback<SWrapper> callback);

    void editmono(Integer id,String monoName, String monoDetail,
                  Double monoMoney, String monoPic,BaseCallback<SWrapper> callback);
    void getmono(BaseCallback<MonosWrapper> callback);

    void deletemono(Integer id,BaseCallback<SWrapper> callback);
}
