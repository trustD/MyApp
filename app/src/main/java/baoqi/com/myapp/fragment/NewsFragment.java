package baoqi.com.myapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import baoqi.com.myapp.R;
import baoqi.com.myapp.adapter.NewsViewPagerAdapter;
import baoqi.com.myapp.events.MsgEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    ViewPager mNewsViewpager;
    NewsViewPagerAdapter mNewsAdapter;
    ArrayList<Fragment> datas = new ArrayList<>();
    String[] resourse;
    ArrayList<String> tabsdata = new ArrayList<>();
    String[] type = {

            "T1348647909107", "T1399700447917", "T1348648141035", "T1348648517839", "T1348648756099", "T1348649580692",

            "T1348648650048", "T1348654060988", "T1350383429665", "T1348654151579", "T1348650593803",
            "T1348650839000", "T1370583240249", "T1379038288239", "T1348649145984", "T1348649776727",
            "T1356600029035", "T1348649654285", "T1349837698345", "T1397016069906", "T1348649475931"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_news, null);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.news_tab);
        mNewsViewpager = (ViewPager) view.findViewById(R.id.news_viewpager);
        initData();
        mNewsAdapter = new NewsViewPagerAdapter(getChildFragmentManager(), datas, tabsdata);
        mNewsViewpager.setAdapter(mNewsAdapter);
        tabs.setupWithViewPager(mNewsViewpager);
        return view;

    }

    private void initData() {

        resourse = getResources().getStringArray(R.array.news);
        for (int i = 0; i < resourse.length; i++) {

            datas.add(new CommonNews());
            EventBus.getDefault().post(new MsgEvent(type[i]));
            tabsdata.add(resourse[i]);

        }
    }


}
