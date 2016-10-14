package baoqi.com.myapp.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import baoqi.com.myapp.R;
import baoqi.com.myapp.bean.s.zhihu.ZhihuDailyItem;
import baoqi.com.myapp.bean.s.zhihu.ZhihuStory;
import baoqi.com.myapp.presenter.impPresenter.ZhihuDeatilsImpl;
import baoqi.com.myapp.presenter.impView.ZhIhuDeatils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhihuDeatils extends Activity implements ZhIhuDeatils {

    @BindView(R.id.zhihui_web)
    WebView mZhihuiWeb;
    private ZhihuDeatilsImpl mZhihuDeatilsImpl;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_deatils);
        ButterKnife.bind(this);
        ZhihuDailyItem ZhihuDailyItem = (ZhihuDailyItem) getIntent().getSerializableExtra("zhihuDetils");
        getUrl(ZhihuDailyItem);

    }

    private void getUrl(ZhihuDailyItem item) {
        mZhihuDeatilsImpl = new ZhihuDeatilsImpl(this);
        mZhihuDeatilsImpl.getZhiStory(item.getId());

    }

    @Override
    public void showZhihuStory(ZhihuStory zhihuStory) {
        url = zhihuStory.getShareUrl();
        Log.d("0---",url);
        mZhihuiWeb.loadUrl(url);
        WebSettings setting = mZhihuiWeb.getSettings();
        setting.setJavaScriptEnabled(false);

        mZhihuiWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }
}
