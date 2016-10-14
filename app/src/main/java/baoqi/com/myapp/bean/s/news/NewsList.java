package baoqi.com.myapp.bean.s.news;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hasee on 2016/10/12.
 */

public class NewsList {
    @SerializedName("T1348647853363")
   public  ArrayList<News> list;

    public ArrayList<News> getList() {
        return list;
    }

    public NewsList(ArrayList<News> list) {
        this.list = list;
    }
}
