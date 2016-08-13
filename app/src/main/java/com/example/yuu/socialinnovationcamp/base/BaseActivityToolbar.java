package com.example.yuu.socialinnovationcamp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.yuu.socialinnovationcamp.R;

/**
 * Created by huylv on 13-Aug-16.
 */
public abstract class BaseActivityToolbar extends BaseActivity {

    @Nullable
    protected Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }
}