package baoqi.com.myapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import baoqi.com.myapp.R;
import baoqi.com.myapp.adapter.ZhihuAdapter;
import baoqi.com.myapp.bean.s.zhihu.ZhihuDaily;
import baoqi.com.myapp.layout.WrapContentLinearLayoutManager;
import baoqi.com.myapp.presenter.impPresenter.ZhihuPresenterImpl;
import baoqi.com.myapp.presenter.impView.IZhihuFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends Fragment implements IZhihuFragment {

    @BindView(R.id.zhihu_recycler)
    RecyclerView mZhihuRecycler;
    @BindView(R.id.prograss)
    ProgressBar mPrograss;
    private ZhihuPresenterImpl mZhihuPresenter;
    private ZhihuAdapter mZhihuAdapter;
    private View view;
    private WrapContentLinearLayoutManager llm;
    RecyclerView.OnScrollListener loadingMoreListener;

    boolean loading;
    private String currentLoadDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhihu, container, false);
        ButterKnife.bind(this, view);

        initData();
        initListener();
        initView();
        return view;
    }



    private void initData() {
        mZhihuPresenter = new ZhihuPresenterImpl(this);
        mZhihuAdapter = new ZhihuAdapter(getContext());


    }
    private void initListener() {
        loadingMoreListener = new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0)
                {
                    int visibleItemCount = llm.getChildCount();
                    int totalItemCount =llm.getItemCount();
                    int pastViesiblesItems = llm.findFirstVisibleItemPosition();

                    if (!loading&&(visibleItemCount+pastViesiblesItems>=totalItemCount)){
                        loading = true;
                        loadMoreDate();
                    }
                }
            }
        };

    }



    private void initView() {

        llm = new WrapContentLinearLayoutManager(getContext());
        mZhihuRecycler.setLayoutManager(llm);
        mZhihuRecycler.setAdapter(mZhihuAdapter);
        mZhihuRecycler.addOnScrollListener(loadingMoreListener);
        loadData();
    }
    //调用最新的数据
    private void loadData() {
        if (mZhihuAdapter.getItemCount() > 0){
            mZhihuAdapter.clearData();
        }
        currentLoadDate = "0";
        mZhihuPresenter.getLastZhihuNews();
    }
    //滑动加载更多
    private void loadMoreDate() {
        mZhihuAdapter.loadingStart();
        mZhihuPresenter.getTheDaily(currentLoadDate);
    }


    //将条目添加进adapter中
    @Override
    public void updateList(ZhihuDaily zhihuDaily) {
        if (loading){
            loading = false;
            mZhihuAdapter.loadingfinish();
        }
        currentLoadDate = zhihuDaily.getDate();
        mZhihuAdapter.addItems(zhihuDaily.getStories());
        //如果新的数据没有填满屏幕  就调用加载更多数据方法
        if (!mZhihuRecycler.canScrollVertically(View.SCROLL_INDICATOR_BOTTOM)){
            loadMoreDate();
        }

    }

    @Override
    public void showProgressDialog() {
        if (mPrograss !=null){
            mPrograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hidProgressDialog() {
        if (mPrograss != null){
            mPrograss.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(String error) {

    }


}
