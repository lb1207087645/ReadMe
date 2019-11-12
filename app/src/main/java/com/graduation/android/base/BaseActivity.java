package com.graduation.android.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.graduation.android.R;


/**
 * Activity基类的实现
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
