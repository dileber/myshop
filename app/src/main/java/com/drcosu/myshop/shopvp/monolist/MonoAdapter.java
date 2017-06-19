package com.drcosu.myshop.shopvp.monolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drcosu.myshop.R;
import com.drcosu.myshop.data.model.MonoModel;
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

public class MonoAdapter extends DileberAdapter<MonoAdapter.MonoHolder> implements IDileberData<MonoModel>{

    List<MonoModel> mValue = new ArrayList<>();

    public MonoAdapter(MonoListContract.Presenter mPresenter){
        super(mPresenter);
    }

//    MonoListContract.Presenter mPresenter;
//    public MonoAdapter(MonoListContract.Presenter mPresenter){
//        this.mPresenter = mPresenter;
//    }

    @Override
    protected void addViewHolderData(MonoHolder holder, int position) {
        holder.model = mValue.get(position);
    }

    @Override
    public DileberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mono, parent, false);
        return new MonoHolder(view);
    }

    @Override
    public int getItemCount() {
        return mValue.size();
    }

    @Override
    public void addData(List<MonoModel> data) {
        mValue.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addData(MonoModel data) {
        mValue.add(data);
        notifyDataSetChanged();
    }

    @Override
    public void clean() {
        mValue.clear();
    }

    class MonoHolder extends DileberHolder<MonoModel>{

        final TextView mono_title;
        final TextView mono_detail;
        final TextView money;
        final SimpleDraweeView mono_image;

        public MonoHolder(View itemView) {
            super(itemView);
            mono_title = findView(R.id.mono_title);
            mono_detail = findView(R.id.mono_detail);
            mono_image = findView(R.id.mono_image);
            money = findView(R.id.money);

        }

        @Override
        public void load() {
            mono_title.setText(model.getMononame());
            mono_detail.setText(model.getMonodetail());
            mono_image.setImageURI(model.getMonopic());
            money.setText(String.valueOf(model.getMonomoney().doubleValue()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditMonoActivity.start(itemView.getContext(),model, EditMonoActivity.Type.edit);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((MonoListContract.Presenter)mPresenter).clernMono(model.getMonoid());
                    return true;
                }
            });
        }
    }
}
