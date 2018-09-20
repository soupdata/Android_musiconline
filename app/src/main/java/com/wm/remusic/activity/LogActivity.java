package com.wm.remusic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wm.remusic.R;
import com.wm.remusic.http.HttpUtils;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class LogActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        initView();
    }

    private void initView() {

        final EditText logcount = (EditText) findViewById(R.id.count);
        final EditText logPwd = (EditText) findViewById(R.id.logPwd);
        Button btn = (Button) findViewById(R.id.logBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String count = logcount.getText().toString().trim();
               String pwd = logPwd.getText().toString().trim();



               Map<String,String> data =new HashMap<String,String>();
               data.put("username",count);
               data.put("passwd",pwd);
               String url= HttpUtils.URL+"/Servlet";
               String returndata=HttpUtils.SendPostMethod((List<BasicNameValuePair>) data);
                try {
                    JSONObject jsondata=new JSONObject(returndata);
                    if(jsondata.getBoolean("flag")){
                        Toast.makeText(LogActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LogActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
