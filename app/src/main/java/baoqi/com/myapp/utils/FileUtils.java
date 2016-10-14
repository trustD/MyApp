package baoqi.com.myapp.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.Snackbar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import retrofit2.http.Url;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by hasee on 2016/10/11.
 */

public class FileUtils {

    public static void createDir(String dirname){
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File directory = new File(externalStorageDirectory,dirname);
        if (!directory.exists())
            directory.mkdir();
    }

    public static void downImage(PhotoViewAttacher mPhotoViewAttacher,String dirname){
        Bitmap drawingCache = mPhotoViewAttacher.getImageView().getDrawingCache();
        try {
           File file = new File(dirname,new Date().getTime() +".jpg");

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            //将缓存资源压缩

            drawingCache.compress(Bitmap.CompressFormat.JPEG,100,bos);

            bos.flush();
            bos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static File getImage(String dirname){
        File[] files = new File(dirname).listFiles();
        for (int i=0; i <files.length;i++ ){
            File file = files[i];

                return file;

        }
        return null;
    }
}
