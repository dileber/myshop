package com.drcosu.myshop.shopvp.orderlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drcosu.myshop.R;
import com.drcosu.myshop.data.model.MonoModel;
import com.drcosu.myshop.data.model.OrderModel;
import com.drcosu.myshop.shopvp.mono.EditMonoActivity;
import com.drcosu.myshop.shopvp.order.OrderActivity;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.view.recycle.DileberAdapter;
import com.drcosu.ndileber.view.recycle.DileberHolder;
import com.drcosu.ndileber.view.recycle.IDileberData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 2017/6/7.
 */

public class OrderListAdapter extends DileberAdapter<OrderListAdapter.OrderHolder> implements IDileberData<OrderModel> {

    List<OrderModel> mValue = new ArrayList<>();


    @Override
    public void addData(List<OrderModel> data) {
        mValue.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addData(OrderModel data) {
        mValue.add(data);
        notifyDataSetChanged();
    }

    @Override
    public void clean() {
        mValue.clear();
    }

    @Override
    protected void addViewHolderData(OrderHolder holder, int position) {
        holder.model = mValue.get(position);
    }

    @Override
    public DileberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new OrderHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValue.size();
    }

    class OrderHolder extends DileberHolder<OrderModel>{
        final TextView type,time,money,title;

        public OrderHolder(View itemView) {
            super(itemView);
            type = findView(R.id.type);
            time = findView(R.id.time);
            money = findView(R.id.money);
            title = findView(R.id.title);
        }

        @Override
        public void load() {

            switch (model.getOrderstate()){
                case 0:
                    type.setText("已下单");
                    break;
                case 1:
                    type.setText("代付款");
                    break;
                case 2:
                    type.setText("已付款");
                    break;
            }
            time.setText("下单时间："+UTime.getDateStr(UTime.Pattern.y_m_d_h_m_s,model.getOrdercreatetime()));
            money.setText("人民币"+model.getOrdermoney());
            title.setText(model.getOrdertitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderActivity.start(itemView.getContext(),model);
                }
            });
        }
    }
}