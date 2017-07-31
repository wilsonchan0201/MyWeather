package todo.example.willis.myweather.entrance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import todo.example.willis.myweather.MainActivity;
import todo.example.willis.myweather.R;
import todo.example.willis.myweather.utils.LogUtil;

/**
 * Created by willis on 7/13/17.
 */

public class SplashActivity extends Activity {

    @BindView(R.id.spalsh_frame)
    FrameLayout mFrameLayout;

    @BindView(R.id.Test)
    Button mbtn;
    private static final String TAG = "wils-splash";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        mFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.i(TAG, "Thread:" + Thread.currentThread().toString());

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 100);
    }

}
