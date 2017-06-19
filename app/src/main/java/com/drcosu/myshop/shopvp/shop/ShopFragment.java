package com.drcosu.myshop.shopvp.shop;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.myshop.data.wrapper.ShopsWrapper;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.storage.UStorage;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.List;

public class ShopFragment extends BaseShopFragment implements ShopContract.View {

    private static final String USER = "user";

    private UserModel user;

    public static ShopFragment newInstance(UserModel user) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER, user);
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
            user = (UserModel) getArguments().getSerializable(USER);
        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_shop;
    }

    RecyclerView shopcart;
    TextView name,phone,huilv,detail,heji,button;
    EditText jiajia;
    ShopAdapter shopAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        shopcart = findView(R.id.shopcart);
        name = findView(R.id.name);
        phone = findView(R.id.phone);
        huilv = findView(R.id.huilv);
        detail = findView(R.id.detail);
        jiajia = findView(R.id.jiajia);
        heji = findView(R.id.heji);
        button = findView(R.id.button);

        shopAdapter = new ShopAdapter(mPresenter);
        shopcart.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopcart.setAdapter(shopAdapter);

        name.setText(user.getUsername());
        phone.setText(user.getUserphone());
        mPresenter.getHuilv();
        mPresenter.getShop(user.getUserid());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addMoney = jiajia.getText().toString();
                if(addMoney==null||addMoney.equals("")){
                    toast("填写加价", Toast.LENGTH_SHORT);
                    return;
                }
                if(huilvValue==null){
                    toast("汇率获取出现问题", Toast.LENGTH_SHORT);
                    return;
                }

                mPresenter.addOrder(user.getUserid(),Double.parseDouble(addMoney),huilvValue.doubleValue(),detail.getText().toString());
            }
        });

        jiajia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null&&!s.equals("")){
                    addM =  new BigDecimal(s.toString());
                    heji();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    BigDecimal addM = null;

    @Override
    protected void show() {

    }

    @Override
    protected void hidden() {

    }

    ShopContract.Presenter mPresenter;

    @Override
    public void setPresenter(ShopContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }
    BigDecimal shopmoney = null;
    @Override
    public void showShop(ShopsWrapper shopsWrapper) {
        shopAdapter.clean();
        if(shopsWrapper.getData()!=null){
            shopAdapter.addData(shopsWrapper.getData());

            List<ShopModel> shopModels = shopsWrapper.getData();
            BigDecimal money = new BigDecimal(0);
            StringBuffer title = new StringBuffer("");
            for(int i=0;i<shopModels.size();i++){
                int count = shopModels.get(i).getShopcount();
                BigDecimal bigCount = new BigDecimal(count);
                BigDecimal onemoney = shopModels.get(i).getMono().getMonomoney();
                String t = shopModels.get(i).getMono().getMononame();
                title.append(t+",");
                money = money.add(onemoney.multiply(bigCount));
            }
            shopmoney = money;
            heji();
        }
    }

    private void heji(){
        BigDecimal money = new BigDecimal(0);
        if(shopmoney!=null) {
            money = money.add(shopmoney);
        }
        if(addM!=null){
            money = money.add(addM);
        }
        if(huilvValue!=null){
            money = money.multiply(huilvValue);
        }

        heji.setText("合计人民币：" + money.doubleValue());
    }

    BigDecimal huilvValue = null;
    @Override
    public void showHuilv(String huilv) {
        this.huilv.setText(huilv);
        this.huilvValue = new BigDecimal(huilv);
        heji();
    }

    @Override
    public void showResult(SWrapper sWrapper) {
        showAlert(UDialog.DIALOG_SUCCESS,sWrapper.getMsg());
        getActivity().finish();
    }

    interface DialogMy{
        void mz(DialogInterface dialog,TextView count);
    }

    @Override
    public void showMydialog(final DialogMy onClickListener){

        final View customView =View.inflate(getActivity(),R.layout.countdialog,null);
        new AlertDialog.Builder(getActivity()).setTitle("填写数量").setView(customView)
     .setPositiveButton("确定", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             onClickListener.mz(dialog,(TextView) customView.findViewById(R.id.num));
         }
     })
     .setNegativeButton("取消", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.dismiss();
         }
     }).show();
    }


}
