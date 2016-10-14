package baoqi.com.myapp.bean.s.news;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2016/10/10.
 */

public class News implements Serializable{

    private String docid;

    private String title;


    private String digest;


    private String imgsrc;


    private String source;


    private String ptime;

    @Override
    public String toString() {
        return "News{" +
                "docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", source='" + source + '\'' +
                ", ptime='" + ptime + '\'' +
                ", tag='" + tag + '\'' +
                ", hasFadedIn=" + hasFadedIn +
                '}';
    }

    private String tag;

    public  boolean hasFadedIn=false;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
