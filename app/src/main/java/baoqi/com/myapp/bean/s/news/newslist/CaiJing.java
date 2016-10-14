package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class CaiJing {
    @SerializedName("T1348649580692")
    ArrayList<News> list;

    public ArrayList<News> getList() {
        return list;
    }

    public CaiJing(ArrayList<News> list) {

        this.list = list;
    }
}
