package com.bdc.struct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.bdc.bui.tab.bottom.HiTabBottom;
import com.bdc.bui.tab.bottom.HiTabBottomInfo;

public class MainActivity extends AppCompatActivity implements MainActivityLogic.ActivityProvider {
    private MainActivityLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logic=new MainActivityLogic(this,savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logic.onSaveInstanceState(outState);
    }
}
