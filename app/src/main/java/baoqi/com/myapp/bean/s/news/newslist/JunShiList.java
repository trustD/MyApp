package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class JunShiList {

    @SerializedName("T1348648141035")
    ArrayList<News> listl;

    public ArrayList<News> getListl() {
        return listl;
    }

    public JunShiList(ArrayList<News> listl) {

        this.listl = listl;
    }
}
