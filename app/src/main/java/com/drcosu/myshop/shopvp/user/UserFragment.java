package com.drcosu.myshop.shopvp.user;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopFragment;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.wrapper.UsersWrapper;
import com.drcosu.myshop.shopvp.orderlist.OrderListActivity;
import com.drcosu.myshop.shopvp.shop.ShopActivity;
import com.drcosu.ndileber.mvp.data.model.SelectModel;
import com.drcosu.ndileber.tools.selectdialog.BaseNoticeWindow;
import com.drcosu.ndileber.tools.selectdialog.SelectDialog;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends BaseShopFragment implements UserContract.View {

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean retain() {
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_user;
    }

    RecyclerView user_list;
    UserAdapter userAdapter;

    List<SelectModel> list;

    SelectDialog<SelectModel> chooseDialog;

    private boolean shop = true;

    @Override
    protected void initView(Bundle savedInstanceState) {
        user_list = findView(R.id.user_list);
        user_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        userAdapter = new UserAdapter(mPresenter);
        user_list.setAdapter(userAdapter);
        setActivityTitle(this,"下单用户");
        setActivityRightButton(this, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop = !shop;
                refresh();
            }
        });

        list = new ArrayList<SelectModel>();

        list.add(new SelectModel(1,"当前购物车"));
        list.add(new SelectModel(2,"订单"));


        chooseDialog =
                new SelectDialog.Builder<SelectModel>(getActivity())
                        .setDataList(list)
                        .setButtonColor(ContextCompat.getColor(getActivity(),R.color.dileber_text_10))
                        .setButtonSize(14)
                        .setLastButtonSize(14)
                        .setTitleText("选择")
                        .build();


    }


    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }

    UserContract.Presenter mPresenter;

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }

    @Override
    public void showUser(UsersWrapper usersWrapper) {
        userAdapter.clean();
        if(usersWrapper.getData()!=null){
            userAdapter.addData(usersWrapper.getData());
        }
    }

    @Override
    public void jump(final UserModel user) {
        chooseDialog.setButtonListener(new BaseNoticeWindow.OnButtonListener() {
            @Override
            public void onSureListener(View v, SelectModel selectModel) {
                switch (selectModel.getId()){
                    case 1:
                        ShopActivity.start(getActivity(),user);
                        break;
                    case 2:
                        OrderListActivity.start(getActivity(),user);
                        break;
                }
            }

            @Override
            public void onDiscardListener(View v) {

            }

            @Override
            public void onDismissListener(View v, int nType) {

            }
        });
        chooseDialog.show(user_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void refresh() {
        if(shop) {

            setActivityTitle(UserFragment.this,"下单用户");
            mPresenter.getUser();

        }else{

            setActivityTitle(UserFragment.this,"加入订单用户");
            mPresenter.getUserByOrder();


        }
    }
}
