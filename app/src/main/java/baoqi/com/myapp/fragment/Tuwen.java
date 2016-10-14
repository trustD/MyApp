package baoqi.com.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

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
    IRecyclerView mTuWenRecycler;

    private ArrayList<Integer> localImages = new ArrayList<>();
    OnePresenterlmpl mPresenterlmpl;
    private ArrayList<OneBean> datas = new ArrayList<>();
    private IRecyclerView mIRecyclerView;
    private Subscription mSubscription;
    private TuWenAdapter mAdapter;

    int nian = 2016;
    int yue = 10;
    String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuwen, container, false);
         mTuWenRecycler = (IRecyclerView) view.findViewById(R.id.tuWenRecycler);

        initData();
        initView();
        return view;
    }

    private void initView() {

        mTuWenRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        //添加头部视图

        mAdapter.notifyDataSetChanged();
        mTuWenRecycler.setIAdapter(mAdapter);
        mTuWenRecycler.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                yue-=1;
                if (yue==1){
                    nian-=1;
                    yue=12;
                }
                loadData();
            }
        });

        mTuWenRecycler.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                yue-=1;
                if (yue==1){
                    nian-=1;
                    yue=12;
                }
                loadData();
            }
        });
        mIRecyclerView.setRefreshing(false);
    }


    private void initData() {
        mAdapter = new TuWenAdapter(datas,getContext());
        mPresenterlmpl = new OnePresenterlmpl(this);
        loadData();





    }

    private void loadData() {
        date = nian+"-"+yue;
        mPresenterlmpl.loadWebData(date);
    }


    @Override
    public void updateData(OneList list) {
        datas.addAll(list.getData());
        mAdapter.notifyDataSetChanged();

    }
}
