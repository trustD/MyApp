package baoqi.com.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import baoqi.com.myapp.Activity.ZhihuDeatils;
import baoqi.com.myapp.MainActivity;
import baoqi.com.myapp.R;
import baoqi.com.myapp.bean.s.zhihu.ZhihuDailyItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hasee on 2016/10/11.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MainActivity.LoadingMore{
    public static final  int TYPE_LOADING_MORE = -1;
    public static final int NORMAL_ITEM = 1;
    boolean showLoadingMore;
    private ArrayList<ZhihuDailyItem> zhihuDailyItems = new ArrayList<>();
    private Context mContext;
    private String mImageUrl;

    public ZhihuAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case NORMAL_ITEM:
                return new ZhihuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.zhihu_recycler_item,parent,false));
            case TYPE_LOADING_MORE:
                return new LoadingMoreHolder(LayoutInflater.from(mContext).inflate(R.layout.infinite_loading,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //获得当前条目的布局类型
        int type = getItemViewType(position);
        switch (type){
            case NORMAL_ITEM:
                bindViewHolderNormal((ZhihuViewHolder)holder,position);
                break;
            case TYPE_LOADING_MORE:
                bindViewHolderLoading((LoadingMoreHolder)holder,position);
                break;
        }

    }

    private void bindViewHolderLoading(LoadingMoreHolder holder, int position) {
        holder.mProgressBar.setVisibility(showLoadingMore == true?View.VISIBLE:View.INVISIBLE);
    }

    private void bindViewHolderNormal(ZhihuViewHolder holder, int position) {
        final ZhihuDailyItem zhihuDailyItem = zhihuDailyItems.get(holder.getAdapterPosition());
        holder.mTextView.setText(zhihuDailyItem.getTitle());
        Glide.with(mContext)
                .load(zhihuDailyItems.get(position).getImages()[0])
                .centerCrop()
                .crossFade()
                .into(holder.mImageView);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToZhihuDeatilsActivity(zhihuDailyItem);
            }
        });

    }

    private void jumpToZhihuDeatilsActivity(ZhihuDailyItem zhihuDailyItem) {
        Intent intent = new Intent(mContext, ZhihuDeatils.class);
        intent.putExtra("zhihuDetils",zhihuDailyItem);
        mContext.startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return zhihuDailyItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        //如果 position小于Item总数同时 大于0就返回普通布局
        if (position < getDataItemCount() && getDataItemCount() > 0){
            return NORMAL_ITEM;
        }

        return TYPE_LOADING_MORE;
    }
    private int getDataItemCount(){
        return zhihuDailyItems.size();
    }


    public void addItems(ArrayList<ZhihuDailyItem>list){
        int n = list.size();

        zhihuDailyItems.addAll(list);
        notifyDataSetChanged();
    }
    private int getLoadingMoreItemPosition(){
        //正在刷新就在item底部加载数据  否则 在顶头加载数据
        return showLoadingMore?getItemCount()-1:RecyclerView.NO_POSITION;
    }

    @Override
    public void loadingStart() {
        //如果刷新开关是true 就返回 否则就将开关设为true开始舒心
        if (showLoadingMore) return;
        showLoadingMore = true;
        //通知加入新的条目
        notifyItemInserted(getLoadingMoreItemPosition());
    }

    @Override
    public void loadingfinish() {
        if (!showLoadingMore) return;
        //刷新结束
        final  int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemChanged(loadingPos);
    }

    public void clearData(){
        zhihuDailyItems.clear();
        notifyDataSetChanged();
    }




    public static class LoadingMoreHolder extends RecyclerView.ViewHolder{
        ProgressBar mProgressBar;
        public LoadingMoreHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView;
        }
    }
    class ZhihuViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ImageView mImageView;
        CardView mCardView;
        public ZhihuViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_text_id);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image_id);
            mCardView = (CardView) itemView.findViewById(R.id.card_background_zhihu);
        }
    }
}
