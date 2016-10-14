package baoqi.com.myapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import baoqi.com.myapp.Activity.MeiziPhotoDescribeActivity;
import baoqi.com.myapp.MainActivity;
import baoqi.com.myapp.R;
import baoqi.com.myapp.bean.s.Mm;

/**
 * Created by hasee on 2016/10/10.
 */

public class MeiziAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements MainActivity.LoadingMore{
    private Context mContext;

    private static final int TYPE_LOADING_MORE = -1;
    private static final int NOMAL_ITEM = 1;

    boolean showLoadingMore;
    private ArrayList<Mm> meiziItems = new ArrayList<>();

    public MeiziAdapter(Context context) {
        mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       switch (viewType){
           case NOMAL_ITEM:
               return new MeiziViewHolder(LayoutInflater.from(mContext).inflate(R.layout.meizi_layout_item,parent,false));
           case TYPE_LOADING_MORE:
               return new LoadingMoreHolder(LayoutInflater.from(mContext).inflate(R.layout.infinite_loading,parent,false));
       }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case NOMAL_ITEM:
                bindViewHolderNormal((MeiziViewHolder) holder, position);
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHold((LoadingMoreHolder) holder, position);
                break;
        }
    }

    private void bindLoadingViewHold(LoadingMoreHolder holder, int position) {
        holder.progressBar.setVisibility(showLoadingMore? View.VISIBLE : View.INVISIBLE);
    }

    private void bindViewHolderNormal(final MeiziViewHolder holder, int position) {
        final Mm mm = meiziItems.get(holder.getAdapterPosition());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/10/10
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDescribeActivity(mm,holder);
            }
        });




        Glide.with(mContext)
                .load(mm.getUrl())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                        // TODO: 2016/10/10
                    }
                }).centerCrop()
                .into(holder.imageView);


    }

    private void startDescribeActivity(Mm mm, MeiziViewHolder holder) {
        Intent intent = new Intent(mContext, MeiziPhotoDescribeActivity.class);
        intent.putExtra("image",mm.getUrl());
        mContext.startActivity(intent);


    }



    @Override
    public int getItemCount() {
        return meiziItems.size();
    }

    public void addItems(ArrayList<Mm> list) {
        meiziItems.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position<meiziItems.size()
                &&meiziItems.size()>0){

            return NOMAL_ITEM;
        }
        return TYPE_LOADING_MORE;
    }

    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }


    @Override
    public void loadingStart() {
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());

    }

    @Override
    public void loadingfinish() {
        if (!showLoadingMore) return;
        int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }

    public void clearData() {
        meiziItems.clear();
        notifyDataSetChanged();
    }


    public static class LoadingMoreHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingMoreHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView;
        }
    }


    class MeiziViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MeiziViewHolder(View itemView) {
            super(itemView);
           imageView= (ImageView) itemView.findViewById(R.id.item_image_id);
        }
    }
}
