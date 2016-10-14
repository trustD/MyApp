package baoqi.com.myapp.bean.s.zhihu;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by hasee on 2016/10/11.
 */
public class ZhihuDailyItem implements Serializable{

    private String[] images;

    private int type;

    @Override
    public String toString() {
        return "ZhihuDailyItem{" +
                "images=" + Arrays.toString(images) +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", hasFadedIn=" + hasFadedIn +
                '}';
    }

    private String id;

    private String title;
    private String date;
    public boolean hasFadedIn = false;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
