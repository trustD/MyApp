package baoqi.com.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import baoqi.com.myapp.MainActivity;
import baoqi.com.myapp.R;
import baoqi.com.myapp.bean.s.news.News;
import baoqi.com.myapp.bean.s.news.NewsDeatils;

/**
 * Created by hasee on 2016/10/12.
 */

public class TopNewsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements MainActivity.LoadingMore{



    public TopNewsRVAdapter(Context context) {
        mContext = context;
    }

    ArrayList<News> datas;
    Context mContext;
    boolean showLoadingMore;

    public TopNewsRVAdapter(ArrayList<News> datas, Context context) {
        this.datas = datas;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.top_news_item,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      //bindViewHolderNormal((MyHolder)holder,position);
        News news = datas.get(holder.getAdapterPosition());

        ((MyHolder) holder).title.setText(news.getTitle());
        Glide.with(mContext)
                .load(news.getImgsrc())
                .centerCrop()
                .into(((MyHolder) holder).image);


    }

    private void bindViewHolderNormal(final MyHolder holder, int position) {
        final News news = datas.get(holder.getAdapterPosition());
        holder.title.setText(news.getTitle());
        Glide.with(mContext)
                .load(news.getImgsrc())
                .centerCrop()
                .into(holder.image);
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewsDetails(news,holder);
            }
        });





    }

    private void goToNewsDetails(News news, MyHolder holder) {
        Intent intent = new Intent(mContext,NewsDeatils.class);
        intent.putExtra("id",news.getDocid());
        mContext.startActivity(intent);


    }

    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }
    private int getDataItemCount() {

        return datas.size();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void loadingStart() {
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());

    }

    public void addItems(ArrayList<News> list) {
        list.remove(0);
        int n = list.size();
        datas.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public void loadingfinish() {
        if (!showLoadingMore) return;
        final  int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }

    public void clearData(){
        datas.clear();
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        LinearLayout background;
        public MyHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.nows_item_image_id);
            title = (TextView) itemView.findViewById(R.id.item_text_title);
            background = (LinearLayout) itemView.findViewById(R.id.background_news);
        }
    }
}
