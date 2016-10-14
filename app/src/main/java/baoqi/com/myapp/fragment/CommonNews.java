package baoqi.com.myapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

import baoqi.com.myapp.R;
import baoqi.com.myapp.adapter.TopNewsRVAdapter;
import baoqi.com.myapp.bean.s.news.News;
import baoqi.com.myapp.events.MsgEvent;
import baoqi.com.myapp.presenter.impPresenter.CommonNesPresenterImpl;
import baoqi.com.myapp.presenter.impView.ICommonNewsFragment;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommonNews extends Fragment implements ICommonNewsFragment{

    private CommonNesPresenterImpl mCommonNesPresenter;
    private PullLoadMoreRecyclerView mRecyclerView;
    private TopNewsRVAdapter mNewsRVAdapter;
    String type ;
    int id;
    ArrayList<News> data = new ArrayList<>();

    public CommonNews() {
        EventBus.getDefault().register(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_news, container, false);
        mRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.commonnews_recycler);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView.setLinearLayout();
        if (data.isEmpty()){
            mNewsRVAdapter.notifyDataSetChanged();
        }
        mRecyclerView.setAdapter(mNewsRVAdapter);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                id=id+20;
                mCommonNesPresenter.getWebNews(type,id);
                mRecyclerView.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                id=id+20;
                mCommonNesPresenter.getWebNews(type,id);
                mRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }

    private void initData() {
        mCommonNesPresenter = new CommonNesPresenterImpl(this);
        mNewsRVAdapter = new TopNewsRVAdapter(data,getContext());
        id =0;
        mCommonNesPresenter.getWebNews(type,id);
    }

    @Override
    public void updateData(ArrayList<News> list) {
        data.addAll(list);
        mNewsRVAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(MsgEvent msg){

        this.type = msg.getMsg();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
