package com.drcosu.myshop.data.wrapper;

import com.drcosu.myshop.data.model.MonoModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonosWrapper extends SWrapper{
    List<MonoModel> data;

    public List<MonoModel> getData() {
        return data;
    }

    public void setData(List<MonoModel> data) {
        this.data = data;
    }

}
