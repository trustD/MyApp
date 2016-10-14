package baoqi.com.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by hasee on 2016/10/12.
 */

public class NewsViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> datas;
    ArrayList<String> mTabdata;

    public NewsViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> datas, ArrayList<String> tabdata) {
        super(fm);
        this.datas = datas;
        this.mTabdata = tabdata;
    }

    public NewsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public NewsViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabdata.get(position);
    }

}
