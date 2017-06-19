package com.drcosu.myshop.data.wrapper;

import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.ndileber.mvp.data.model.SWrapper;

import java.util.List;

/**
 * Created by shidawei on 2017/6/6.
 */

public class ShopsWrapper extends SWrapper {

    List<ShopModel> data;

    public List<ShopModel> getData() {
        return data;
    }

    public void setData(List<ShopModel> data) {
        this.data = data;
    }

}
