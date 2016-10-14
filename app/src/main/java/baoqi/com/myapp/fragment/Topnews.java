package baoqi.com.myapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import baoqi.com.myapp.R;
import baoqi.com.myapp.adapter.TopNewsRVAdapter;
import baoqi.com.myapp.bean.s.news.News;
import baoqi.com.myapp.bean.s.news.NewsDeatils;
import baoqi.com.myapp.bean.s.news.NewsList;
import baoqi.com.myapp.layout.WrapContentLinearLayoutManager;
import baoqi.com.myapp.presenter.impPresenter.NewsPresenter;
import baoqi.com.myapp.presenter.impView.INewsFragment;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class Topnews extends Fragment implements INewsFragment {

    private NewsPresenter mNewsPresenter;
    ArrayList<News> data;

    TopNewsRVAdapter mAdapter;
    RecyclerView.OnScrollListener loadingMoreListener;
    WrapContentLinearLayoutManager llm;
    boolean loading;
    private  String type;
    private  int id;
    private RecyclerView mRecycle;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topnews, container, false);

        mRecycle = (RecyclerView) view.findViewById(R.id.top_news_rv);
        initData();
        initView();

        return view;
    }





    private void initView() {
        initListener();
        llm = new WrapContentLinearLayoutManager(getContext());
        loadDate();
        mAdapter = new TopNewsRVAdapter(data,getContext());
        mRecycle.setLayoutManager(llm);
        mRecycle.setAdapter(mAdapter);
        mRecycle.addOnScrollListener(loadingMoreListener);

    }

    private void loadDate() {
        id = 0;
        type = "T1348647853363";
        mNewsPresenter.getNews(type, id);

    }

    private void initListener() {
        loadingMoreListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = llm.getChildCount();
                    int totalItemCount = llm.getItemCount();
                    int pastVisiblesItems = llm.findFirstVisibleItemPosition();

                    if (!loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true;
                        loadMoreDate();
                    }

                }

            }
        };
    }

    private void loadMoreDate() {
        mAdapter.loadingStart();

        mNewsPresenter.getNews(type, id);
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mNewsPresenter = new NewsPresenter(getContext(),this);

    }

    //给Adapter添加数据 在装饰者调用
//    @Override
//    public void updateList(List list) {
//        if (loading) {
//            loading = false;
//            RVAdapter.loadingfinish();
//        }
//        id += 20;
//
//
////        switch (type) {
////            case "T1348647909107":
////                datas =
////                break;
////
////            case "T1399700447917":
////                datas = list.getFootnews();
////                break;
////            case "T1348648141035":
////                datas = list.getJunShi();
////                break;
////            case "T1348648517839":
////                datas = list.getYule();
////                break;
////            case "T1348649580692":
////                datas = list.getCaiJing();
////                break;
////            case "T1348648650048":
////                datas = list.getKeJi();
////
////                break;
////            case "T1348654060988":
////                datas = list.getDianYing();
////
////                break;
////            case "T1350383429665":
////                datas = list.getQiChe();
////
////                break;
////            case "T1348654151579":
////                datas = list.getXiaoHua();
////
////                break;
////            case "T1348650593803":
////                datas = list.getYouXi();
////
////                break;
////            case "T1348650839000":
////                datas = list.getShiShang();
////
////                break;
////            case "T1370583240249":
////                datas = list.getQingGan();
////
////                break;
////            case "T1379038288239":
////                datas = list.getJingXuan();
////
////                break;
////            case "T1348649145984":
////                datas = list.getDianTai();
////
////                break;
////            case "T1348649776727":
////                datas = list.getNBA();
////
////                break;
////            case "T1351233117091":
////                datas = list.getShuMa();
////
////                break;
////            case "T1356600029035":
////                datas = list.getCaiPiao();
////
////                break;
////            case "T1348649654285":
////                datas = list.getShouJi();
////
////                break;
////            case "T1349837698345":
////                datas = list.getBoKe();
////
////                break;
////            case "T1397016069906":
////                datas = list.getBaoXueYouXi();
////
////                break;
////            case "T1348649475931":
////                datas = list.getCBA();
////
////                break;
//
//
//        }
//
//        RVAdapter.addItems(datas);
//
//    }


    //调用了adapter的additem方法，吧newsList数据添加到adapter中
    @Override
    public void updateNews(NewsList newsList) {
        if (loading){
            loading = false;
            mAdapter.loadingfinish();
        }
        id+=20;
        data.addAll(newsList.getList());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void getNewsDeatils(NewsDeatils deatils) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
