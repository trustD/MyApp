package baoqi.com.myapp.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import baoqi.com.myapp.R;
import baoqi.com.myapp.utils.FileUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MeiziPhotoDescribeActivity extends Activity {


    PhotoViewAttacher mPhotoViewAttacher;
    @BindView(R.id.shot)
    ImageView mShot;
    String mImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_photo_describe);
        ButterKnife.bind(this);
        parseIntent();
        getData();
        setupAttacher();


    }
    @OnClick(R.id.download)
    public void downImage(View view){
        saveImage();
        successful();
    }


    private void setupAttacher() {
        mPhotoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(MeiziPhotoDescribeActivity.this)
                        .setMessage("少侠，好眼光赶紧收了她")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                saveImage();
                                successful();
                            }
                        }).show();
                return true;
            }
        });

    }

    private void successful() {
        Toast.makeText(getApplicationContext(),getString(R.string.image_success),Toast.LENGTH_SHORT).show();
    }

    private void saveImage() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File directory = new File(externalStorageDirectory,"Meizituijian");
        if (!directory.exists())
            directory.mkdir();
        Bitmap drawingCache = mPhotoViewAttacher.getImageView().getDrawingCache();
        try {
            //TODO 后期要添加 判断文件是否存在的功能
            File file = new File(directory, new Date().getTime() + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            drawingCache.compress(Bitmap.CompressFormat.JPEG,100,fos);
            //通知系统遍历文件
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            getApplicationContext().sendBroadcast(intent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Snackbar.make(getCurrentFocus(),"阿偶出错了呢",Snackbar.LENGTH_SHORT).show();
        }
    }



    private void getData() {
        Glide.with(this)
                .load(mImageUrl)
                .centerCrop()
                .into(mShot);

        mPhotoViewAttacher = new PhotoViewAttacher(mShot);

    }


    private void parseIntent() {
       mImageUrl = getIntent().getStringExtra("image");
    }
}
