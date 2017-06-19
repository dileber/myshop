package com.drcosu.myshop.shopvp.mono;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.model.MonoModel;
import com.drcosu.myshop.data.source.MonoRepository;
import com.drcosu.myshop.shopvp.user.UserFragment;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.UToolBar;

public class EditMonoActivity extends BaseShopActivity {


    public enum Type{
        edit,add
    }

    public static final String TYPE = "type";
    public static final String MONO = "mono";
    public static void start(Context context, MonoModel mono, Type type){
        Intent intent = new Intent();
        intent.setClass(context,EditMonoActivity.class);
        intent.putExtra(TYPE,type);
        intent.putExtra(MONO,mono);
        context.startActivity(intent);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_edit_mono;
    }

    Type type;
    MonoModel mono;
    EditMonoFragment editMonoFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        type = (Type) getIntent().getSerializableExtra(TYPE);
        mono = (MonoModel) getIntent().getSerializableExtra(MONO);
        UToolBar uToolBar = new UToolBar();
        if(type == Type.edit){
            uToolBar.setTitleString("编辑");
        }else if(type == Type.add){
            uToolBar.setTitleString("添加");
        }
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar,uToolBar);
        editMonoFragment = ActivityUtils.getFragment(getSupportFragmentManager(),R.id.content,EditMonoFragment.newInstance(mono,type));
        new EditMonoPresenter(editMonoFragment, MonoRepository.getInstance());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        editMonoFragment.onActivityResult(requestCode, resultCode, data);
    }
}
