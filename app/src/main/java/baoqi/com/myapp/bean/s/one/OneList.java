package baoqi.com.myapp.bean.s.one;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/10/13.
 */

public class OneList {

    /**
     * hpcontent_id : 1453
     * hp_title : VOL.1425
     * author_id : -1
     * hp_img_url : http://image.wufazhuce.com/Flndhx9Dbqn3ez9GqPMBmDZHiMq2
     * hp_img_original_url : http://image.wufazhuce.com/Flndhx9Dbqn3ez9GqPMBmDZHiMq2
     * hp_author : 悠闲的午后&Lost7 作品
     * ipad_url : http://image.wufazhuce.com/Fma5J6AzHq9joYpodTVpvX0aNEOY
     * hp_content : 那些腼腆的单身喵，大都有一堆表情包。为了那个喜欢的人而存的：知道不可能，知道得不到，既然不能深情也不能矫情，让我以逗B的身份留在你身边好不好。by 大冰
     * hp_makettime : 2016-08-31 23:00:00
     * last_update_date : 2016-08-26 13:54:38
     * web_url : http://m.wufazhuce.com/one/1453
     * wb_img_url :
     * praisenum : 36884
     * sharenum : 3991
     * commentnum : 693
     */
    @SerializedName("data")
    ArrayList<OneBean> data;
     int res;

    public ArrayList<OneBean> getData() {
        return data;
    }

    public int getRes() {
        return res;
    }
}
