package baoqi.com.myapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import baoqi.com.myapp.MainActivity;
import baoqi.com.myapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Guiding extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.page_guiding)
    ViewPager mPageGuiding;
    @BindView(R.id.start)
    Button mStart;

    LinearLayout mPoints;

    private ArrayList<View> pageViews = new ArrayList<>();
    private ImageView imageView;
    private ImageView[]  points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guiding);
        ButterKnife.bind(this);
        mPoints = (LinearLayout) findViewById(R.id.points1);

        initView();
        initPoints();
        setupListener();
    }
    @OnClick(R.id.start)
    void start(View v){
        startActivity(new Intent(Guiding.this,MainActivity.class));
    }




    private void initPoints() {
        int count = mPoints.getChildCount();
        points = new ImageView[count];
        for (int i = 0;i<count;i++){
            ImageView x = (ImageView) mPoints.getChildAt(i);
            points[i] = x;
        }

    }

    private void initView() {
        ImageView a = new ImageView(this);
        a.setImageResource(R.mipmap.a_1);
        a.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView b = new ImageView(this);
        b.setImageResource(R.mipmap.a_2);
        b.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView c = new ImageView(this);
        c.setImageResource(R.mipmap.a_3);
        c.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView d = new ImageView(this);
        d.setImageResource(R.mipmap.a_4);
        d.setScaleType(ImageView.ScaleType.FIT_XY);
        pageViews.add(a);
        pageViews.add(b);
        pageViews.add(c);
        pageViews.add(d);
    }





    int current = 0;
    private void setupListener() {
      mPageGuiding.setAdapter(new A());
      mPageGuiding.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (ImageView iv : points) {
            iv.setVisibility(View.VISIBLE);
        }
        if (position ==current) {
            points[position].setImageResource(android.R.drawable.presence_online);
        } else {
            points[position].setImageResource(android.R.drawable.presence_online);

            points[current].setImageResource(android.R.drawable.presence_invisible);
            current = position;
        }
        //显示最后一页时，引导完成并存储标记
        if (position == pageViews.size()-1) {
            mStart.setVisibility(View.VISIBLE);
            for (ImageView iv : points) {
                iv.setVisibility(View.GONE);
            }

            SharedPreferences sp = getSharedPreferences("args", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isGuid", true).commit();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class A extends PagerAdapter{

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageViews.get(position));
            return pageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pageViews.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }




}
