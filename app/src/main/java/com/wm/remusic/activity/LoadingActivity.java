package com.wm.remusic.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wm.remusic.R;

/**
 *
 */
public class LoadingActivity extends Activity {

    //延迟1秒
    private static final long SPLASH_DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // 使用Handler的postDelayed方法，1秒后执行跳转到MainActivity
        new Handler().postDelayed(new Runnable() {
            public void run() {
                goHome();
            }
        }, SPLASH_DELAY_MILLIS);
    }

    private void goHome() {
        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);

        LoadingActivity.this.startActivity(intent);
        LoadingActivity.this.finish();
    }
}
