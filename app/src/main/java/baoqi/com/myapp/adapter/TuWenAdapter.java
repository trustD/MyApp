package baoqi.com.myapp.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import baoqi.com.myapp.R;
import baoqi.com.myapp.bean.s.one.OneBean;
import butterknife.ButterKnife;

/**
 * Created by hasee on 2016/10/13.
 */

public class TuWenAdapter extends RecyclerView.Adapter<TuWenAdapter.MyHolder>{
    ArrayList<OneBean> datas;
    Context mContext;

    public TuWenAdapter(ArrayList<OneBean> datas, Context context) {
        this.datas = datas;
        mContext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.tuwen_item,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(datas.get(position).getHp_title());
        holder.content.setText(datas.get(position).getHp_content());
        Glide.with(mContext)
                .load(datas.get(position).getHp_img_url())
                .centerCrop().into(holder.tuwenImage);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
            ImageView tuwenImage;
            TextView title;
            TextView content;
        public MyHolder(View itemView) {
            super(itemView);
            tuwenImage = (ImageView) itemView.findViewById(R.id.tuwen_image);
            title = (TextView) itemView.findViewById(R.id.tuwen_hptitle);
            content = (TextView) itemView.findViewById(R.id.tuwen_hpcontent);
        }
    }
}
