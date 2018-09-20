package com.wm.remusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.wm.remusic.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1106827060";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logining);
        initLoginView();
    }

    public void initLoginView() {
        final Button reg = (Button) findViewById(R.id.regBtn);
        final Button log = (Button) findViewById(R.id.loginBtn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent();
                regIntent.setClass(LoginActivity.this, RegActivity.class);
                startActivity(regIntent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIntent = new Intent();
                logIntent.setClass(LoginActivity.this,LogActivity.class);
                startActivity(logIntent);
            }
        });
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
        final ImageView qq = (ImageView) findViewById(R.id.qqImage);
        ImageView wx = (ImageView) findViewById(R.id.wxImage);

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QQlogin();
                Toast.makeText(LoginActivity.this, "qq登录", Toast.LENGTH_SHORT).show();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "wx登录", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void QQlogin() {
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this, "all", mIUiListener);
    }

    public void WXlogin() {

    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
