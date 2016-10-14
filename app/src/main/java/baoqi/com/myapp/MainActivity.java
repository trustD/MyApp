package baoqi.com.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import baoqi.com.myapp.fragment.Meizi;
import baoqi.com.myapp.fragment.NewsFragment;
import baoqi.com.myapp.fragment.Tuwen;
import baoqi.com.myapp.fragment.ZhihuFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity {


    Fragment mfagment;
    @BindView(R.id.foot1)
    RadioButton mFoot1;
    @BindView(R.id.foot4)
    RadioButton mFoot4;
    @BindView(R.id.main_foot)
    RadioGroup mMainFoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();


    }

    private void initView() {
        RadioGroup footer = (RadioGroup) findViewById(R.id.main_foot);
        footer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foot1:
                        mfagment = new NewsFragment();
                        break;
                    case R.id.foot2:
                        mfagment = new ZhihuFragment();
                        break;
                    case R.id.foot3:
                        mfagment = new Meizi();
                        break;
                    case R.id.foot4:
                        mfagment = new Tuwen();
                }
                loadFragment(mfagment);
            }
        });
    }

    private void loadFragment(Fragment mfagment) {
        FragmentTransaction tansaction = getSupportFragmentManager().beginTransaction();
        tansaction.replace(R.id.fragment_container, mfagment).commit();
    }


    long curTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - curTime < 2000) {
                finish();
                System.exit(0);
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                curTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface LoadingMore {
        void loadingStart();

        void loadingfinish();
    }
}
