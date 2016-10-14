package baoqi.com.myapp.bean.s.news.newslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import baoqi.com.myapp.bean.s.news.News;

/**
 * Created by hasee on 2016/10/13.
 */

public class FootnewsList {

    public ArrayList<News> getFootnews() {
        return Footnews;
    }

    public FootnewsList(ArrayList<News> footnews) {
        Footnews = footnews;
    }

    @SerializedName("T1399700447917")
    ArrayList<News> Footnews;

}
