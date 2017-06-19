package com.drcosu.myshop.app;

import com.drcosu.ndileber.app.BaseConfiger;
import com.drcosu.ndileber.tools.HPref;

/**
 * Created by shidawei on 2017/4/20.
 */

public class SHOPPrefer {

    public final static String SAREPREFER_SHOP = "sareprefer_shop";

    public final static String USERNAME = "username";
    public final static String USERPASS = "userpass";

    public static void push(String key,Object value){
        HPref.getInstance().put(SAREPREFER_SHOP,key,value);
    }

    public static <T>T get(String key, T defaultValue,Class<T> returnType){
        return HPref.getInstance().get(SAREPREFER_SHOP,key,defaultValue,returnType);
    }
}
