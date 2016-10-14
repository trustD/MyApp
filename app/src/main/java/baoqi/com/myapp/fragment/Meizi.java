package baoqi.com.myapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import baoqi.com.myapp.R;
import baoqi.com.myapp.adapter.MeiziAdapter;
import baoqi.com.myapp.bean.s.Mm;
import baoqi.com.myapp.layout.WrapContentLinearLayoutManager;
import baoqi.com.myapp.presenter.impPresenter.MeiziPresenterlmpl;
import baoqi.com.myapp.presenter.impView.IMeiziFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class Meizi extends Fragment implements IMeiziFragment {


    @BindView(R.id.recycle_meizi)
    RecyclerView mRecycleMeizi;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    MeiziPresenterlmpl mMeiziPresenter;
    MeiziAdapter meiziAdapter;
    RecyclerView.OnScrollListener loadmoreListener;

    private boolean loading;

    private int index = 1;
    private WrapContentLinearLayoutManager llm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        initDate();

        initView();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initDate() {
        mMeiziPresenter = new MeiziPresenterlmpl(this);

    }

    private void initView() {
        meiziAdapter = new MeiziAdapter(getContext());
        llm = new WrapContentLinearLayoutManager(getContext());
        initListener();
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleMeizi.setLayoutManager(llm);
        mRecycleMeizi.setAdapter(meiziAdapter);
        mRecycleMeizi.addOnScrollListener(loadmoreListener);
        mRecycleMeizi.setItemAnimator(new DefaultItemAnimator());
        loadDate();
    }

    private void loadDate() {
        if (meiziAdapter.getItemCount() > 0) {
            meiziAdapter.clearData();
        }
        mMeiziPresenter.getMeiziData(index);
    }


    private void initListener() {

        loadmoreListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    int visibleItemCount = llm.getChildCount();
                    int totalItemCount = llm.getItemCount();
                    int pastVisiblesItems = llm.findFirstVisibleItemPosition();
                    //一开始有个小bug  一滑动就没有限制的刷新，也不崩溃。检查发现  加载数据方法 没有写在方法体里
                    if (!loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount){
                        loading = true;
                        index += 1;
                        loadMoreDate();
                    }

                }

            }
        };


    }

    private void loadMoreDate() {
        meiziAdapter.loadingStart();
        mMeiziPresenter.getMeiziData(index);
    }


    @Override
    public void updateMeiziData(ArrayList<Mm> list) {
        meiziAdapter.loadingfinish();
        loading = false;
        meiziAdapter.addItems(list);

    }

    @Override
    public void showProgressDialog() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidProgressDialog() {
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        mProgress.setVisibility(View.INVISIBLE);
        if (mRecycleMeizi != null) {
            Snackbar.make(mRecycleMeizi, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMeiziPresenter.getMeiziData(index);
                }
            }).show();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMeiziPresenter.unsubscrible();
    }
}
