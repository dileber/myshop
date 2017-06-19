package com.drcosu.myshop.shopvp.order;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopFragment;
import com.drcosu.myshop.data.model.OrderModel;
import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.wrapper.OrderShopWrapper;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.myshop.shopvp.orderlist.OrderListContract;
import com.drcosu.myshop.shopvp.shop.ShopAdapter;
import com.drcosu.myshop.shopvp.shop.ShopContract;
import com.drcosu.myshop.shopvp.shop.ShopFragment;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;

import java.math.BigDecimal;
import java.util.List;

public class OrderFragment extends BaseShopFragment implements OrderContract.View {

    private static final String ORDER = "order";

    private OrderModel order;

    public static OrderFragment newInstance(OrderModel orderId) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ORDER, orderId);
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
            order = (OrderModel) getArguments().getSerializable(ORDER);
        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_order;
    }

    RecyclerView shopcart;
    TextView huilv,detail,heji,button,jiajia;
    ShopAdapter shopAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        shopcart = findView(R.id.shopcart);
        huilv = findView(R.id.huilv);
        detail = findView(R.id.detail);
        jiajia = findView(R.id.jiajia);
        heji = findView(R.id.heji);
        button = findView(R.id.button);
        if(order.getOrderstate()==0){
            button.setText("已下单等待购买");
        }else if(order.getOrderstate()==1){
            button.setText("等待客户支付");
        }
        heji.setText("合计人民币：" + order.getOrdermoney().doubleValue());


        shopAdapter = new ShopAdapter();
        shopcart.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopcart.setAdapter(shopAdapter);


        mPresenter.getOrder(order.getOrderid());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order.getOrderstate()==0){
                    dialogOk("确定要购买么？", new DialogLinstener() {
                        @Override
                        public void confirm(Dialog dialog) {
                            mPresenter.updateOrder(order.getOrderid(),1);
                            dialog.dismiss();

                        }

                        @Override
                        public void cancel(Dialog dialog) {
                            dialog.dismiss();
                        }
                    });
                } else if(order.getOrderstate() == 1){
                    dialogOk("客户确定支付完了么？", new DialogLinstener() {
                        @Override
                        public void confirm(Dialog dialog) {
                            mPresenter.updateOrder(order.getOrderid(),2);
                            dialog.dismiss();

                        }

                        @Override
                        public void cancel(Dialog dialog) {
                            dialog.dismiss();
                        }
                    });
                }



            }
        });

    }


    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }

    OrderContract.Presenter mPresenter;

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }


    @Override
    public void showOrder(OrderShopWrapper orderShopWrapper) {
        huilv.setText(String.valueOf(orderShopWrapper.getData().getHuilv()));
        jiajia.setText(String.valueOf(orderShopWrapper.getData().getJiajia()));
        detail.setText(orderShopWrapper.getData().getDetail());

        shopAdapter.clean();
        shopAdapter.addData(orderShopWrapper.getData().getShopModels());

    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}