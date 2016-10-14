package baoqi.com.myapp.bean.s.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hasee on 2016/10/11.
 */

public class ZhihuDaily {
    @SerializedName("date")
    private String date;
    @SerializedName("top_stories")
    private ArrayList<ZhihuDailyItem> mZhihuDailyItems;
    @SerializedName("stories")
    private ArrayList<ZhihuDailyItem> stories;

    public ArrayList<ZhihuDailyItem> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhihuDailyItem> stories) {
        this.stories = stories;
    }

    public ArrayList<ZhihuDailyItem> getZhihuDailyItems() {
        return mZhihuDailyItems;
    }

    public void setZhihuDailyItems(ArrayList<ZhihuDailyItem> zhihuDailyItems) {
        mZhihuDailyItems = zhihuDailyItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}
