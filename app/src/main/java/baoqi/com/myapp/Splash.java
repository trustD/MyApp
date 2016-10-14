package baoqi.com.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import baoqi.com.myapp.Activity.Guiding;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

    @BindView(R.id.splash_image)
    ImageView mSplashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
        start();
    }

    Intent intent = null;

    private void start() {
        SharedPreferences shared = getSharedPreferences("args", MODE_PRIVATE);
        boolean isGuid = shared.getBoolean("isGuid", false);
        if (isGuid) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, Guiding.class);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void init() {
        Glide.with(this)
                .load(R.mipmap.zhihu1)
                .fitCenter().into(mSplashImage);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();

            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
