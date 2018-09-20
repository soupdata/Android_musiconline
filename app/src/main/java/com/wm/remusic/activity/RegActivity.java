package com.wm.remusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wm.remusic.R;

/**
 *
 */

public class RegActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        initView();

    }

    private void initView() {

        final EditText regCount = (EditText) findViewById(R.id.regCount);
        final EditText regPwd = (EditText) findViewById(R.id.regPwd);
        final EditText ReregPwd = (EditText) findViewById(R.id.ReregPwd);
        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = regCount.getText().toString();
                String pwd = regPwd.getText().toString();
                String RePwd = ReregPwd.getText().toString();
                if (!count.isEmpty() || !pwd.isEmpty() || !RePwd.isEmpty() || pwd.equals(RePwd)){
                    //把信息存入服务器

                }
                else{
                    Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
