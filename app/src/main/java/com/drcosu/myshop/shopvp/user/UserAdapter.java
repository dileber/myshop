package com.drcosu.myshop.shopvp.user;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drcosu.myshop.R;
import com.drcosu.myshop.data.model.UserModel;
import com.drcosu.ndileber.mvp.data.model.SelectModel;
import com.drcosu.ndileber.tools.selectdialog.BaseNoticeWindow;
import com.drcosu.ndileber.tools.selectdialog.SelectDialog;
import com.drcosu.ndileber.view.recycle.DileberAdapter;
import com.drcosu.ndileber.view.recycle.DileberHolder;
import com.drcosu.ndileber.view.recycle.IDileberData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 2017/6/5.
 */

public class UserAdapter extends DileberAdapter<UserAdapter.UserHolder> implements IDileberData<UserModel>{

    List<UserModel> userModels = new ArrayList<>();

//    UserContract.Presenter mPresenter;
//
    public UserAdapter(UserContract.Presenter mPresenter){
        super(mPresenter);
    }

    @Override
    protected void addViewHolderData(UserHolder holder, int position) {
        holder.model = userModels.get(position);
    }

    @Override
    public DileberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    @Override
    public void addData(List<UserModel> data) {
        userModels.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addData(UserModel data) {
        userModels.add(data);
        notifyDataSetChanged();
    }

    @Override
    public void clean() {
        userModels.clear();
    }

    class UserHolder extends DileberHolder<UserModel>{

        final TextView user_name;
        final TextView user_phone;

        public UserHolder(View itemView) {
            super(itemView);
            user_name = findView(R.id.user_name);
            user_phone = findView(R.id.user_phone);
        }

        @Override
        public void load() {
            user_name.setText(model.getUsername());
            user_phone.setText(model.getUserphone());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((UserContract.Presenter)mPresenter).jump(model);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((UserContract.Presenter)mPresenter).cleanShop(model.getUserid());
                    return true;
                }
            });
        }
    }
}
