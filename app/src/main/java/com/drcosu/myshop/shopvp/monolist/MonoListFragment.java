package com.drcosu.myshop.shopvp.monolist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopFragment;
import com.drcosu.myshop.data.wrapper.MonosWrapper;
import com.drcosu.myshop.shopvp.mono.EditMonoActivity;

public class MonoListFragment extends BaseShopFragment implements MonoListContract.View{

    public MonoListFragment() {
    }

    public static MonoListFragment newInstance() {
        MonoListFragment fragment = new MonoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean retain() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_mono_list;
    }


    RecyclerView mono_list;
    MonoAdapter monoAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mono_list = findView(R.id.mono_list);
        mono_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        monoAdapter = new MonoAdapter(mPresenter);
        mono_list.setAdapter(monoAdapter);
        setActivityTitle(this,"商品列表");
        setActivityRightButton(this, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditMonoActivity.start(getActivity(),null, EditMonoActivity.Type.add);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMonos();
    }

    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }

    MonoListContract.Presenter mPresenter;

    @Override
    public void setPresenter(MonoListContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }


    @Override
    public void showMonos(MonosWrapper monosWrapper) {
        monoAdapter.clean();
        if(monosWrapper.getData()!=null){
            monoAdapter.addData(monosWrapper.getData());
        }
    }
}
