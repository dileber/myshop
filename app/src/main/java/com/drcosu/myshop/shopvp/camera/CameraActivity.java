package com.drcosu.myshop.shopvp.camera;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drcosu.myshop.R;
import com.drcosu.myshop.base.BaseShopActivity;
import com.drcosu.myshop.data.source.MonoRepository;
import com.drcosu.myshop.shopvp.mono.EditMonoFragment;
import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.data.DataSourceException;
import com.drcosu.ndileber.mvp.data.model.SWrapper;
import com.drcosu.ndileber.mvp.data.model.SelectModel;
import com.drcosu.ndileber.tools.HString;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.media.BitmapDecoder;
import com.drcosu.ndileber.tools.selectdialog.BaseNoticeWindow;
import com.drcosu.ndileber.tools.selectdialog.SelectDialog;
import com.drcosu.ndileber.tools.storage.StorageType;
import com.drcosu.ndileber.tools.storage.UStorage;
import com.drcosu.ndileber.utils.UToolBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends BaseShopActivity {


    public static void start(Activity context, int requestCode){
        Intent it = new Intent();
        it.setClass(context,CameraActivity.class);
        context.startActivityForResult(it,requestCode);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected int layoutViewId() {
        return R.layout.activity_camera;
    }

    String outPath = UStorage.getWritePath(HString.get32UUID() + ".jpg", StorageType.TYPE_TEMP);

    ImageView imageView;
    TextView button;

    SelectDialog<SelectModel> chooseDialog;

    LinearLayout camera_layout;

    List<SelectModel> list;

    @Override
    protected void initView(Bundle savedInstanceState) {

        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("图片");
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar,uToolBar);

        imageView = findView(R.id.imageView);
        button = findView(R.id.button);
        button.setText("选择照片途径");
        camera_layout = findView(R.id.camera_layout);

        list = new ArrayList<SelectModel>();

        list.add(new SelectModel(1,"拍照"));
        list.add(new SelectModel(2,"从相册选择"));



        chooseDialog =
                new SelectDialog.Builder<SelectModel>(this)
                        .setDataList(list)
                        .setButtonColor(ContextCompat.getColor(this,R.color.dileber_text_10))
                        .setButtonSize(14)
                        .setLastButtonSize(14)
                        .setTitleText("选择")
                        .build();

        chooseDialog.setButtonListener(new BaseNoticeWindow.OnButtonListener() {
            @Override
            public void onSureListener(View v, SelectModel selectModel) {
                switch (selectModel.getId()){
                    case 1:
                        startCamera();
                        break;
                    case 2:
                        startPhotos();
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDialog.show(camera_layout);
            }
        });
    }

    private static final int REQUEST_CODE_CAMERA = 0;
    private static final int REQUEST_CODE_PHOTO = 1;
    private static final int REQUEST_CODE_CROP = 2;

    public void startPhotos(){
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, REQUEST_CODE_PHOTO);
    }

    public void startCamera(){
        try {
            if (TextUtils.isEmpty(outPath)) {
                toast(getResources().getString(R.string.sdcard_not_enough_error), Toast.LENGTH_LONG);
                finish();
                return;
            }
            File outputFile = new File(outPath);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        } catch (ActivityNotFoundException e) {
            finish();
        } catch (Exception e) {
            toast(getResources().getString(R.string.sdcard_not_enough_head_error), Toast.LENGTH_LONG);
            finish();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                String photoPath = pathFromResult(data);
                if (!TextUtils.isEmpty(photoPath)) {
                    File outputFile = new File(photoPath);

                    startImageAction(Uri.fromFile(outputFile),true);
                    //crop(photoPath);
                }else{
                    showAlert(UDialog.DIALOG_ERROR,"拍照出现错误请,请重新拍照");
                }
                break;
            case REQUEST_CODE_PHOTO:
                if(data!=null){
                    startImageAction(data.getData(), true);
                }
                break;
            case REQUEST_CODE_CROP:
                if(data!=null){
                    final File cropfile = new File(data.getData().getPath());
                    Bitmap bitmap = BitmapDecoder.decodeSampledForDisplay(cropfile.getAbsolutePath());
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                    button.setText("上传");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loading();
                            MonoRepository.getInstance().addmonoimage(cropfile, new BaseDataSource.BaseCallback<SWrapper>() {
                                @Override
                                public void onSuccess(SWrapper sWrapper) {
                                    loadDialogDismiss();
                                    if(sWrapper.getState()==0){
                                        showAlert(UDialog.DIALOG_SUCCESS,"上传成功");
                                        Intent intent = new Intent();
                                        intent.putExtra(EditMonoFragment.CAMERA_URL,sWrapper.getMsg());
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }else if(sWrapper.getState()==-1){
                                        showAlert(UDialog.DIALOG_ERROR,sWrapper.getMsg());
                                    }
                                }

                                @Override
                                public void onFailure(DataSourceException e) {
                                    loadDialogDismiss();
                                    showAlert(UDialog.DIALOG_ERROR,e.getMessage());
                                }
                            });
                        }
                    });
                }
                break;

        }
    }


//    private void crop(String src) {
//        Intent intent = getIntent();
//        int outputX = intent.getIntExtra(CropImageActivity.EXTRA_OUTPUTX, 0);
//        int outputY = intent.getIntExtra(CropImageActivity.EXTRA_OUTPUTY, 0);
//        String outPath = intent.getStringExtra(CropImageActivity.EXTRA_FILE_PATH);
//        CropImageActivity.startForFile(this, src, outputX, outputY, outPath, REQUEST_CODE_CROP);
//    }


    private void startImageAction(Uri uri, boolean isCrop) {
        Intent intent = null;
        if (isCrop) {
            intent = new Intent("com.android.camera.action.CROP");
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, REQUEST_CODE_CROP);
    }

    private void onPickedCamera(Intent data, int code) {
        try {
            String photoPath = pathFromResult(data);
            if (!TextUtils.isEmpty(photoPath)) {
                //toast(photoPath,Toast.LENGTH_LONG);
                File outputFile = new File(photoPath);
                Bitmap bitmap = BitmapDecoder.decodeSampledForDisplay(outputFile.getAbsolutePath());
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    toast(getString(R.string.image_show_error),Toast.LENGTH_LONG);
                }
            }
        } catch (Exception e) {
            toast(getResources().getString(R.string.picker_image_error), Toast.LENGTH_LONG);
            finish();
        }
    }
    private String pathFromResult(Intent data) {
        if (data == null || data.getData() == null) {
            return outPath;
        }

        Uri uri = data.getData();
        Cursor cursor = getContentResolver()
                .query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if (cursor == null) {
            // miui 2.3 有可能为null
            return uri.getPath();
        } else {
            if (uri.toString().contains("content://com.android.providers.media.documents/document/image")) { // htc 某些手机
                // 获取图片地址
                String _id = null;
                String uridecode = uri.decode(uri.toString());
                int id_index = uridecode.lastIndexOf(":");
                _id = uridecode.substring(id_index + 1);
                Cursor mcursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, " _id = " + _id,
                        null, null);
                mcursor.moveToFirst();
                int column_index = mcursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                outPath = mcursor.getString(column_index);
                if (!mcursor.isClosed()) {
                    mcursor.close();
                }
                if (!cursor.isClosed()) {
                    cursor.close();
                }
                return outPath;

            } else {
                cursor.moveToFirst();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                outPath = cursor.getString(column_index);
                if (!cursor.isClosed()) {
                    cursor.close();
                }
                return outPath;
            }
        }
    }

}
