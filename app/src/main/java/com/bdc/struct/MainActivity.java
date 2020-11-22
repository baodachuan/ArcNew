package com.bdc.struct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bdc.bui.tab.bottom.HiTabBottom;
import com.bdc.bui.tab.bottom.HiTabBottomInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HiTabBottom bottom=findViewById(R.id.tab_bottom);
        HiTabBottomInfo tabBottomInfo=new HiTabBottomInfo(
                "首页","font/iconfont.ttf",
                getString(R.string.if_home),null,
                "#ff656667","#ffd44949"

        );
        bottom.setHiTabInfo(tabBottomInfo);


        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LogDemoActivity.class));
            }
        });
    }
}
