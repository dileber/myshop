package com.drcosu.myshop.shopvp.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drcosu.myshop.R;
import com.drcosu.myshop.data.model.MonoModel;
import com.drcosu.myshop.data.model.ShopModel;
import com.drcosu.myshop.shopvp.mono.EditMonoActivity;
import com.drcosu.ndileber.view.recycle.DileberAdapter;
import com.drcosu.ndileber.view.recycle.DileberHolder;
import com.drcosu.ndileber.view.recycle.IDileberData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 2017/6/5.
 */

public class ShopAdapter extends DileberAdapter<ShopAdapter.ShopHolder> implements IDileberData<ShopModel>{

    List<ShopModel> mValue = new ArrayList<>();

    public ShopAdapter(){

    }

//    ShopContract.Presenter shopPresenter = null;
//    public ShopAdapter(ShopContract.Presenter shopPresenter){
//        this.shopPresenter = shopPresenter;
//    }

    @Override
    protected void addViewHolderData(ShopHolder holder, int position) {
        holder.model = mValue.get(position);
    }

    @Override
    public DileberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValue.size();
    }

    @Override
    public void addData(List<ShopModel> data) {
        mValue.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addData(ShopModel data) {
        mValue.add(data);
        notifyDataSetChanged();
    }

    @Override
    public void clean() {
        mValue.clear();
    }

    class ShopHolder extends DileberHolder<ShopModel>{

        final TextView mono_title;
        final TextView mono_detail;
        final TextView money;
        final TextView num;
        final SimpleDraweeView mono_image;

        public ShopHolder(View itemView) {
            super(itemView);
            mono_title = findView(R.id.mono_title);
            mono_detail = findView(R.id.mono_detail);
            mono_image = findView(R.id.mono_image);
            money = findView(R.id.money);
            num = findView(R.id.num);

        }

        @Override
        public void load() {
            mono_title.setText(model.getMono().getMononame());
            mono_detail.setText(model.getMono().getMonodetail());
            mono_image.setImageURI(model.getMono().getMonopic());
            money.setText(String.valueOf(model.getMono().getMonomoney().doubleValue()));
            num.setText(String.valueOf(model.getShopcount()));
            final ShopContract.Presenter shopPresenter = (ShopContract.Presenter) mPresenter;

            if(shopPresenter!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shopPresenter.updataShopCount(model.getShopuserid(),model.getShopid());
                    }
                });
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        shopPresenter.deleteOneShop(model.getShopuserid(),model.getShopid());
                        return true;
                    }
                });
            }
        }
    }
}
