package com.drcosu.myshop.shopvp.mono;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopFragment;
import com.drcosu.myshop.data.model.MonoModel;
import com.drcosu.myshop.shopvp.camera.CameraActivity;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;
import com.facebook.drawee.view.SimpleDraweeView;

import java.math.BigDecimal;

public class EditMonoFragment extends BaseShopFragment implements EditMonoContract.View{

    public EditMonoFragment() {
    }

    EditMonoActivity.Type type;
    MonoModel mono;

    public static EditMonoFragment newInstance(MonoModel mono, EditMonoActivity.Type type) {
        EditMonoFragment fragment = new EditMonoFragment();
        Bundle args = new Bundle();
        args.putSerializable(EditMonoActivity.MONO, mono);
        args.putSerializable(EditMonoActivity.TYPE, type);
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
            type = (EditMonoActivity.Type) getArguments().getSerializable(EditMonoActivity.TYPE);
            mono = (MonoModel) getArguments().getSerializable(EditMonoActivity.MONO);
        }
    }

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_edit_mono;
    }

    SimpleDraweeView mono_image;
    TextView title;
    TextView money;
    TextView detail;
    TextView button;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mono_image = findView(R.id.mono_image);
        title = findView(R.id.title);
        money = findView(R.id.money);
        detail = findView(R.id.detail);
        button = findView(R.id.button);

        if(type == EditMonoActivity.Type.edit){
            title.setText(mono.getMononame());
            money.setText(String.valueOf(mono.getMonomoney().doubleValue()));
            detail.setText(mono.getMonodetail());
            mono_image.setImageURI(mono.getMonopic());
        }

        mono_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraActivity.start(getActivity(),CAMERA);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type== EditMonoActivity.Type.add){
                    if(imageUrl==null){
                        showAlert(UDialog.DIALOG_ERROR,"请添加图片");
                        return;
                    }
                    mPresenter.addmono(title.getText().toString(),detail.getText().toString(),Double.parseDouble(money.getText().toString()),imageUrl);
                }else if(type == EditMonoActivity.Type.edit){
                    mPresenter.editmono(mono.getMonoid(),title.getText().toString(),detail.getText().toString(),Double.parseDouble(money.getText().toString()),imageUrl);
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

    public static final int CAMERA = 1;
    public static final String CAMERA_URL = "url";



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode){
            case CAMERA:
                imageUrl = data.getStringExtra(CAMERA_URL);
                mono_image.setImageURI(imageUrl);
                //toast(url, Toast.LENGTH_LONG);
                break;
        }
    }

    EditMonoContract.Presenter mPresenter;
    @Override
    public void setPresenter(EditMonoContract.Presenter presenter) {
        super.setPresenter(presenter);
        mPresenter = presenter;
    }

    String imageUrl = null;

    @Override
    public void showResult(SWrapper sWrapper) {
        dialogOk("操作成功", new DialogLinstener() {
            @Override
            public void confirm(Dialog dialog) {
                getActivity().finish();
            }

            @Override
            public void cancel(Dialog dialog) {
                getActivity().finish();
            }
        });
    }
}
