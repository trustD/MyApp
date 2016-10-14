package baoqi.com.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import baoqi.com.myapp.R;
import baoqi.com.myapp.adapter.LocalImageHolder;
import baoqi.com.myapp.adapter.TopNewsRVAdapter;
import baoqi.com.myapp.adapter.TuWenAdapter;
import baoqi.com.myapp.api.ApiManage;
import baoqi.com.myapp.bean.s.one.OneBean;
import baoqi.com.myapp.bean.s.one.OneList;
import baoqi.com.myapp.presenter.impPresenter.OnePresenterlmpl;
import baoqi.com.myapp.presenter.impView.IOneFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tuwen extends Fragment implements IOneFragment {


    OnePresenterlmpl mPresenterlmpl;
    private ArrayList<OneBean> datas = new ArrayList<>();
    private TuWenAdapter mAdapter;
    int nian = 2016;
    int yue = 10;
    String date;
    private PullLoadMoreRecyclerView mTuWenRecycler;
    private TextView mOneDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuwen, container, false);
        mTuWenRecycler = (PullLoadMoreRecyclerView) view.findViewById(R.id.tuwenRecyclerView);
        mOneDate = (TextView) view.findViewById(R.id.one_date);
        mTuWenRecycler.setGridLayout(2);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mTuWenRecycler.setAdapter(mAdapter);
        mTuWenRecycler.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                refresh();
            }
        });

    }

    private void refresh() {
        yue = yue-1;
        if (yue==1){
            nian= nian-1;
            yue = 12;
        }
        switch (yue){
            case 12:
                mOneDate.setText("Dec."+nian);
                break;
            case 11:
                mOneDate.setText("Nov."+nian);
                break;
            case 10:
                mOneDate.setText("Oct."+nian);
                break;
            case 9:
                mOneDate.setText("Sep."+nian);
                break;
            case 8:
                mOneDate.setText("Aug."+nian);
                break;
            case 7:
                mOneDate.setText("Jul."+nian);
                break;
            case 6:
                mOneDate.setText("Jun."+nian);
                break;
            case 5:
                mOneDate.setText("May."+nian);
                break;
            case 4:
                mOneDate.setText("Apr."+nian);
                break;
            case 3:
                mOneDate.setText("Mar."+nian);
                break;
            case 2:
                mOneDate.setText("Feb."+nian);
                break;
            case 1:
                mOneDate.setText("Jan."+nian);
                break;
        }
        datas.clear();
        loadData();
        mTuWenRecycler.setPullLoadMoreCompleted();
    }


    private void initData() {
        mAdapter = new TuWenAdapter(datas, getContext());
        mPresenterlmpl = new OnePresenterlmpl(this);
        loadData();
    }

    private void loadData() {
        date = nian + "-" + yue;
        mPresenterlmpl.loadWebData(date);
    }


    @Override
    public void updateData(OneList list) {
        datas.addAll(list.getData());
        mAdapter.notifyDataSetChanged();

    }


}
